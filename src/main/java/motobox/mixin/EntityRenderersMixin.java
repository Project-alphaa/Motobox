package motobox.mixin;

import motobox.util.EntityRenderHelper;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(EntityRenderers.class)
public class EntityRenderersMixin {
    @Inject(method = "reloadEntityRenderers", at = @At("HEAD"))
    private static void motobox$reloadContextListeners(EntityRendererFactory.Context ctx, CallbackInfoReturnable<Map<EntityType<?>, EntityRenderer<?>>> cir) {
        EntityRenderHelper.reload(ctx);
    }
}
