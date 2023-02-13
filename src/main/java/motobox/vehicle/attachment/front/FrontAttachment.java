package motobox.vehicle.attachment.front;

import motobox.entity.VehicleEntity;
import motobox.util.AUtils;
import motobox.vehicle.attachment.BaseAttachment;
import motobox.vehicle.attachment.FrontAttachmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public abstract class FrontAttachment extends BaseAttachment<FrontAttachmentType<?>> {
    public FrontAttachment(FrontAttachmentType<?> type, VehicleEntity vehicle) {
        super(type, vehicle);
    }

    @Override
    public Vec3d pos() {
        return this.vehicle.getHeadPos();
    }

    @Override
    protected void updateTrackedAnimation(float animation) {
        this.vehicle.setTrackedFrontAttachmentAnimation(animation);
    }

    public boolean canDrive(Entity entity) {
        return entity instanceof PlayerEntity;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
    }

    @Override
    public void readNbt(NbtCompound nbt) {
    }

    public void dropOrTransfer(ItemStack stack, Vec3d dropPos) {
        var rearAtt = this.vehicle.getRearAttachment();
        boolean drop = true;
        if (rearAtt instanceof Inventory inv) {
            if (AUtils.transferInto(stack, inv)) {
                drop = false;
            }
        }
        if (drop) {
            world().spawnEntity(new ItemEntity(world(), dropPos.x, dropPos.y, dropPos.z, stack));
        }
    }

    public static FrontAttachmentType<?> fromNbt(NbtCompound nbt) {
        return FrontAttachmentType.REGISTRY.get(Identifier.tryParse(nbt.getString("type")));
    }
}
