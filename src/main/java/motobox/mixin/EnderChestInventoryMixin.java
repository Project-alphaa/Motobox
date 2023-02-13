package motobox.mixin;

import motobox.util.duck.EnderChestInventoryDuck;
import motobox.vehicle.attachment.rear.BaseChestRearAttachment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderChestInventory.class)
public class EnderChestInventoryMixin implements EnderChestInventoryDuck {
    private @Nullable BaseChestRearAttachment motobox$activeAttachment = null;

    @Override
    public void motobox$setActiveAttachment(BaseChestRearAttachment attachment) {
        this.motobox$activeAttachment = attachment;
    }

    @Inject(method = "canPlayerUse", at = @At("HEAD"), cancellable = true)
    private void motobox$allowPlayerUseWithAttachment(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (this.motobox$activeAttachment != null) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "onOpen", at = @At("TAIL"))
    private void motobox$openActiveAttachment(PlayerEntity player, CallbackInfo ci) {
        if (this.motobox$activeAttachment != null) {
            this.motobox$activeAttachment.open(player);
        }
    }

    @Inject(method = "onClose", at = @At("TAIL"))
    private void motobox$closeActiveAttachment(PlayerEntity player, CallbackInfo ci) {
        if (this.motobox$activeAttachment != null) {
            this.motobox$activeAttachment.close(player);
        }
        this.motobox$activeAttachment = null;
    }
}
