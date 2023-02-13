package motobox.mixin.ufo;

import motobox.entity.ufo.UfoEntity;
import motobox.entity.ufo.UfoInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;

@Mixin(ClientPlayerEntity.class)
public abstract class InputHandler extends AbstractClientPlayerEntity {

    @Shadow private boolean riding;

    public InputHandler(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "tickRiding", at = @At("TAIL"))
    public void tickRiding(CallbackInfo info) {
        if (getVehicle() instanceof UfoEntity) {
            riding = UfoInput.receivingInput();
        }
    }
}