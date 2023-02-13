package motobox.item;

import motobox.entity.VehicleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public interface VehicleInteractable {
    ActionResult interactVehicle(ItemStack stack, PlayerEntity player, Hand hand, VehicleEntity vehicle);
}
