package motobox.mixin;

import motobox.entity.EntityWithInventory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "hasRidingInventory", at = @At("HEAD"), cancellable = true)
    private void motobox$allowCustomRidingInventories(CallbackInfoReturnable<Boolean> cir) {
        if (this.client.player != null && this.client.player.getVehicle() instanceof EntityWithInventory invEntity && invEntity.hasInventory()) {
            cir.setReturnValue(true);
        }
    }
}
