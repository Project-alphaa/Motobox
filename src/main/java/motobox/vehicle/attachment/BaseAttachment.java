package motobox.vehicle.attachment;

import motobox.block.MotoboxBlocks;
import motobox.entity.VehicleEntity;
import motobox.vehicle.VehicleComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class BaseAttachment<T extends VehicleComponent<T>> {
    public final T type;
    protected final VehicleEntity vehicle;

    private float animation;

    public BaseAttachment(T type, VehicleEntity vehicle) {
        this.type = type;
        this.vehicle = vehicle;
    }

    public final VehicleEntity vehicle() {
        return this.vehicle;
    }

    protected final World world() {
        return this.vehicle.getWorld();
    }

    public abstract Vec3d pos();

    public float animation() {
        return animation;
    }

    public void setAnimation(float animation) {
        this.animation = animation;
    }

    protected abstract void updateTrackedAnimation(float animation);

    public void onTrackedAnimationUpdated(float animation) {
        this.setAnimation(animation);
    }

    public void tick() {
    }

    public void onRemoved() {
    }

    public abstract void writeNbt(NbtCompound nbt);

    public abstract void readNbt(NbtCompound nbt);

    public void updatePacketRequested(ServerPlayerEntity player) {
    }

    protected boolean canModifyBlocks() {
        if (this.vehicle.getFirstPassenger() instanceof PlayerEntity player && player.canModifyBlocks()) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            if (world().getBlockState(this.vehicle.getBlockPos().down(i)).isOf(MotoboxBlocks.ALLOW)) {
                return true;
            }
        }
        return false;
    }

    public final NbtCompound toNbt() {
        var nbt = new NbtCompound();
        nbt.putString("type", this.type.getId().toString());
        this.writeNbt(nbt);
        return nbt;
    }
}
