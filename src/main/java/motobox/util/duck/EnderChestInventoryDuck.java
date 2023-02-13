package motobox.util.duck;

import motobox.vehicle.attachment.rear.BaseChestRearAttachment;
import net.minecraft.inventory.EnderChestInventory;

public interface EnderChestInventoryDuck {
    void motobox$setActiveAttachment(BaseChestRearAttachment attachment);

    static EnderChestInventoryDuck of(EnderChestInventory inv) {
        return (EnderChestInventoryDuck) inv;
    }
}
