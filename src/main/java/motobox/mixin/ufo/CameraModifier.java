package motobox.mixin.ufo;

import motobox.entity.ufo.UfoEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;

@Mixin(Camera.class)
public abstract class CameraModifier {

    @Shadow protected abstract double clipToSpace(double desiredCameraDistance);
    @Shadow protected abstract void moveBy(double x, double y, double z);

    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setPos(DDD)V", shift = At.Shift.AFTER), cancellable = true)
    public void update(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo info) {
        if (focusedEntity.getVehicle() instanceof UfoEntity ufo) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            minecraftClient.options.setPerspective(Perspective.THIRD_PERSON_BACK);

            float cameraDistance = ufo.getThirdPersonCameraDistance();

            moveBy(-clipToSpace(cameraDistance), 0.0, 0.0);
            info.cancel();
        }
    }
}