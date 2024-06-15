package motobox.entity.ufo;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.World;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class AbstractUfoEntity extends Entity implements GeoEntity {
    // Movement
    private final float velocityDecay;
    private int interpolationSteps;

    // Position
    private double x;
    private double y;
    private double z;

    // Direction
    private double pitch;
    private double yaw;

    public AbstractUfoEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
        this.intersectionChecked = true;
        this.velocityDecay = 0.9f;
    }

    public abstract float getThirdPersonCameraDistance();
    public abstract float getScale();
    public abstract Entity abduct();

    @Override
    public void tick() {
        super.tick();

        if (this.isLogicalSideForUpdatingMovement()) {
            this.updateInterpolatedPose();
            this.decayVelocity();
            if (this.getWorld().isClient) {
                this.applyInput();
            }
            this.move(MovementType.SELF, this.getVelocity());
        } else {
            this.setVelocity(Vec3d.ZERO);

            // TODO radius
            List<PlayerEntity> collisions = this.getWorld().getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().withMinY(this.getBoundingBox().maxY - 0.1), entity -> true);
            for (PlayerEntity player : collisions) {
                player.setPosition(player.getPos().getX(), this.getBoundingBox().getMax(Axis.Y), player.getPos().getZ());
                player.setVelocity(player.getVelocity().getX(), 0, player.getVelocity().getZ());
                player.setOnGround(true);
            }
        }

    }

    // Verified
    private void updateInterpolatedPose() {
        if (this.isLogicalSideForUpdatingMovement()) {
            this.updateTrackedPosition(this.getX(), this.getY(), this.getZ());
            this.interpolationSteps = 0;
        }
        if (this.interpolationSteps <= 0) return;

        // Calculate interpolated pose
        double interpolatedX = this.getX() + (this.x - this.getX()) / this.interpolationSteps;
        double interpolatedY = this.getY() + (this.y - this.getY()) / this.interpolationSteps;
        double interpolatedZ = this.getZ() + (this.z - this.getZ()) / this.interpolationSteps;
        float interpolatedPitch = (float) (this.getPitch() + (this.pitch - this.getPitch()) / this.interpolationSteps);
        float interpolatedYaw = (float) (this.getYaw() + MathHelper.wrapDegrees(this.yaw - this.getYaw()) / this.interpolationSteps);

        // Apply interpolated pose
        this.setPosition(interpolatedX, interpolatedY, interpolatedZ);
        this.setRotation(interpolatedYaw, interpolatedPitch);

        this.interpolationSteps--;
    }

    // Verified
    private void decayVelocity() {
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.multiply(this.velocityDecay));
    }

    // Verified
    private void applyInput() {
        if (!this.hasPassengers()) return;

        this.setYaw(-this.getControllingPassenger().getHeadYaw());

        float fockwardVelocity = 0.0f; // FOrward and baCKward = fockward
        if (UfoInput.pressingForward()) fockwardVelocity += 0.05F;
        if (UfoInput.pressingBackward()) fockwardVelocity -= 0.05F;

        float sidewaysVelocity = 0.0f;
        if (UfoInput.pressingRight()) sidewaysVelocity += 0.05F;
        if (UfoInput.pressingLeft()) sidewaysVelocity -= 0.05F;

        float verticalVelocity = 0.0f;
        if (UfoInput.pressingUp()) verticalVelocity += 0.05F;
        if (UfoInput.pressingDown()) verticalVelocity -= 0.05F;

        this.setVelocity(
                this.getVelocity().add(
                        MathHelper.sin(this.getYaw() * MathHelper.RADIANS_PER_DEGREE) * fockwardVelocity,
                        verticalVelocity,
                        MathHelper.cos(-this.getYaw() * MathHelper.RADIANS_PER_DEGREE) * fockwardVelocity
                ).add(
                        MathHelper.sin((this.getYaw() - 90) * MathHelper.RADIANS_PER_DEGREE) * sidewaysVelocity,
                        0,
                        MathHelper.cos((-this.getYaw() + 90) * MathHelper.RADIANS_PER_DEGREE) * sidewaysVelocity
                )
        );
    }

    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        return new Vec3d(passenger.getX(), this.getBoundingBox().maxY + 0.1, passenger.getZ());
    }

    @Override
    public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps, boolean interpolate) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.yaw = yaw;
        this.pitch = pitch;

        this.interpolationSteps = 10;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (player.shouldCancelInteraction()) {
            return ActionResult.PASS;
        }
        if (!this.getWorld().isClient) {
            return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
        }
        return ActionResult.SUCCESS;
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
    public double getMountedHeightOffset() {
        return 1.0;
    }

    @Override
    public LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() instanceof LivingEntity living ? living : null;
    }

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound var1) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound var1) {}

    /*
     * ANIMATION SIDE
     */

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private PlayState predicate(AnimationState<AbstractUfoEntity> event) {
        event.getController().setAnimation(RawAnimation.begin().thenPlay("ufo.spin"));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}