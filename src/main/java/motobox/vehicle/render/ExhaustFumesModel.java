package motobox.vehicle.render;

import motobox.Motobox;
import motobox.entity.VehicleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ExhaustFumesModel<T extends VehicleEntity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("vehicle_exhaust_fumes"), "main");

    public static final Identifier[] SMOKE_TEXTURES = new Identifier[]{
            Motobox.id("textures/entity/vehicle/exhaust/exhaust_smoke_0.png"),
            Motobox.id("textures/entity/vehicle/exhaust/exhaust_smoke_1.png"),
            Motobox.id("textures/entity/vehicle/exhaust/exhaust_smoke_2.png"),
            Motobox.id("textures/entity/vehicle/exhaust/exhaust_smoke_3.png")
    };
    public static final Identifier[] FLAME_TEXTURES = new Identifier[]{
            Motobox.id("textures/entity/vehicle/exhaust/exhaust_flames_0.png"),
            Motobox.id("textures/entity/vehicle/exhaust/exhaust_flames_1.png"),
            Motobox.id("textures/entity/vehicle/exhaust/exhaust_flames_2.png"),
            Motobox.id("textures/entity/vehicle/exhaust/exhaust_flames_3.png")
    };
    private final ModelPart main;

    public ExhaustFumesModel(EntityRendererFactory.Context ctx) {
        super(RenderLayer::getEntityCutout);

        main = ctx.getPart(MODEL_LAYER).getChild("main");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData main = partdefinition.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        return TexturedModelData.of(meshdefinition, 16, 16);
    }

    @Override
    public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}
