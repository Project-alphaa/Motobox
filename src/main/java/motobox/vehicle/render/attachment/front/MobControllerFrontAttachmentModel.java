package motobox.vehicle.render.attachment.front;

import motobox.Motobox;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class MobControllerFrontAttachmentModel extends FrontAttachmentRenderModel {
    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("vehicle/front_attachment/mob_controller"), "main");

    public MobControllerFrontAttachmentModel(EntityRendererFactory.Context ctx) {
        super(RenderLayer::getEntityCutout, ctx, MODEL_LAYER);
    }
}
