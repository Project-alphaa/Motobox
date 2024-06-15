package motobox.entity;

import com.google.common.collect.Lists;
import motobox.Motobox;
import motobox.block.LaunchGelBlock;
import motobox.block.OffRoadBlock;
import motobox.block.VehicleAssemblerBlock;
import motobox.item.MotoboxItems;
import motobox.item.VehicleInteractable;
import motobox.particle.MotoboxParticles;
import motobox.screen.VehicleScreenHandlerContext;
import motobox.sound.MotoboxSounds;
import motobox.sound.VehicleSoundInstance;
import motobox.util.AUtils;
import motobox.util.midnightcontrols.ControllerUtils;
import motobox.util.network.PayloadPackets;
import motobox.vehicle.*;
import motobox.vehicle.attachment.FrontAttachmentType;
import motobox.vehicle.attachment.RearAttachmentType;
import motobox.vehicle.attachment.front.FrontAttachment;
import motobox.vehicle.attachment.rear.RearAttachment;
import motobox.vehicle.render.RenderableVehicle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.Consumer;

import static motobox.Motobox.id;

@SuppressWarnings("deprecation")
public class VehicleEntity extends BoatEntity implements RenderableVehicle, EntityWithInventory {
    private static final TrackedData<Float> REAR_ATTACHMENT_YAW = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Float> REAR_ATTACHMENT_ANIMATION = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Float> FRONT_ATTACHMENT_ANIMATION = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.FLOAT);

    private VehicleFrame frame = VehicleFrame.REGISTRY.getOrDefault(null);
    private VehicleWheel wheels = VehicleWheel.REGISTRY.getOrDefault(null);
    private VehicleEngine engine = VehicleEngine.REGISTRY.getOrDefault(null);
    private RearAttachment rearAttachment;
    private FrontAttachment frontAttachment;

    private static final TrackedData<Boolean> ROTATE_LEFT = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> ROTATE_RIGHT = DataTracker.registerData(VehicleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private final VehicleStats stats = new VehicleStats();

    @Environment(EnvType.CLIENT)
    private Model frameModel = null;
    @Environment(EnvType.CLIENT)
    private Model wheelModel = null;
    @Environment(EnvType.CLIENT)
    private Model engineModel = null;
    @Environment(EnvType.CLIENT)
    private @Nullable Model rearAttachmentModel = null;
    @Environment(EnvType.CLIENT)
    private @Nullable Model frontAttachmentModel = null;

    public static final int SMALL_TURBO_TIME = 35;
    public static final int MEDIUM_TURBO_TIME = 70;
    public static final int LARGE_TURBO_TIME = 115;
    public static final float TERMINAL_VELOCITY = -1.2f;

    private long clientTime;

    private double trackedX;
    private double trackedY;
    private double trackedZ;
    private float trackedYaw;
    private int lerpTicks;

    private boolean dirty = false;

    private float engineSpeed = 0;
    private float boostSpeed = 0;
    private float speedDirection = 0;
    private float lastBoostSpeed = boostSpeed;

    private int boostTimer = 0;
    private float boostPower = 0;
    private int jumpCooldown = 0;

    private float hSpeed = 0;
    private float vSpeed = 0;

    private Vec3d addedVelocity = getVelocity();

    private float steering = 0;
    private float lastSteering = steering;

    private float angularSpeed = 0;

    private float wheelAngle = 0;
    private float lastWheelAngle = 0;

    private final Displacement displacement = new VehicleEntity.Displacement();

    private boolean drifting = false;
    private boolean burningOut = false;
    private int driftDir = 0;
    private int turboCharge = 0;

    private float lockedViewOffset = 0;

    private boolean vehicleOnGround = true;
    private boolean wasOnGround = vehicleOnGround;
    private boolean isFloorDirectlyBelow = true;
    private boolean touchingWall = false;

    private Vec3d lastVelocity = Vec3d.ZERO;
    private Vec3d lastPosForDisplacement = Vec3d.ZERO;

    private Vec3d prevTailPos = null;

    // Prevents jittering when going down slopes
    private int slopeStickingTimer = 0;

    private int suspensionBounceTimer = 0;
    private int lastSusBounceTimer = suspensionBounceTimer;

    private final Deque<Double> prevYDisplacements = new ArrayDeque<>();

    private boolean offRoad = false;
    private Color debrisColor = new Color(0, 0, 0);

    private int fallTicks = 0;

    private int despawnTime = -1;
    private int despawnCountdown = 0;
    private boolean decorative = false;

    private boolean wasEngineRunning = false;

    private float standStillTime = -1.3f;

    public void writeSyncToClientData(PacketByteBuf buf) {
        buf.writeInt(boostTimer);
        buf.writeFloat(steering);
        buf.writeFloat(wheelAngle);
        buf.writeInt(turboCharge);
        buf.writeFloat(engineSpeed);
        buf.writeFloat(boostSpeed);
        buf.writeByte(compactInputData());

        buf.writeBoolean(drifting);
        buf.writeBoolean(burningOut);
    }

    public void readSyncToClientData(PacketByteBuf buf) {
        boostTimer = buf.readInt();
        steering = buf.readFloat();
        wheelAngle = buf.readFloat();
        turboCharge = buf.readInt();
        engineSpeed = buf.readFloat();
        boostSpeed = buf.readFloat();
        readCompactedInputData(buf.readByte());

        setDrifting(buf.readBoolean());
        setBurningOut(buf.readBoolean());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        setComponents(
                VehicleFrame.REGISTRY.getOrDefault(Identifier.tryParse(nbt.getString("frame"))),
                VehicleWheel.REGISTRY.getOrDefault(Identifier.tryParse(nbt.getString("wheels"))),
                VehicleEngine.REGISTRY.getOrDefault(Identifier.tryParse(nbt.getString("engine")))
        );

        var rAtt = nbt.getCompound("rearAttachment");
        setRearAttachment(RearAttachment.fromNbt(rAtt));
        rearAttachment.readNbt(rAtt);

        var fAtt = nbt.getCompound("frontAttachment");
        setFrontAttachment(FrontAttachment.fromNbt(fAtt));
        frontAttachment.readNbt(fAtt);

        engineSpeed = nbt.getFloat("engineSpeed");
        boostSpeed = nbt.getFloat("boostSpeed");
        boostTimer = nbt.getInt("boostTimer");
        boostPower = nbt.getFloat("boostPower");
        speedDirection = nbt.getFloat("speedDirection");
        vSpeed = nbt.getFloat("verticalSpeed");
        hSpeed = nbt.getFloat("horizontalSpeed");
        addedVelocity = AUtils.v3dFromNbt(nbt.getCompound("addedVelocity"));
        lastVelocity = AUtils.v3dFromNbt(nbt.getCompound("lastVelocity"));
        angularSpeed = nbt.getFloat("angularSpeed");
        steering = nbt.getFloat("steering");
        wheelAngle = nbt.getFloat("wheelAngle");
        drifting = nbt.getBoolean("drifting");
        driftDir = nbt.getInt("driftDir");
        burningOut = nbt.getBoolean("burningOut");
        turboCharge = nbt.getInt("turboCharge");
        accelerating = nbt.getBoolean("accelerating");
        braking = nbt.getBoolean("braking");
        steeringLeft = nbt.getBoolean("steeringLeft");
        steeringRight = nbt.getBoolean("steeringRight");
        holdingDrift = nbt.getBoolean("holdingDrift");
        fallTicks = nbt.getInt("fallTicks");
        despawnTime = nbt.getInt("despawnTime");
        despawnCountdown = nbt.getInt("despawnCountdown");
        decorative = nbt.getBoolean("decorative");

        updateModels = true;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putString("frame", frame.getId().toString());
        nbt.putString("wheels", wheels.getId().toString());
        nbt.putString("engine", engine.getId().toString());
        nbt.put("rearAttachment", rearAttachment.toNbt());
        nbt.put("frontAttachment", frontAttachment.toNbt());
        nbt.putFloat("engineSpeed", engineSpeed);
        nbt.putFloat("boostSpeed", boostSpeed);
        nbt.putInt("boostTimer", boostTimer);
        nbt.putFloat("boostPower", boostPower);
        nbt.putFloat("speedDirection", speedDirection);
        nbt.putFloat("verticalSpeed", vSpeed);
        nbt.putFloat("horizontalSpeed", hSpeed);
        nbt.put("addedVelocity", AUtils.v3dToNbt(addedVelocity));
        nbt.put("lastVelocity", AUtils.v3dToNbt(lastVelocity));
        nbt.putFloat("angularSpeed", angularSpeed);
        nbt.putFloat("steering", steering);
        nbt.putFloat("wheelAngle", wheelAngle);
        nbt.putBoolean("drifting", drifting);
        nbt.putInt("driftDir", driftDir);
        nbt.putBoolean("burningOut", burningOut);
        nbt.putInt("turboCharge", turboCharge);
        nbt.putBoolean("accelerating", accelerating);
        nbt.putBoolean("braking", braking);
        nbt.putBoolean("steeringLeft", steeringLeft);
        nbt.putBoolean("steeringRight", steeringRight);
        nbt.putBoolean("holdingDrift", holdingDrift);
        nbt.putInt("fallTicks", fallTicks);
        nbt.putInt("despawnTime", despawnTime);
        nbt.putInt("despawnCountdown", despawnCountdown);
        nbt.putBoolean("decorative", decorative);
    }

    private boolean accelerating = false;
    private boolean braking = false;
    private boolean steeringLeft = false;
    private boolean steeringRight = false;
    private boolean holdingDrift = false;

    @SuppressWarnings("ConstantValue")
    private boolean prevHoldDrift = holdingDrift;

    public byte compactInputData() {
        int r = ((((((((accelerating ? 1 : 0) << 1) | (braking ? 1 : 0)) << 1) | (steeringLeft ? 1 : 0)) << 1) | (steeringRight ? 1 : 0)) << 1) | (holdingDrift ? 1 : 0);
        return (byte) r;
    }

    public void readCompactedInputData(byte data) {
        int d = data;
        holdingDrift = (1 & d) > 0;
        d = d >> 0b1;
        steeringRight = (1 & d) > 0;
        d = d >> 0b1;
        steeringLeft = (1 & d) > 0;
        d = d >> 0b1;
        braking = (1 & d) > 0;
        d = d >> 0b1;
        accelerating = (1 & d) > 0;
    }

    @Environment(EnvType.CLIENT)
    public boolean updateModels = true;

    public VehicleEntity(EntityType<? extends VehicleEntity> type, World world) {
        super(type, world);

        this.ignoreCameraFrustum = true;

        this.setRearAttachment(RearAttachmentType.REGISTRY.getOrDefault(null));
        this.setFrontAttachment(FrontAttachmentType.REGISTRY.getOrDefault(null));
    }

    public VehicleEntity(World world) {
        this(MotoboxEntities.VEHICLE_ENTITY, world);
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        if (getWorld().isClient()) {
            PayloadPackets.requestSyncVehicleComponentsPacket(this);
        }
    }

    @Override
    public VehicleFrame getFrame() {
        return frame;
    }

    @Override
    public VehicleWheel getWheels() {
        return wheels;
    }

    @Override
    public VehicleEngine getEngine() {
        return engine;
    }

    @Override
    public @Nullable RearAttachment getRearAttachment() {
        return rearAttachment;
    }

    @Override
    public @Nullable FrontAttachment getFrontAttachment() {
        return frontAttachment;
    }

    @Override
    public float getSteering(float tickDelta) {
        return MathHelper.lerp(tickDelta, lastSteering, steering);
    }

    @Override
    public float getWheelAngle(float tickDelta) {
        return MathHelper.lerp(tickDelta, lastWheelAngle, wheelAngle);
    }

    public float getBoostSpeed(float tickDelta) {
        return MathHelper.lerp(tickDelta, lastBoostSpeed, boostSpeed);
    }

    @Override
    public float getSuspensionBounce(float tickDelta) {
        return MathHelper.lerp(tickDelta, lastSusBounceTimer, suspensionBounceTimer);
    }

    @Override
    public boolean engineRunning() {
        return hasPassengers();
    }

    @Override
    public int getTurboCharge() {
        return turboCharge;
    }

    @Override
    public long getTime() {
        return this.clientTime;
    }

    public float getHSpeed() {
        return hSpeed;
    }

    public float getVSpeed() {
        return vSpeed;
    }

    @Override
    public int getBoostTimer() {
        return boostTimer;
    }

    public double getEffectiveSpeed() {
        if (this.getControllingPassenger() instanceof PlayerEntity player && player.isMainPlayer()) {
            return Math.max(this.addedVelocity.length(), Math.abs(this.hSpeed));
        }

        return Math.max(this.addedVelocity.length(), Math.abs(this.engineSpeed + this.boostSpeed));
    }

    @Override
    public boolean vehicleOnGround() {
        return vehicleOnGround;
    }

    @Override
    public boolean debris() {
        return offRoad && hSpeed != 0;
    }

    @Override
    public Color debrisColor() {
        return debrisColor;
    }

    public boolean burningOut() {
        return burningOut;
    }

    private void setDrifting(boolean drifting) {
        if (this.getWorld().isClient() && !this.drifting && drifting) {
            playSkiddingSound();
        }

        this.drifting = drifting;
    }

    private void setBurningOut(boolean burningOut) {
        if (this.getWorld().isClient() && !this.drifting && !this.burningOut && burningOut) {
            playSkiddingSound();
        }

        this.burningOut = burningOut;
    }

    public boolean isDrifting() {
        return this.drifting;
    }

    public <T extends RearAttachment> void setRearAttachment(RearAttachmentType<T> rearAttachment) {
        if (rearAttachment == null) {
            return;
        }
        if (this.rearAttachment == null || this.rearAttachment.type != rearAttachment) {
            if (this.rearAttachment != null) {
                this.rearAttachment.onRemoved();
            }

            this.rearAttachment = rearAttachment.constructor().apply(rearAttachment, this);
            this.rearAttachment.setYaw(this.getYaw());

            if (!getWorld().isClient() && !this.rearAttachment.isRideable() && this.getPassengerList().size() > 1) {
                this.getPassengerList().get(1).stopRiding();
            }

            this.updateModels = true;
            syncAttachments();
        }
    }

    public <T extends FrontAttachment> void setFrontAttachment(FrontAttachmentType<T> frontAttachment) {
        if (frontAttachment == null) {
            return;
        }
        if (this.frontAttachment == null || this.frontAttachment.type != frontAttachment) {
            if (this.frontAttachment != null) {
                this.frontAttachment.onRemoved();
            }
            this.frontAttachment = frontAttachment.constructor().apply(frontAttachment, this);

            this.updateModels = true;
            syncAttachments();
        }
    }

    public void setComponents(VehicleFrame frame, VehicleWheel wheel, VehicleEngine engine) {
        this.frame = frame;
        this.wheels = wheel;
        this.engine = engine;
        this.updateModels = true;
        this.setStepHeight(wheels.size());
        this.stats.from(frame, wheel, engine);
        this.displacement.applyWheelbase(frame.model().wheelBase().get());
        if (!getWorld().isClient()) syncComponents();
    }

    public void forNearbyPlayers(int radius, boolean ignoreDriver, Consumer<ServerPlayerEntity> action) {
        for (PlayerEntity p : getWorld().getPlayers()) {
            if (ignoreDriver && p == getFirstPassenger()) {
                continue;
            }
            if (p.getPos().distanceTo(getPos()) < radius && p instanceof ServerPlayerEntity player) {
                action.accept(player);
            }
        }
    }

    public Vec3d getTailPos() {
        return this.getPos()
                .add(new Vec3d(0, 0, this.getFrame().model().rearAttachmentPos().getFloat() * 0.0625)
                        .rotateY((float) Math.toRadians(180 - this.getYaw()))
                );
    }

    public Vec3d getHeadPos() {
        return this.getPos()
                .add(new Vec3d(0, 0, this.getFrame().model().frontAttachmentPos().getFloat() * 0.0625)
                        .rotateY((float) Math.toRadians(-this.getYaw()))
                );
    }

    public boolean hasSpaceForPassengers() {
        if (frame.id().equals(id("truck"))) {
            return (this.rearAttachment.isRideable()) ? (this.getPassengerList().size() < 4) : (this.getPassengerList().size() < 3);
        } else if (frame.id().equals(id("rusty_car"))) {
            return (this.rearAttachment.isRideable()) ? (this.getPassengerList().size() < 5) : (this.getPassengerList().size() < 4);
        }
        return (this.rearAttachment.isRideable()) ? (this.getPassengerList().size() < 2) : (!this.hasPassengers());
    }

    public void setSpeed(float horizontal, float vertical) {
        this.hSpeed = horizontal;
        this.vSpeed = vertical;
    }

    @Environment(EnvType.CLIENT)
    private void playEngineSound() {
        if (this.getEngine().isEmpty()) {
            return;
        }

        var client = MinecraftClient.getInstance();
        client.getSoundManager().play(new VehicleSoundInstance.EngineSound(client, this));
    }

    @Environment(EnvType.CLIENT)
    private void playSkiddingSound() {
        var client = MinecraftClient.getInstance();
        client.getSoundManager().play(new VehicleSoundInstance.SkiddingSound(client, this));
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        if (source.getAttacker() != null) {
            return false;
        }

        return super.damage(source, amount);
    }

    @Override
    protected void dropItems(DamageSource source) {
        this.dropStack(asPrefabItem());
    }

    @Override
    public void tick() {
        this.ticksUnderwater = this.location == Location.UNDER_WATER || this.location == Location.UNDER_FLOWING_WATER ? this.ticksUnderwater + 1.0f : 0.0f;

        if (!this.getWorld().isClient && this.ticksUnderwater >= 60.0f) {
            this.removeAllPassengers();
        }

        boolean first = this.firstUpdate;

        if (lastWheelAngle != wheelAngle) markDirty();
        lastWheelAngle = wheelAngle;

        if (!this.wasEngineRunning && this.engineRunning() && this.getWorld().isClient()) {
            playEngineSound();
        }
        this.wasEngineRunning = this.engineRunning();

        if (!this.hasPassengers() || !Objects.requireNonNull(this.getFrontAttachment()).canDrive(this.getFirstPassenger())) {
            accelerating = false;
            braking = false;
            steeringLeft = false;
            steeringRight = false;
            holdingDrift = false;
        }

        if (this.jumpCooldown > 0) {
            this.jumpCooldown--;
        }

        super.baseTick();
        if (!this.rearAttachment.type.isEmpty()) this.rearAttachment.tick();
        if (!this.frontAttachment.type.isEmpty()) this.frontAttachment.tick();

        var prevPos = this.getPos();

        positionTrackingTick();
        collisionStateTick();
        steeringTick();
        driftingTick();
        burnoutTick();

        movementTick();
        if (this.isLogicalSideForUpdatingMovement()) {
            this.move(MovementType.SELF, this.getVelocity());
        }
        postMovementTick();

        if (!getWorld().isClient()) {
            var prevTailPos = this.prevTailPos != null ? this.prevTailPos : this.getTailPos();
            var tailPos = this.getTailPos();

            this.rearAttachment.pull(prevTailPos.subtract(tailPos));
            this.prevTailPos = tailPos;

            if (dirty) {
                syncData();
                dirty = false;
            }
//            if (this.hasSpaceForPassengers() && !decorative) {
//                var touchingEntities = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(0.2, 0, 0.2), EntityPredicates.canBePushedBy(this));
//                for (Entity entity : touchingEntities) {
//                    if (!entity.hasPassenger(this)) {
//                        if (!entity.hasVehicle() && entity.getWidth() <= this.getWidth() && entity instanceof MobEntity && !(entity instanceof WaterCreatureEntity)) {
//                            entity.startRiding(this);
//                        }
//                    }
//                }
//            }
            if (this.hasPassengers()) {
                if (Objects.requireNonNull(this.getFrontAttachment()).canDrive(this.getFirstPassenger()) && this.getFirstPassenger() instanceof MobEntity mob) {
                    provideMobDriverInputs(mob);
                }

                this.despawnCountdown = 0;
            } else if (this.despawnTime > 0) {
                this.despawnCountdown++;

                if (this.despawnCountdown >= this.despawnTime) {
                    this.destroyVehicle(false, RemovalReason.DISCARDED);
                }
            }
        } else {
            clientTime++;

            lastSusBounceTimer = suspensionBounceTimer;
            if (suspensionBounceTimer > 0) {
                suspensionBounceTimer--;
            }

            if (Math.abs(this.hSpeed) < 0.05 && !this.burningOut && this.getControllingPassenger() instanceof PlayerEntity) {
                this.standStillTime = AUtils.shift(this.standStillTime, 0.05f, 1f);
            } else {
                this.standStillTime = AUtils.shift(this.standStillTime, 0.15f, -1.3f);
            }
        }

        displacementTick(first || (this.getPos().subtract(prevPos).length() > 0.01 || this.getYaw() != this.prevYaw));
    }

    public void positionTrackingTick() {
        if (this.isLogicalSideForUpdatingMovement()) {
            this.lerpTicks = 0;
            updateTrackedPosition(getX(), getY(), getZ());
        } else if (lerpTicks > 0) {
            this.setPosition(
                    this.getX() + ((this.trackedX - this.getX()) / (double) this.lerpTicks),
                    this.getY() + ((this.trackedY - this.getY()) / (double) this.lerpTicks),
                    this.getZ() + ((this.trackedZ - this.getZ()) / (double) this.lerpTicks)
            );
            this.setYaw(this.getYaw() + (MathHelper.wrapDegrees(this.trackedYaw - this.getYaw()) / (float) this.lerpTicks));

            this.lerpTicks--;
        }
    }

    public void markDirty() {
        dirty = true;
    }

    private void syncData() {
        forNearbyPlayers(200, true, player -> PayloadPackets.sendSyncVehicleDataPacket(this, player));
    }

    private void syncComponents() {
        forNearbyPlayers(200, false, player -> PayloadPackets.sendSyncVehicleComponentsPacket(this, player));
    }

    private void syncAttachments() {
        forNearbyPlayers(200, false, player -> PayloadPackets.sendSyncVehicleAttachmentsPacket(this, player));
    }

    public ItemStack asPrefabItem() {
        var stack = new ItemStack(MotoboxItems.VEHICLE);
        var vehicle = stack.getOrCreateSubNbt("Vehicle");
        vehicle.putString("frame", frame.getId().toString());
        vehicle.putString("wheels", wheels.getId().toString());
        vehicle.putString("engine", engine.getId().toString());
        return stack;
    }

    @Nullable
    @Override
    public ItemStack getPickBlockStack() {
        return asPrefabItem();
    }

    // making mobs drive vehicles
    // technically the mobs don't drive, instead the vehicle
    // self-drives to the mob's destination...
    public void provideMobDriverInputs(MobEntity driver) {
        // Don't move if the driver doesn't exist or can't drive
        if (driver == null || driver.isDead() || driver.isRemoved()) {
            if (accelerating || steeringLeft || steeringRight) markDirty();
            accelerating = false;
            steeringLeft = false;
            steeringRight = false;
            return;
        }

        var path = driver.getNavigation().getCurrentPath();
        // checks if there is a current, incomplete path that the entity has targeted
        if (path != null && !path.isFinished() && path.getEnd() != null) {
            // determines the relative position to drive to, based on the end of the path
            var pos = path.getEnd().getPos().subtract(getPos());
            // determines the angle to that position
            double target = MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(pos.getX(), pos.getZ())));
            // determines another relative position, this time to the path's current node (in the case of the path directly to the end being obstructed)
            var fnPos = path.getCurrentNode().getPos().subtract(getPos());
            // determines the angle to that current node's position
            double fnTarget = MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(fnPos.getX(), fnPos.getZ())));
            // if the difference in angle between the end position and the current node's position is too great,
            // the vehicle will drive to that current node under the assumption that the path directly to the
            // end is obstructed
            if (Math.abs(target - fnTarget) > 69) {
                pos = fnPos;
                target = fnTarget;
            }
            // fixes up the vehicle's own yaw value
            float yaw = MathHelper.wrapDegrees(-getYaw());
            // finds the difference between the target angle and the yaw
            double offset = MathHelper.wrapDegrees(yaw - target);
            // whether the vehicle should go in reverse
            boolean reverse = false;
            // a value to determine the threshold used to determine whether the vehicle is moving
            // both slow enough and is at an extreme enough offset angle to incrementally move in reverse
            float mul = 0.5f + (MathHelper.clamp(hSpeed, 0, 1) * 0.5f);
            if (pos.length() < 20 * mul && Math.abs(offset) > 180 - (170 * mul)) {
                long time = getWorld().getTime();
                // this is so that the vehicle alternates between reverse and forward,
                // like a driver would do in order to angle their vehicle toward a target location
                reverse = (time % 80 <= 30);
            }
            // set the accel/brake inputs
            accelerating = !reverse;
            braking = reverse;
            // set the steering inputs, with a bit of a dead zone to prevent jittering
            if (offset < -7) {
                steeringRight = reverse;
                steeringLeft = !reverse;
            } else if (offset > 7) {
                steeringRight = !reverse;
                steeringLeft = reverse;
            }
            markDirty();
        } else {
            if (accelerating || steeringLeft || steeringRight) markDirty();
            accelerating = false;
            steeringLeft = false;
            steeringRight = false;
        }
    }

    public void movementTick() {
        // Handles boosting
        lastBoostSpeed = boostSpeed;
        if (boostTimer > 0) {
            boostTimer--;
            boostSpeed = Math.min(boostPower, boostSpeed + 0.09f);
            if (engineSpeed < stats.getComfortableSpeed()) {
                engineSpeed += 0.012f;
            }
            markDirty();
        } else {
            boostSpeed = AUtils.zero(boostSpeed, 0.09f);
        }

        // Get block below's friction
        var blockBelow = new BlockPos(getBlockX(), (int) (getY() - 0.05), getBlockZ());
        float grip = 1 - ((MathHelper.clamp((getWorld().getBlockState(blockBelow).getBlock().getSlipperiness() - 0.6f) / 0.4f, 0, 1) * (1 - stats.getGrip() * 0.8f)));

        // Bounce on gel
        if (this.vehicleOnGround && this.jumpCooldown <= 0 && getWorld().getBlockState(this.getBlockPos()).getBlock() instanceof LaunchGelBlock) {
            this.setSpeed(Math.max(this.getHSpeed(), 0.1f), Math.max(this.getVSpeed(), 0.9f));
            this.jumpCooldown = 5;
            this.vehicleOnGround = false;
        }

        // Track the last position of the vehicle
        this.lastPosForDisplacement = getPos();

        // cumulative will be modified by the following code and then the vehicle will be moved by it
        // Currently initialized with the value of addedVelocity (which is a general velocity vector applied to the vehicle, i.e. for when it bumps into a wall and is pushed back)
        var cumulative = addedVelocity;

        // Reduce gravity underwater
        cumulative = cumulative.add(0, (vSpeed * (isSubmergedInWater() ? 0.15f : 1)), 0);

        // This is the general direction the vehicle will move, which is slightly offset to the side when drifting and delayed when on slippery surface
        this.speedDirection = MathHelper.lerp(grip, getYaw(), getYaw() - (drifting ? Math.min(turboCharge * 6, 43 + (-steering * 12)) * driftDir : -steering * 12));

        // Handle acceleration
        if (accelerating) {
            float speed = Math.max(this.engineSpeed, 0);
            // yeah ...
            this.engineSpeed +=
                    // The following conditions check whether the vehicle should NOT receive normal acceleration
                    // It will not receive this acceleration if the vehicle is steering or tight-drifting
                    (float) ((
                                                (this.drifting && AUtils.haveSameSign(this.steering, this.driftDir)) ||
                                                        (!this.drifting && this.steering != 0 && hSpeed > 0.5)
                                        ) ? (this.hSpeed < stats.getComfortableSpeed() ? 0.001 : 0) // This will supply a small amount of acceleration if the vehicle is moving slowly only
                    
                                                // Otherwise, it will receive acceleration as normal
                                                // It will receive this acceleration if the vehicle is moving straight or wide-drifting (the latter slightly reduces acceleration)
                                                : calculateAcceleration(speed, stats) * (drifting ? 0.86 : 1) * (engineSpeed > stats.getComfortableSpeed() ? 0.25f : 1) * grip);
        }

        // Handle braking/reverse
        if (braking) {
            this.engineSpeed = Math.max(this.engineSpeed - 0.15f, -0.25f);
        }
        // Handle when the vehicle is rolling to a stop
        if (!accelerating && !braking) {
            this.engineSpeed = AUtils.zero(this.engineSpeed, 0.025f);
        }

        // Slow the vehicle a bit while steering and moving fast
        if (!drifting && steering != 0 && hSpeed > 0.8) {
            engineSpeed -= engineSpeed * 0.00042f;
        }

        if (this.burningOut()) {
            engineSpeed -= (float) ((engineSpeed) * 0.5);
        }

        // Allows for the sticky slope effect to continue for a tick after not being on a slope
        // This prevents the vehicle from randomly jumping if it's moving down a slope quickly
        var below = new BlockPos(MathHelper.floor(getX()), MathHelper.floor(getY() - 0.51), MathHelper.floor(getZ()));
        var state = getWorld().getBlockState(below);
        if (state.isIn(Motobox.STICKY_SLOPES)) {
            slopeStickingTimer = 1;
        } else {
            slopeStickingTimer = Math.max(0, slopeStickingTimer - 1);
        }

        // Handle being in off-road
        if (boostSpeed < 0.4f && getWorld().getBlockState(getBlockPos()).getBlock() instanceof OffRoadBlock offRoadBlock) {
            int layers = getWorld().getBlockState(getBlockPos()).get(OffRoadBlock.LAYERS);
            float cap = stats.getComfortableSpeed() * (1 - ((float) layers / 3.5f));
            engineSpeed = Math.min(cap, engineSpeed);
            this.debrisColor = offRoadBlock.color;
            this.offRoad = true;
        } else this.offRoad = false;

        // Set the horizontal speed
        if (!burningOut()) hSpeed = engineSpeed + boostSpeed;

        // Sticking to sticky slopes
        double lowestPrevYDisp = 0;
        for (double d : prevYDisplacements) {
            lowestPrevYDisp = Math.min(d, lowestPrevYDisp);
        }
        if (slopeStickingTimer > 0 && vehicleOnGround && lowestPrevYDisp <= 0) {
            double cumulHSpeed = Math.sqrt((cumulative.x * cumulative.x) + (cumulative.z * cumulative.z));
            cumulative = cumulative.add(0, -(0.25 + cumulHSpeed), 0);
        }


        float angle = (float) Math.toRadians(-speedDirection);
        if (this.burningOut()) {
            if (Math.abs(hSpeed) > 0.02) {
                this.addedVelocity = new Vec3d(Math.sin(angle) * hSpeed, 0, Math.cos(angle) * hSpeed);
                this.hSpeed = 0;
                cumulative = cumulative.add(addedVelocity);
            }
        } else {
            // Apply the horizontal speed to the cumulative movement
            cumulative = cumulative.add(Math.sin(angle) * hSpeed, 0, Math.cos(angle) * hSpeed);
        }

        // Turn the wheels
        float wheelCircumference = (float) (2 * (wheels.model().radius() / 16) * Math.PI);
        if (hSpeed > 0) markDirty();
        wheelAngle += 300 * (hSpeed / wheelCircumference) + (hSpeed > 0 ? ((1 - grip) * 15) : 0); // made it a bit slower intentionally, also make it spin more when on slippery surface

        // Set the vehicle's velocity
        if (this.isLogicalSideForUpdatingMovement()) {
            this.setVelocity(cumulative);
        }

        lastVelocity = cumulative;

        // Damage and launch entities that are hit by a moving vehicle
        if (Math.abs(hSpeed) > 0.2) {
            runOverEntities(cumulative);
        }
    }

    public void runOverEntities(Vec3d velocity) {
        var frontBox = getBoundingBox().offset(velocity.multiply(0.5));
        var velAdd = velocity.add(0, 0.1, 0).multiply(3);
        for (var entity : getWorld().getEntitiesByType(TypeFilter.instanceOf(Entity.class), frontBox, entity -> entity != this && entity != getFirstPassenger())) {
            if (!entity.isInvulnerable()) {
                if (entity instanceof LivingEntity living && entity.getVehicle() != this) {
                    living.damage(getWorld().getDamageSources().create(MotoboxEntities.VEHICLE_DAMAGE_TYPE), hSpeed * 10);

                    entity.addVelocity(velAdd.x, velAdd.y, velAdd.z);
                }
            }
        }
    }

    public void postMovementTick() {
        float addedVelReduction = 0.1f;
        if (this.burningOut()) {
            addedVelReduction = 0.05f;
        }

        // Reduce the values of addedVelocity incrementally
        double addVelLen = addedVelocity.length();
        if (addVelLen > 0)
            addedVelocity = addedVelocity.multiply(Math.max(0, addVelLen - addedVelReduction) / addVelLen);

        float angle = (float) Math.toRadians(-speedDirection);
        if (touchingWall && hSpeed > 0.1 && addedVelocity.length() <= 0) {
            engineSpeed /= 3.6F;
            double knockSpeed = ((-0.2 * hSpeed) - 0.5);
            addedVelocity = addedVelocity.add(Math.sin(angle) * knockSpeed, 0, Math.cos(angle) * knockSpeed);

            getWorld().playSound(this.getX(), this.getY(), this.getZ(), MotoboxSounds.COLLISION, SoundCategory.AMBIENT, 0.76f, 0.65f + (0.06f * (this.getWorld().random.nextFloat() - 0.5f)), true);
        }

        double yDisp = getPos().subtract(this.lastPosForDisplacement).getY();

        // Increment the falling timer
        if (!vehicleOnGround && yDisp < 0) {
            fallTicks += 1;
        } else {
            fallTicks = 0;
        }

        // Handle launching off slopes
        double highestPrevYDisp = 0;
        for (double d : prevYDisplacements) {
            highestPrevYDisp = Math.max(d, highestPrevYDisp);
        }
        if (wasOnGround && !vehicleOnGround && !isFloorDirectlyBelow) {
            vSpeed = (float) MathHelper.clamp(highestPrevYDisp, 0, hSpeed * 0.6f);
        }

        // Handles gravity
        vSpeed = Math.max(vSpeed - 0.08f, !vehicleOnGround ? TERMINAL_VELOCITY : -0.01f);

        // Store previous y displacement to use when launching off slopes
        prevYDisplacements.push(yDisp);
        if (prevYDisplacements.size() > 2) {
            prevYDisplacements.removeLast();
        }

        // Handle setting the locked view offset
        if (hSpeed != 0) {
            float vOTarget = (drifting ? driftDir * -23 : steering * -5.6f);
            if (vOTarget == 0) lockedViewOffset = AUtils.zero(lockedViewOffset, 2.5f);
            else {
                if (lockedViewOffset < vOTarget) lockedViewOffset = Math.min(lockedViewOffset + 3.7f, vOTarget);
                else lockedViewOffset = Math.max(lockedViewOffset - 3.7f, vOTarget);
            }
        }

        if (this.burningOut()) {
            float speed = (float) this.addedVelocity.length();
            float acc = (1.7f / (1 + this.frame.weight())) + (4 * speed);
            float lim = 9 + (4 * speed);
            if (this.steering != 0) {
                this.angularSpeed = MathHelper.clamp(this.angularSpeed + (acc * this.steering), -lim, lim);
            } else {
                this.angularSpeed = AUtils.shift(this.angularSpeed, acc * 0.5f, 0);
            }
        } else if (hSpeed != 0) {
            float traction = (1 / (1 + (4 * this.hSpeed))) + (0.3f * this.stats.getGrip());
            this.angularSpeed = AUtils.shift(this.angularSpeed, 6 * traction,
                    (drifting ? (((this.steering + (driftDir)) * driftDir * 2.5f + 1.5f) * driftDir) * (((1 - stats.getGrip()) + 2) / 2.5f) : this.steering * ((4f * Math.min(hSpeed, 1)) + (hSpeed > 0 ? 2 : -3.5f))) * ((stats.getHandling() + 1) / 2));
        } else {
            this.angularSpeed = AUtils.shift(this.angularSpeed, 3, 0);
        }

        // Turns the vehicle based on steering/drifting
        if (hSpeed != 0 || this.burningOut()) {
            float yawInc = angularSpeed;// + (drifting ? (((this.steering + (driftDir)) * driftDir * 2.5f + 1.5f) * driftDir) * (((1 - stats.getGrip()) + 2) / 2.5f) : this.steering * ((4f * Math.min(hSpeed, 1)) + (hSpeed > 0 ? 2 : -3.5f))) * ((stats.getHandling() + 1) / 2);
            float prevYaw = getYaw();
            this.setYaw(getYaw() + yawInc);
            // Commented out to stop player model from jittering around when driving
//            if (getWorld().isClient) {
//                var passenger = getFirstPassenger();
//                if (passenger instanceof PlayerEntity player) {
//                    if (inLockedViewMode()) {
//                        player.setYaw(MathHelper.wrapDegrees(getYaw() + lockedViewOffset));
//                        player.setBodyYaw(MathHelper.wrapDegrees(getYaw() + lockedViewOffset));
//                    } else {
//                        player.setYaw(MathHelper.wrapDegrees(player.getYaw() + yawInc));
//                        player.setBodyYaw(MathHelper.wrapDegrees(player.getYaw() + yawInc));
//                    }
//                }
//            } else {
//                for (Entity e : getPassengerList()) {
//                    if (e == getFirstPassenger()) {
//                        e.setYaw(MathHelper.wrapDegrees(e.getYaw() + yawInc));
//                        e.setBodyYaw(MathHelper.wrapDegrees(e.getYaw() + yawInc));
//                    }
//                }
//            }
            if (getWorld().isClient()) {
                this.prevYaw = prevYaw;
            }
        }
    }

    @Override
    public void move(MovementType movementType, Vec3d movement) {
        if (!this.getWorld().isClient() && movementType == MovementType.PLAYER) {
            AUtils.IGNORE_ENTITY_GROUND_CHECK_STEPPING = true;
        }
        super.move(movementType, movement);
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false; // Riders shouldn't take fall damage
    }

    public void displacementTick(boolean tick) {
        if (this.getWorld().isClient()) {
            this.displacement.preTick();

            if (tick) {
                this.displacement.tick(this.getWorld(), this, this.getPos(), this.getYaw(), this.getStepHeight());
            }

            if (getWorld().getBlockState(this.getBlockPos()).getBlock() instanceof VehicleAssemblerBlock) {
                this.displacement.lastVertical = this.displacement.verticalTarget = (-this.wheels.model().radius() / 16);
            }
        }
    }

    public Displacement getDisplacement() {
        return this.displacement;
    }

    public void collisionStateTick() {
        wasOnGround = vehicleOnGround;
        vehicleOnGround = false;
        isFloorDirectlyBelow = false;
        touchingWall = false;
        var b = getBoundingBox();
        var groundBox = new Box(b.minX, b.minY - 0.04, b.minZ, b.maxX, b.minY, b.maxZ);
        var wid = (b.getXLength() + b.getZLength()) * 0.5f;
        var floorBox = new Box(b.minX + (wid * 0.94), b.minY - 0.05, b.minZ + (wid * 0.94), b.maxX - (wid * 0.94), b.minY, b.maxZ - (wid * 0.94));
        var wallBox = b.contract(0.05).offset(this.lastVelocity.normalize().multiply(0.12));
        var start = new BlockPos(
                (int) (b.minX - 0.1), (int) (b.minY - 0.2), (int) (b.minZ - 0.1));
        var end = new BlockPos((int) (b.maxX + 0.1), (int) (b.maxY + 0.2 + this.getStepHeight()), (int) (b.maxZ + 0.1));
        var groundCuboid = VoxelShapes.cuboid(groundBox);
        var floorCuboid = VoxelShapes.cuboid(floorBox);
        var wallCuboid = VoxelShapes.cuboid(wallBox);
        var stepWallCuboid = wallCuboid.offset(0, this.getStepHeight() - 0.05, 0);
        boolean wallHit = false;
        boolean stepWallHit = false;
        var shapeCtx = ShapeContext.of(this);
        if (this.getWorld().isRegionLoaded(start, end)) {
            var pos = new BlockPos.Mutable();
            for (int x = start.getX(); x <= end.getX(); ++x) {
                for (int y = start.getY(); y <= end.getY(); ++y) {
                    for (int z = start.getZ(); z <= end.getZ(); ++z) {
                        pos.set(x, y, z);
                        var state = this.getWorld().getBlockState(pos);
                        var blockShape = state.getCollisionShape(this.getWorld(), pos, shapeCtx).offset(pos.getX(), pos.getY(), pos.getZ());
                        this.vehicleOnGround = this.vehicleOnGround || VoxelShapes.matchesAnywhere(blockShape, groundCuboid, BooleanBiFunction.AND);
                        this.isFloorDirectlyBelow = this.isFloorDirectlyBelow || VoxelShapes.matchesAnywhere(blockShape, floorCuboid, BooleanBiFunction.AND);
                        wallHit = wallHit || VoxelShapes.matchesAnywhere(blockShape, wallCuboid, BooleanBiFunction.AND);
                        stepWallHit = stepWallHit || VoxelShapes.matchesAnywhere(blockShape, stepWallCuboid, BooleanBiFunction.AND);
                    }
                }
            }
        }
        this.touchingWall = (wallHit && stepWallHit);
    }

    public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps, boolean interpolate) {
        this.trackedX = x;
        this.trackedY = y;
        this.trackedZ = z;
        this.trackedYaw = yaw;
        this.lerpTicks = this.getType().getTrackTickInterval() + 1;
    }

    private float calculateAcceleration(float speed, VehicleStats stats) {
        // A somewhat over-engineered function to accelerate the vehicle, since I didn't want to add a hard speed cap
        return (1 / ((300 * speed) + (18.5f - (stats.getAcceleration() * 5.3f)))) * (0.9f * ((stats.getAcceleration() + 1) / 2));
    }

    @Environment(EnvType.CLIENT)
    public void provideClientInput(boolean fwd, boolean back, boolean left, boolean right, boolean space) {
        // Receives inputs client-side and sends them to the server
        if (!(
                fwd == accelerating &&
                        back == braking &&
                        left == steeringLeft &&
                        right == steeringRight &&
                        space == holdingDrift
        )) {
            setInputs(fwd, back, left, right, space);
            PayloadPackets.sendSyncVehicleInputPacket(this, accelerating, braking, steeringLeft, steeringRight, holdingDrift);
        }
    }

    public void setInputs(boolean fwd, boolean back, boolean left, boolean right, boolean space) {
        this.prevHoldDrift = this.holdingDrift;
        this.accelerating = fwd;
        this.braking = back;
        this.steeringLeft = left;
        this.steeringRight = right;
        this.holdingDrift = space;
    }

    public void boost(float power, int time) {
        if (power > boostPower || time > boostTimer) {
            boostTimer = time;
            boostPower = power;
        }
        if (this.isLogicalSideForUpdatingMovement()) {
            this.engineSpeed = Math.max(this.engineSpeed, this.stats.getComfortableSpeed() * 0.5f);
        }
    }

    private void steeringTick() {
        // Adjust the steering based on the left/right inputs
        this.lastSteering = steering;
        if (steeringLeft == steeringRight) {
            this.steering = AUtils.zero(this.steering, 0.42f);
        } else if (steeringLeft) {
            this.steering -= 0.42f;
            this.steering = Math.max(-1, this.steering);
        } else {
            this.steering += 0.42f;
            this.steering = Math.min(1, this.steering);
        }
    }

    private void consumeTurboCharge() {
        if (turboCharge > LARGE_TURBO_TIME) {
            boost(0.38f, 38);
        } else if (turboCharge > MEDIUM_TURBO_TIME) {
            boost(0.3f, 21);
        } else if (turboCharge > SMALL_TURBO_TIME) {
            boost(0.23f, 9);
        }
        turboCharge = 0;
    }

    private void driftingTick() {
        // Handles starting a drift
        if (steering != 0) {
            if (!drifting && !prevHoldDrift && holdingDrift && hSpeed > 0.4f && vehicleOnGround) {
                setDrifting(true);
                driftDir = steering > 0 ? 1 : -1;
                // Reduce speed when a drift starts, based on how long the last drift was for
                // This allows you to do a series of short drifts without tanking all your speed, while still reducing your speed when you begin the drift(s)
                engineSpeed -= (float) (0.028 * engineSpeed);
            }
        }
        // Handles drifting effects, ending a drift, and the drift timer (for drift turbos)
        if (drifting) {
            if (this.vehicleOnGround()) createDriftParticles();
            // Ending a drift successfully, giving you a turbo boost
            if (prevHoldDrift && !holdingDrift) {
                setDrifting(false);
                consumeTurboCharge();
                // Ending a drift unsuccessfully, not giving you a boost
            } else if (hSpeed < 0.33f) {
                setDrifting(false);
                turboCharge = 0;
            }
            if (vehicleOnGround)
                turboCharge += ((steeringLeft && driftDir < 0) || (steeringRight && driftDir > 0)) ? 2 : 1;
        }
    }

    private void endBurnout() {
        setBurningOut(false);
        this.engineSpeed = 0;
    }

    private void burnoutTick() {
        if (this.burningOut()) {
            if (this.vehicleOnGround()) {
                if (this.addedVelocity.length() > 0.05 || Math.abs(this.angularSpeed) > 0.05) {
                    createDriftParticles();
                }
                if (hSpeed < 0.08 && turboCharge <= SMALL_TURBO_TIME) turboCharge += 1;
            }
            if (!this.braking) {
                endBurnout();
                consumeTurboCharge();
            } else if (!this.accelerating) {
                endBurnout();
                this.turboCharge = 0;
            }
            this.wheelAngle += 20;
        } else if ((this.accelerating || hSpeed > 0.05) && this.braking) {
            setBurningOut(true);
            this.turboCharge = 0;
        }
    }

    public void createDriftParticles() {
        var origin = this.getPos().add(0, this.displacement.verticalTarget, 0);
        for (var wheel : this.getFrame().model().wheelBase().get().wheels) {
            if (wheel.end() == WheelBase.WheelEnd.BACK) {
                var pos = new Vec3d(wheel.right() + ((wheel.right() > 0 ? 1 : -1) * this.getWheels().model().width() * wheel.scale()), 0, wheel.forward())
                        .rotateX((float) Math.toRadians(this.displacement.currAngularX))
                        .rotateZ((float) Math.toRadians(this.displacement.currAngularZ))
                        .rotateY((float) Math.toRadians(-this.getYaw())).multiply(0.0625).add(0, 0.4, 0);
                getWorld().addParticle(MotoboxParticles.DRIFT_SMOKE, origin.x + pos.x, origin.y + pos.y, origin.z + pos.z, 0, 0, 0);
            }
        }
    }

    private static boolean inLockedViewMode() {
        return ControllerUtils.inControllerMode();
    }

    @Environment(EnvType.CLIENT)
    private void updateModels(EntityRendererFactory.Context ctx) {
        if (updateModels) {
            this.frameModel = frame.model().model().apply(ctx);
            this.wheelModel = wheels.model().model().apply(ctx);
            this.engineModel = engine.model().model().apply(ctx);
            this.rearAttachmentModel = this.rearAttachment.type.model().model().apply(ctx);
            this.frontAttachmentModel = this.frontAttachment.type.model().model().apply(ctx);

            updateModels = false;
        }
    }

    @Environment(EnvType.CLIENT)
    public Model getWheelModel(EntityRendererFactory.Context ctx) {
        updateModels(ctx);
        return wheelModel;
    }

    @Environment(EnvType.CLIENT)
    public Model getFrameModel(EntityRendererFactory.Context ctx) {
        updateModels(ctx);
        return frameModel;
    }

    @Environment(EnvType.CLIENT)
    public Model getEngineModel(EntityRendererFactory.Context ctx) {
        updateModels(ctx);
        return engineModel;
    }

    @Override
    public @Nullable Model getRearAttachmentModel(EntityRendererFactory.Context ctx) {
        updateModels(ctx);
        return rearAttachmentModel;
    }

    @Override
    public @Nullable Model getFrontAttachmentModel(EntityRendererFactory.Context ctx) {
        updateModels(ctx);
        return frontAttachmentModel;
    }

    @Override
    public float getVehicleYaw(float tickDelta) {
        return getYaw(tickDelta);
    }

    @Override
    public float getRearAttachmentYaw(float tickDelta) {
        return this.rearAttachment.yaw(tickDelta);
    }
    
    public LivingEntity getControllingPassenger() {
        return getFirstPassenger() instanceof LivingEntity entity ? entity : null;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.hasSpaceForPassengers();
    }

    @Override
    public boolean hasInventory() {
        var attachment = this.getRearAttachment();
        return attachment != null && attachment.hasMenu();
    }

    @Override
    public void openInventory(PlayerEntity player) {
        var attachment = this.getRearAttachment();
        if (attachment == null) return;
        var factory = attachment.createMenu(new VehicleScreenHandlerContext(this));
        if (factory != null) {
            player.openHandledScreen(factory);
        }
    }

    public float getStandStillTime() {
        return this.standStillTime;
    }

    public void playHitSound() {
        getWorld().emitGameEvent(this, GameEvent.ENTITY_DAMAGE, getPos());
        getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_COPPER_BREAK, SoundCategory.AMBIENT, 1, 0.9f + (this.getWorld().random.nextFloat() * 0.2f));
    }

    private void dropParts(Vec3d pos) {
        getWorld().spawnEntity(new ItemEntity(getWorld(), pos.x, pos.y, pos.z, MotoboxItems.VEHICLE_FRAME.createStack(this.getFrame())));
        getWorld().spawnEntity(new ItemEntity(getWorld(), pos.x, pos.y, pos.z, MotoboxItems.VEHICLE_ENGINE.createStack(this.getEngine())));

        var wheelStack = MotoboxItems.VEHICLE_WHEEL.createStack(this.getWheels());
        wheelStack.setCount(this.getFrame().model().wheelBase().get().wheelCount);
        getWorld().spawnEntity(new ItemEntity(getWorld(), pos.x, pos.y, pos.z, wheelStack));
    }

    public void destroyRearAttachment(boolean drop) {
        if (drop) {
            var dropPos = this.rearAttachment.pos();
            getWorld().spawnEntity(new ItemEntity(getWorld(), dropPos.x, dropPos.y, dropPos.z,
                    MotoboxItems.REAR_ATTACHMENT.createStack(this.getRearAttachmentType())));
        }
        this.setRearAttachment(RearAttachmentType.EMPTY);
    }

    public void destroyFrontAttachment(boolean drop) {
        if (drop) {
            var dropPos = this.frontAttachment.pos();
            getWorld().spawnEntity(new ItemEntity(getWorld(), dropPos.x, dropPos.y, dropPos.z,
                    MotoboxItems.FRONT_ATTACHMENT.createStack(this.getFrontAttachmentType())));
        }
        this.setFrontAttachment(FrontAttachmentType.EMPTY);
    }

    public void destroyVehicle(boolean drop, RemovalReason reason) {
        if (!this.rearAttachment.type.isEmpty()) {
            this.destroyRearAttachment(drop);
        }
        if (!this.frontAttachment.type.isEmpty()) {
            this.destroyFrontAttachment(drop);
        }
        if (drop) {
            this.dropParts(this.getPos().add(0, 0.3, 0));
        }
        this.remove(reason);
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (player.isSneaking()) {
            if (this.hasInventory()) {
                if (!getWorld().isClient()) {
                    openInventory(player);
                    return ActionResult.PASS;
                } else {
                    return ActionResult.SUCCESS;
                }
            }
        }

        var stack = player.getStackInHand(hand);
        if ((!this.decorative || player.isCreative()) && stack.isOf(MotoboxItems.HAMMER)) {
            double playerAngle = Math.toDegrees(Math.atan2(player.getZ() - this.getZ(), player.getX() - this.getX()));
            double angleDiff = MathHelper.wrapDegrees(this.getYaw() - playerAngle);

            if (angleDiff < 0 && !this.frontAttachment.type.isEmpty()) {
                this.destroyFrontAttachment(!player.isCreative());
                this.playHitSound();

                return ActionResult.success(getWorld().isClient);
            } else if (!this.rearAttachment.type.isEmpty()) {
                this.destroyRearAttachment(!player.isCreative());
                this.playHitSound();

                return ActionResult.success(getWorld().isClient);
            } else {
                this.destroyVehicle(!player.isCreative(), RemovalReason.KILLED);
                this.playHitSound();

                return ActionResult.success(getWorld().isClient);
            }
        }

        if (!decorative) {
            if (stack.getItem() instanceof VehicleInteractable interactable) {
                return interactable.interactVehicle(stack, player, hand, this);
            }

            if (!this.hasSpaceForPassengers()) {
                final Entity firstPassenger = this.getFirstPassenger();
                if (!(firstPassenger instanceof PlayerEntity)) {
                    if (!getWorld().isClient() && firstPassenger != null) {
                        firstPassenger.stopRiding();
                    }
                    return ActionResult.success(getWorld().isClient);
                }
                return ActionResult.PASS;
            }
            if (!getWorld().isClient()) {
                player.startRiding(this);
            }
            return ActionResult.success(getWorld().isClient());
        }

        return ActionResult.PASS;
    }

    @Override
    public double getMountedHeightOffset() {
        return ((wheels.model().radius() + frame.model().seatHeight().getFloat() - 4) / 16);
    }

    private boolean pressingLeft;
    private boolean pressingRight;
    private boolean pressingForward;
    private boolean pressingBack;

    private float velocityDecay;
    private float yawVelocity;
    
    @Override
    public void updatePassengerPosition(Entity passenger, PositionUpdater updater) {
        if (!this.hasPassenger(passenger)) {
            return;
        }

        passenger.setYaw(passenger.getYaw() + this.yawVelocity);
        passenger.setHeadYaw(passenger.getHeadYaw() + this.yawVelocity);
        this.copyEntityData(passenger);

        if (Objects.equals(frame.getId(), id("truck"))) {
            Vec3d pos;
            if (Objects.equals(passenger, getFirstPassenger())) {
                pos = getPos().add(0.0, displacement.verticalTarget + passenger.getHeightOffset(), 0.0)
                        .add(new Vec3d(-0.5, getMountedHeightOffset() - 0.3, -0.1)
                                .rotateY((float) Math.toRadians((180.0f - getYaw())))
                                .rotateX((float) Math.toRadians((getPitch())))
                                .rotateX((float) Math.toRadians(-displacement.currAngularX))
                                .rotateZ((float) Math.toRadians(-displacement.currAngularZ))
                        );
                passenger.setPosition(pos.x, pos.y, pos.z);
            } else if (getPassengerList().size() >= 2 && passenger == getPassengerList().get(1)) {
                pos = getPos().add(0.0, displacement.verticalTarget + passenger.getHeightOffset(), 0.0)
                        .add(new Vec3d(0.5, getMountedHeightOffset() - 0.3, -0.1)
                                .rotateY((float) Math.toRadians((180.0f - getYaw())))
                                .rotateX((float) Math.toRadians(getPitch()))
                                .rotateX((float) Math.toRadians(-displacement.currAngularX))
                                .rotateZ((float) Math.toRadians(-displacement.currAngularZ))
                        );
                passenger.setPosition(pos.x, pos.y, pos.z);
            } else if (getPassengerList().size() >= 3 && passenger == getPassengerList().get(2)) {
                pos = getPos().add(0.0, displacement.verticalTarget + passenger.getHeightOffset(), 0.0)
                        .add(new Vec3d(-0.25, getMountedHeightOffset() - 0.3, 0.8)
                                .rotateY((float) Math.toRadians((180.0f - getYaw())))
                                .rotateX((float) Math.toRadians((getPitch())))
                                .rotateX((float) Math.toRadians(-displacement.currAngularX))
                                .rotateZ((float) Math.toRadians(-displacement.currAngularZ))
                        );
                passenger.setPosition(pos.x, pos.y, pos.z);
            } else if (hasPassenger(passenger)) {
                pos = getPos().add(
                        new Vec3d(0.0, displacement.verticalTarget, getFrame().model().rearAttachmentPos().getFloat() * 0.0625)
                                .rotateY((float) Math.toRadians((180.0f - getYaw())))
                                .add(0.0, rearAttachment.getPassengerHeightOffset() + passenger.getHeightOffset() - 0.14, 0.0)
                                .add(rearAttachment.scaledYawVec())
                                .rotateX((float) Math.toRadians(-displacement.currAngularX))
                                .rotateZ((float) Math.toRadians(-displacement.currAngularZ))
                );
                passenger.setPosition(pos.x, pos.y, pos.z);
            }
            return;
        }
        if (Objects.equals(frame.getId(), id("rusty_car"))) {
            Vec3d pos;
            if (Objects.equals(passenger, getFirstPassenger())) {
                pos = getPos().add(0.0, displacement.verticalTarget + passenger.getHeightOffset(), 0.0)
                        .add(new Vec3d(-0.6, getMountedHeightOffset(), -0.4)
                                .rotateY((float) Math.toRadians((180.0f - getYaw())))
                                .rotateX((float) Math.toRadians((getPitch())))
                                .rotateX((float) Math.toRadians(-displacement.currAngularX))
                                .rotateZ((float) Math.toRadians(-displacement.currAngularZ))
                        );
                passenger.setPosition(pos.x, pos.y, pos.z);
            } else if (getPassengerList().size() >= 2 && passenger == getPassengerList().get(1)) {
                pos = getPos().add(0.0, displacement.verticalTarget + passenger.getHeightOffset(), 0.0)
                        .add(new Vec3d(0.6, getMountedHeightOffset(), -0.4)
                                .rotateY((float) Math.toRadians((180.0f - getYaw())))
                                .rotateX((float) Math.toRadians(getPitch()))
                                .rotateX((float) Math.toRadians(-displacement.currAngularX))
                                .rotateZ((float) Math.toRadians(-displacement.currAngularZ))
                        );
                passenger.setPosition(pos.x, pos.y, pos.z);
            } else if (getPassengerList().size() >= 3 && passenger == getPassengerList().get(2)) {
                pos = getPos().add(0.0, displacement.verticalTarget + passenger.getHeightOffset(), 0.0)
                        .add(new Vec3d(0.6, getMountedHeightOffset(), 0.8)
                                .rotateY((float) Math.toRadians((180.0f - getYaw())))
                                .rotateX((float) Math.toRadians((getPitch())))
                                .rotateX((float) Math.toRadians(-displacement.currAngularX))
                                .rotateZ((float) Math.toRadians(-displacement.currAngularZ))
                        );
                passenger.setPosition(pos.x, pos.y, pos.z);
            } else if (getPassengerList().size() >= 4 && passenger == getPassengerList().get(3)) {
                pos = getPos().add(0.0, displacement.verticalTarget + passenger.getHeightOffset(), 0.0)
                        .add(new Vec3d(-0.6, getMountedHeightOffset(), 0.8)
                                .rotateY((float) Math.toRadians((180.0f - getYaw())))
                                .rotateX((float) Math.toRadians((getPitch())))
                                .rotateX((float) Math.toRadians(-displacement.currAngularX))
                                .rotateZ((float) Math.toRadians(-displacement.currAngularZ))
                        );
                passenger.setPosition(pos.x, pos.y, pos.z);
            } else if (hasPassenger(passenger)) {
                pos = getPos().add(
                        new Vec3d(0.0, displacement.verticalTarget, getFrame().model().rearAttachmentPos().getFloat() * 0.0625)
                                .rotateY((float) Math.toRadians((180.0f - getYaw())))
                                .add(0.0, rearAttachment.getPassengerHeightOffset() + passenger.getHeightOffset() - 0.14, 0.0)
                                .add(rearAttachment.scaledYawVec())
                                .rotateX((float) Math.toRadians(-displacement.currAngularX))
                                .rotateZ((float) Math.toRadians(-displacement.currAngularZ))
                );
                passenger.setPosition(pos.x, pos.y, pos.z);
            }
            return;
        }
        if (passenger == this.getFirstPassenger()) {
            var pos = this.getPos().add(0, this.displacement.verticalTarget + passenger.getHeightOffset(), 0)
                    .add(new Vec3d(0, this.getMountedHeightOffset(), 0)
                            .rotateX((float) Math.toRadians(-this.displacement.currAngularX))
                            .rotateZ((float) Math.toRadians(-this.displacement.currAngularZ)));

            passenger.setPosition(pos.x, pos.y, pos.z);
        } else if (this.hasPassenger(passenger)) {
            var pos = this.getPos().add(
                    new Vec3d(0, this.displacement.verticalTarget, this.getFrame().model().rearAttachmentPos().getFloat() * 0.0625)
                            .rotateY((float) Math.toRadians(180 - this.getYaw())).add(0, this.rearAttachment.getPassengerHeightOffset() + passenger.getHeightOffset() - 0.14, 0)
                            .add(this.rearAttachment.scaledYawVec())
                            .rotateX((float) Math.toRadians(-this.displacement.currAngularX))
                            .rotateZ((float) Math.toRadians(-this.displacement.currAngularZ)));

            passenger.setPosition(pos.x, pos.y, pos.z);
        }
    }

    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        double e;
        Vec3d vec3d = BoatEntity.getPassengerDismountOffset(this.getWidth() * MathHelper.SQUARE_ROOT_OF_TWO, passenger.getWidth(), passenger.getYaw());
        double d = this.getX() + vec3d.x;
        BlockPos blockPos = new BlockPos((int) d, (int) this.getBoundingBox().maxY, (int) (e = this.getZ() + vec3d.z));
        BlockPos blockPos2 = blockPos.down();
        double g;
        ArrayList<Vec3d> list = Lists.newArrayList();
        double f = this.getWorld().getDismountHeight(blockPos);
        if (Dismounting.canDismountInBlock(f)) {
            list.add(new Vec3d(d, (double)blockPos.getY() + f, e));
        }
        if (Dismounting.canDismountInBlock(g = this.getWorld().getDismountHeight(blockPos2))) {
            list.add(new Vec3d(d, (double)blockPos2.getY() + g, e));
        }
        for (EntityPose entityPose : passenger.getPoses()) {
            for (Vec3d vec3d2 : list) {
                if (!Dismounting.canPlaceEntityAt(this.getWorld(), vec3d2, passenger, entityPose)) continue;
                passenger.setPose(entityPose);
                return vec3d2;
            }
        }
        return super.updatePassengerForDismount(passenger);
    }

    @Override
    public boolean collidesWith(Entity other) {
        return BoatEntity.canCollide(this, other);
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean canHit() {
        return !this.isRemoved();
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(REAR_ATTACHMENT_YAW, 0f);
        this.dataTracker.startTracking(REAR_ATTACHMENT_ANIMATION, 0f);
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        super.onTrackedDataSet(data);

        if (REAR_ATTACHMENT_YAW.equals(data)) {
            this.rearAttachment.onTrackedYawUpdated(getTrackedRearAttachmentYaw());
        } else if (REAR_ATTACHMENT_ANIMATION.equals(data)) {
            this.rearAttachment.onTrackedAnimationUpdated(getTrackedRearAttachmentAnimation());
        } else if (FRONT_ATTACHMENT_ANIMATION.equals(data)) {
            this.frontAttachment.onTrackedAnimationUpdated(getTrackedFrontAttachmentAnimation());
        }
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    public void setTrackedRearAttachmentYaw(float value) {
        this.dataTracker.set(REAR_ATTACHMENT_YAW, value);
    }

    public float getTrackedRearAttachmentYaw() {
        return this.dataTracker.get(REAR_ATTACHMENT_YAW);
    }

    public void setTrackedRearAttachmentAnimation(float animation) {
        this.dataTracker.set(REAR_ATTACHMENT_ANIMATION, animation);
    }

    public float getTrackedRearAttachmentAnimation() {
        return this.dataTracker.get(REAR_ATTACHMENT_ANIMATION);
    }

    public void setTrackedFrontAttachmentAnimation(float animation) {
        this.dataTracker.set(FRONT_ATTACHMENT_ANIMATION, animation);
    }

    public float getTrackedFrontAttachmentAnimation() {
        return this.dataTracker.get(FRONT_ATTACHMENT_ANIMATION);
    }

    public void bounce() {
        suspensionBounceTimer = 3;
        getWorld().playSound(this.getX(), this.getY(), this.getZ(), MotoboxSounds.LANDING, SoundCategory.AMBIENT, 1, 1.5f + (0.15f * (this.getWorld().random.nextFloat() - 0.5f)), true);
    }

    @SuppressWarnings("ConstantValue")
    public static final class Displacement {
        private static final int SCAN_STEPS_PER_BLOCK = 20;
        private static final double INV_SCAN_STEPS = 1d / SCAN_STEPS_PER_BLOCK;

        private boolean wereAllOnGround = true;
        private float lastVertical = 0;
        private float lastAngularX = 0;
        private float lastAngularZ = 0;
        private float currAngularX = 0;
        private float currAngularZ = 0;
        private float verticalTarget = 0;
        private float angularXTarget = 0;
        private float angularZTarget = 0;
        private final List<Vec3d> scanPoints = new ArrayList<>();

        public void preTick() {
            this.lastAngularX = currAngularX;
            this.lastAngularZ = currAngularZ;
            this.lastVertical = verticalTarget;

            this.currAngularX = AUtils.shift(this.currAngularX, 9, this.angularXTarget);
            this.currAngularZ = AUtils.shift(this.currAngularZ, 9, this.angularZTarget);
        }

        public void tick(World world, VehicleEntity entity, Vec3d centerPos, double yaw, double stepHeight) {
            yaw = 360 - yaw;
            Vec3d lowestDisplacementPos = null;
            Vec3d highestDisplacementPos = null;
            var scannedPoints = new ArrayList<Vec3d>();
            var collBoxes = new HashSet<Box>();
            boolean anyOnGround = false;
            boolean allOnGround = true;
            for (var scanPoint : scanPoints) {
                scanPoint = scanPoint
                        .rotateY((float) Math.toRadians(yaw));
                var pointPos = scanPoint.add(centerPos);
                collBoxes.clear();

                double scanDist = scanPoint.length();

                int heightOffset = (int) Math.ceil(scanDist);
                var iter = new CuboidBlockIterator(
                        Math.min(MathHelper.floor(centerPos.x), MathHelper.floor(pointPos.x)),
                        MathHelper.floor(centerPos.y) - heightOffset,
                        Math.min(MathHelper.floor(centerPos.z), MathHelper.floor(pointPos.z)),
                        Math.max(MathHelper.floor(centerPos.x), MathHelper.floor(pointPos.x)),
                        MathHelper.floor(centerPos.y) + heightOffset,
                        Math.max(MathHelper.floor(centerPos.z), MathHelper.floor(pointPos.z))
                );

                var mpos = new BlockPos.Mutable();
                while (iter.step()) {
                    mpos.set(iter.getX(), iter.getY(), iter.getZ());
                    var shape = world.getBlockState(mpos).getCollisionShape(world, mpos);
                    if (!shape.isEmpty()) {
                        if (shape == VoxelShapes.fullCube()) {
                            collBoxes.add(new Box(mpos.getX(), mpos.getY(), mpos.getZ(), mpos.getX() + 1, mpos.getY() + 1, mpos.getZ() + 1));
                        } else {
                            shape.offset(mpos.getX(), mpos.getY(), mpos.getZ()).forEachBox(((minX, minY, minZ, maxX, maxY, maxZ) ->
                                    collBoxes.add(new Box(minX, minY, minZ, maxX, maxY, maxZ))));
                        }
                    }
                }

                var pointDir = new Vec3d(scanPoint.x, 0, scanPoint.z).normalize().multiply(INV_SCAN_STEPS);

                double pointY = centerPos.y;
                for (int i = 0; i < Math.ceil(scanDist * SCAN_STEPS_PER_BLOCK); i++) {
                    double pointX = centerPos.x + (i * pointDir.x);
                    double pointZ = centerPos.z + (i * pointDir.z);
                    pointY -= INV_SCAN_STEPS * 1.5;

                    boolean ground = false;
                    for (var box : collBoxes) {
                        if (pointX > box.minX && pointX < box.maxX &&
                                pointZ > box.minZ && pointZ < box.maxZ &&
                                pointY >= (box.minY - (INV_SCAN_STEPS * 2)) && pointY <= box.maxY
                        ) {
                            double diff = box.maxY - pointY;
                            if (diff < (stepHeight + (INV_SCAN_STEPS * 1.5))) {
                                pointY = box.maxY;
                                ground = true;
                            }
                        }
                    }
                    if (ground) {
                        anyOnGround = true;
                    } else {
                        allOnGround = false;
                    }
                }

                pointPos = new Vec3d(pointPos.x, pointY, pointPos.z);

                if (lowestDisplacementPos == null || pointPos.y < lowestDisplacementPos.y) {
                    lowestDisplacementPos = pointPos;
                }
                if (highestDisplacementPos == null || pointPos.y > highestDisplacementPos.y) {
                    highestDisplacementPos = pointPos;
                }

                scannedPoints.add(pointPos);
            }

            if (allOnGround && !wereAllOnGround) {
                entity.bounce();
            }
            wereAllOnGround = allOnGround;

            if (!anyOnGround) {
                return;
            }

            angularXTarget = 0;
            angularZTarget = 0;
            verticalTarget = 0;

            if (lowestDisplacementPos != null) {
                var displacementCenterPos = new Vec3d(centerPos.x, (lowestDisplacementPos.y + highestDisplacementPos.y) * 0.5, centerPos.z);

                var combinedNormals = Vec3d.ZERO;
                int normalCount = 0;
                Vec3d positiveXOffset = null;
                Vec3d negativeXOffset = null;
                Vec3d positiveZOffset = null;
                Vec3d negativeZOffset = null;

                for (var pointPos : scannedPoints) {
                    var pointOffset = pointPos.subtract(displacementCenterPos);
                    if (pointOffset.x > 0) {
                        if (positiveXOffset != null) {
                            var normal = positiveXOffset.crossProduct(pointOffset).normalize();
                            if (normal.y < 0) normal = normal.negate();
                            combinedNormals = combinedNormals.add(normal);
                            normalCount++;
                            positiveXOffset = null;
                        } else positiveXOffset = pointOffset;
                    } else if (pointOffset.x < 0) {
                        if (negativeXOffset != null) {
                            var normal = negativeXOffset.crossProduct(pointOffset).normalize();
                            if (normal.y < 0) normal = normal.negate();
                            combinedNormals = combinedNormals.add(normal);
                            normalCount++;
                            negativeXOffset = null;
                        } else negativeXOffset = pointOffset;
                    } else if (pointOffset.z > 0) {
                        if (positiveZOffset != null) {
                            var normal = positiveZOffset.crossProduct(pointOffset).normalize();
                            if (normal.y < 0) normal = normal.negate();
                            combinedNormals = combinedNormals.add(normal);
                            normalCount++;
                            positiveZOffset = null;
                        } else positiveZOffset = pointOffset;
                    } else if (pointOffset.z < 0) {
                        if (negativeZOffset != null) {
                            var normal = negativeZOffset.crossProduct(pointOffset).normalize();
                            if (normal.y < 0) normal = normal.negate();
                            combinedNormals = combinedNormals.add(normal);
                            normalCount++;
                            negativeZOffset = null;
                        } else negativeZOffset = pointOffset;
                    }
                }

                combinedNormals = normalCount > 0 ? combinedNormals.multiply(1f / normalCount) : new Vec3d(0, 1, 0);

                angularXTarget = MathHelper.wrapDegrees(90f - (float) Math.toDegrees(Math.atan2(combinedNormals.y, combinedNormals.z)));
                angularZTarget = MathHelper.wrapDegrees(270f + (float) Math.toDegrees(Math.atan2(combinedNormals.y, combinedNormals.x)));

                verticalTarget = (float) displacementCenterPos.subtract(centerPos).y;
            }
        }

        public void applyWheelbase(WheelBase wheelBase) {
            this.scanPoints.clear();
            for (WheelBase.WheelPos pos : wheelBase.wheels) {
                this.scanPoints.add(new Vec3d(pos.right() / 16, 0, pos.forward() / 16));
            }
        }

        public float getVertical(float tickDelta) {
            return MathHelper.lerp(tickDelta, lastVertical, verticalTarget);
        }

        public float getAngularX(float tickDelta) {
            return MathHelper.lerpAngleDegrees(tickDelta, lastAngularX, currAngularX);
        }

        public float getAngularZ(float tickDelta) {
            return MathHelper.lerpAngleDegrees(tickDelta, lastAngularZ, currAngularZ);
        }
    }
}
