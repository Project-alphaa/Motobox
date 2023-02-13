package motobox.mixin;

import motobox.entity.VehicleEntity;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onClientCommand", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;updateLastActionTime()V", shift = At.Shift.AFTER), cancellable = true)
    private void motobox$openVehicleInventory(ClientCommandC2SPacket packet, CallbackInfo ci) {
        var vehicle = this.player.getVehicle();
        if (packet.getMode() == ClientCommandC2SPacket.Mode.OPEN_INVENTORY && vehicle instanceof VehicleEntity vehicleEntity) {
            if (vehicleEntity.hasInventory()) {
                vehicleEntity.openInventory(this.player);
                ci.cancel();
            }
        }
    }
}
