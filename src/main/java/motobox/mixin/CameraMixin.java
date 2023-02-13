package motobox.mixin;

import motobox.entity.VehicleEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow protected abstract double clipToSpace(double desiredCameraDistance);

    @Shadow private Entity focusedEntity;

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;clipToSpace(D)D"), method = "update")
    public double motobox$changeThirdsPersonDistance(Camera instance, double desiredCameraDistance) {
        if (focusedEntity.getVehicle() instanceof VehicleEntity) {
            return clipToSpace(desiredCameraDistance * 2);
        }
        return clipToSpace(desiredCameraDistance);
    }
}
