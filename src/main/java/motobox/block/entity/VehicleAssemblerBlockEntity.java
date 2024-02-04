package motobox.block.entity;

import motobox.block.MotoboxBlocks;
import motobox.block.VehicleAssemblerBlock;
import motobox.entity.VehicleEntity;
import motobox.item.MotoboxItems;
import motobox.item.VehicleEngineItem;
import motobox.item.VehicleFrameItem;
import motobox.item.VehicleWheelItem;
import motobox.vehicle.VehicleEngine;
import motobox.vehicle.VehicleFrame;
import motobox.vehicle.VehicleStats;
import motobox.vehicle.VehicleWheel;
import motobox.vehicle.attachment.FrontAttachmentType;
import motobox.vehicle.attachment.RearAttachmentType;
import motobox.vehicle.attachment.front.FrontAttachment;
import motobox.vehicle.attachment.rear.RearAttachment;
import motobox.vehicle.render.RenderableVehicle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleAssemblerBlockEntity extends BlockEntity implements RenderableVehicle {
    @Environment(EnvType.CLIENT)
    private Model frameModel = null;
    @Environment(EnvType.CLIENT)
    private Model engineModel = null;
    @Environment(EnvType.CLIENT)
    private Model wheelModel = null;
    @Environment(EnvType.CLIENT)
    private Model emptyRearAttModel = null;
    @Environment(EnvType.CLIENT)
    private Model emptyFrontAttModel = null;
    private boolean componentsUpdated = true;

    protected VehicleFrame frame = VehicleFrame.EMPTY;
    protected VehicleEngine engine = VehicleEngine.EMPTY;
    protected VehicleWheel wheel = VehicleWheel.EMPTY;
    protected int wheelCount = 0;

    public final List<Text> label = new ArrayList<>();
    protected final VehicleStats stats = new VehicleStats();

    public VehicleAssemblerBlockEntity(BlockPos pos, BlockState state) {
        super(MotoboxBlocks.VEHICLE_ASSEMBLER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public VehicleFrame getFrame() {
        return frame;
    }

    @Override
    public VehicleWheel getWheels() {
        return wheel;
    }

    @Override
    public VehicleEngine getEngine() {
        return engine;
    }

    @Override
    public @Nullable RearAttachment getRearAttachment() {
        return null;
    }

    @Override
    public @Nullable FrontAttachment getFrontAttachment() {
        return null;
    }

    @Override
    public Model getFrameModel(EntityRendererFactory.Context ctx) {
        if (this.componentsUpdated) this.frameModel = this.frame.model().model().apply(ctx);
        return this.frameModel;
    }

    @Override
    public Model getWheelModel(EntityRendererFactory.Context ctx) {
        if (this.componentsUpdated) this.wheelModel = this.wheel.model().model().apply(ctx);
        return this.wheelModel;
    }

    @Override
    public Model getEngineModel(EntityRendererFactory.Context ctx) {
        if (this.componentsUpdated) this.engineModel = this.engine.model().model().apply(ctx);
        return this.engineModel;
    }

    @Override
    public Model getRearAttachmentModel(EntityRendererFactory.Context ctx) {
        if (this.componentsUpdated) this.emptyRearAttModel = RearAttachmentType.EMPTY.model().model().apply(ctx);
        return this.emptyRearAttModel;
    }

    @Override
    public Model getFrontAttachmentModel(EntityRendererFactory.Context ctx) {
        if (this.componentsUpdated) this.emptyFrontAttModel = FrontAttachmentType.EMPTY.model().model().apply(ctx);
        return this.emptyFrontAttModel;
    }

    private void partChanged() {
        this.sync();
        this.markDirty();
        this.world.emitGameEvent(GameEvent.BLOCK_CHANGE, this.getPos(), GameEvent.Emitter.of(getCachedState()));
    }

    protected ActionResult handleItemInteract(PlayerEntity player, ItemStack stack) {
        // Returns success on the server since the client is never 100% confident that the action was valid
        // Subsequent handling is performed with the action result

        if (stack.isOf(MotoboxItems.HAMMER)) {
            if (!world.isClient()) {
                this.dropParts();
                this.partChanged();
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        }
        if (this.frame.isEmpty() && stack.getItem() instanceof VehicleFrameItem frameItem) {
            if (!world.isClient()) {
                this.frame = frameItem.getComponent(stack);
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                this.partChanged();
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        }
        if (!this.frame.isEmpty()) {
            if (this.engine.isEmpty() && stack.getItem() instanceof VehicleEngineItem engineItem) {
                if (!world.isClient()) {
                    this.engine = engineItem.getComponent(stack);
                    if (!player.isCreative()) {
                        stack.decrement(1);
                    }
                    this.partChanged();
                    return ActionResult.SUCCESS;
                }
                return ActionResult.PASS;
            }
            if (stack.getItem() instanceof VehicleWheelItem wheelItem) {
                if (!world.isClient()) {
                    var wheelType = wheelItem.getComponent(stack);
                    if (this.wheel.isEmpty()) {
                        this.wheel = wheelType;
                        this.wheelCount = 0; // Fix wheel count if ever invalid
                    }
                    if (this.wheel == wheelType) {
                        this.wheelCount++;
                        if (!player.isCreative()) {
                            stack.decrement(1);
                        }
                        this.partChanged();
                        return ActionResult.SUCCESS;
                    }
                } else {
                    return ActionResult.PASS;
                }
            }
        }
        if (!this.world.isClient() && stack.isOf(MotoboxItems.FRONT_ATTACHMENT) || stack.isOf(MotoboxItems.REAR_ATTACHMENT)) {
            player.sendMessage(VehicleAssemblerBlock.INCOMPLETE_VEHICLE_DIALOG, true);
        }

        return ActionResult.FAIL;
    }

    public ActionResult interact(PlayerEntity player, Hand hand) {
        var stack = player.getStackInHand(hand);
        var result = this.handleItemInteract(player, stack);

        if (!this.world.isClient() && result == ActionResult.SUCCESS) {
            if (!isComplete()) {
                world.playSound(null, this.pos, SoundEvents.BLOCK_COPPER_PLACE, SoundCategory.BLOCKS, 0.7f, 0.6f + (this.world.random.nextFloat() * 0.15f));
            }

            tryConstructVehicle();
            return ActionResult.SUCCESS;
        }
        return result;
    }

    protected Vec3d centerPos() {
        return new Vec3d(this.pos.getX() + 0.5, this.pos.getY() + 0.75, this.pos.getZ() + 0.5);
    }

    public boolean isComplete() {
        return !this.frame.isEmpty() &&
                !this.engine.isEmpty() &&
                ((!this.wheel.isEmpty()) && (this.wheelCount == this.frame.model().wheelBase().get().wheelCount));
    }

    public void tryConstructVehicle() {
        if (this.isComplete()) {
            var pos = this.centerPos();
            var vehicle = new VehicleEntity(this.world);
            vehicle.refreshPositionAndAngles(pos.x, pos.y, pos.z, this.getVehicleYaw(0), 0);
            vehicle.setComponents(this.frame, this.wheel, this.engine);
            world.spawnEntity(vehicle);

            world.getPlayers().forEach(p -> {
                if (p instanceof ServerPlayerEntity player && p.getBlockPos().getSquaredDistance(this.pos) < 80000) {
                    player.networkHandler.sendPacket(new ParticleS2CPacket(ParticleTypes.EXPLOSION, false, pos.x, pos.y + 0.47, pos.z, 0, 0, 0, 0, 1));
                }
            });
            world.playSound(null, this.pos, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 0.23f, 0.5f);

            this.clear();
        }
    }

    public void dropParts() {
        var pos = this.centerPos();

        this.world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, MotoboxItems.VEHICLE_FRAME.createStack(this.getFrame())));
        this.world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, MotoboxItems.VEHICLE_ENGINE.createStack(this.getEngine())));

        var wheelStack = MotoboxItems.VEHICLE_WHEEL.createStack(this.getWheels());
        wheelStack.setCount(this.wheelCount);
        this.world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, wheelStack));

        this.clear();
    }

    public void clear() {
        this.frame = VehicleFrame.EMPTY;
        this.wheel = VehicleWheel.EMPTY;
        this.engine = VehicleEngine.EMPTY;
        this.wheelCount = 0;
    }

    private boolean hasAllParts() {
        return !this.frame.isEmpty() && !this.wheel.isEmpty() && !this.engine.isEmpty();
    }

    private void onComponentsUpdated() {
        this.componentsUpdated = true;

        if (world == null || world.isClient()) {
            this.label.clear();
            if (this.hasAllParts()) {
                this.stats.from(this.frame, this.wheel, this.engine);
                this.stats.appendTexts(this.label, this.stats);
            }
        }
    }

    private void sync() {
        if (this.world instanceof ServerWorld sWorld) {
            sWorld.getChunkManager().markForUpdate(this.pos);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        this.frame = VehicleFrame.REGISTRY.getOrDefault(Identifier.tryParse(nbt.getString("frame")));
        this.engine = VehicleEngine.REGISTRY.getOrDefault(Identifier.tryParse(nbt.getString("engine")));

        var wheelNbt = nbt.getCompound("wheels");
        this.wheel = VehicleWheel.REGISTRY.getOrDefault(Identifier.tryParse(wheelNbt.getString("type")));
        this.wheelCount = wheelNbt.getInt("count");

        onComponentsUpdated();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        nbt.putString("frame", this.frame.getId().toString());
        nbt.putString("engine", this.engine.getId().toString());

        var wheelNbt = new NbtCompound();
        wheelNbt.putString("type", this.wheel.getId().toString());
        wheelNbt.putInt("count", this.wheelCount);
        nbt.put("wheels", wheelNbt);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        var nbt = new NbtCompound();
        this.writeNbt(nbt);
        return nbt;
    }

    protected boolean powered() {
        var state = this.world.getBlockState(this.pos);
        return state.contains(VehicleAssemblerBlock.POWERED) && state.get(VehicleAssemblerBlock.POWERED);
    }

    @Override
    public float getVehicleYaw(float tickDelta) {
        var state = this.world.getBlockState(this.pos);
        return state.contains(VehicleAssemblerBlock.FACING) ? (state.get(VehicleAssemblerBlock.FACING).asRotation() - 90) : 0;
    }

    @Override
    public int getWheelCount() {
        return this.wheelCount;
    }

    @Override
    public float getRearAttachmentYaw(float tickDelta) {
        return 0;
    }

    @Override
    public float getWheelAngle(float tickDelta) {
        return this.powered() ? (this.getTime() + tickDelta) * 36 : 0;
    }

    @Override
    public float getSteering(float tickDelta) {
        return 0;
    }

    @Override
    public float getSuspensionBounce(float tickDelta) {
        return 0;
    }

    @Override
    public boolean engineRunning() {
        return this.powered();
    }

    @Override
    public int getBoostTimer() {
        return this.powered() ? 1 : 0;
    }

    @Override
    public int getTurboCharge() {
        return 0;
    }

    @Override
    public long getTime() {
        return this.world.getTime();
    }

    @Override
    public boolean vehicleOnGround() {
        return false;
    }

    @Override
    public boolean debris() {
        return false;
    }

    @Override
    public Color debrisColor() {
        return null;
    }
}
