package motobox.mixin;

import motobox.entity.VehicleEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRendererMixin<T extends Entity> {

    @Inject(method = "renderShadow",  at = @At("HEAD"), cancellable = true)
    private static void renderShadow(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity, float opacity, float tickDelta, WorldView world, float radius, CallbackInfo ci) {
        if (entity.hasVehicle()) {
            Entity vehicle = entity.getVehicle();
            if (vehicle instanceof VehicleEntity) {
                ci.cancel();
            }
        }
    }
}
