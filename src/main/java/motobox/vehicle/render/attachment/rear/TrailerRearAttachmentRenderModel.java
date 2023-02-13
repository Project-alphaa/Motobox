package motobox.vehicle.render.attachment.rear;// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import motobox.Motobox;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class TrailerRearAttachmentRenderModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("vehicle/rear_attachment/trailer"), "main");
    private final ModelPart bb_main;

    public TrailerRearAttachmentRenderModel(EntityRendererFactory.Context root) {
        this.bb_main = root.getPart(MODEL_LAYER).getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        ModelPartData bb_main = root.addChild("bb_main", ModelPartBuilder.create().uv(0, 42).cuboid(-3.0F, -9.0F, -16.0F, 27.0F, 2.0F, 40.0F, new Dilation(0.0F))
                .uv(16, 42).cuboid(-3.0F, -20.0F, 22.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F))
                .uv(8, 42).cuboid(-3.0F, -20.0F, -16.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 42).cuboid(22.0F, -20.0F, -16.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F))
                .uv(31, 8).cuboid(22.0F, -20.0F, 22.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 58).cuboid(-2.5F, -19.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 56).cuboid(-2.5F, -16.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 54).cuboid(-2.5F, -13.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 61).cuboid(-2.5F, -10.0F, -14.25F, 1.0F, 1.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 52).cuboid(22.5F, -13.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 50).cuboid(22.5F, -16.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 48).cuboid(22.5F, -19.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 60).cuboid(22.5F, -10.0F, -14.25F, 1.0F, 1.0F, 36.0F, new Dilation(0.0F))
                .uv(94, 10).cuboid(-1.25F, -19.0F, -15.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 8).cuboid(-1.25F, -16.0F, -15.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 6).cuboid(-1.25F, -13.0F, -15.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 13).cuboid(-1.25F, -10.0F, -15.5F, 23.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 4).cuboid(-1.25F, -13.0F, 22.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 2).cuboid(-1.25F, -19.0F, 22.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 0).cuboid(-1.25F, -16.0F, 22.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 12).cuboid(-1.25F, -10.0F, 22.5F, 23.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(9.0F, -8.0F, 23.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(8.0F, -6.0F, 25.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(24.0F, -10.0F, 0.0F, 3.0F, 2.0F, 9.0F, new Dilation(0.0F))
                .uv(7, 3).cuboid(-6.0F, -10.0F, 0.0F, 3.0F, 2.0F, 9.0F, new Dilation(0.0F))
                .uv(0, 8).cuboid(9.0F, -9.0F, -18.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(9.0F, -8.0F, -30.0F, 2.0F, 0.0F, 12.0F, new Dilation(0.0F))
                .uv(56, 84).cuboid(3.0F, -24.0F, -1.0F, 16.0F, 15.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(15, 14).cuboid(-1.25F, 3.25F, -1.5F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 25).cuboid(28.7F, 3.25F, -1.5F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-4.6F, -9.0F, -5.5F, 0.7854F, 0.0F, 0.0F));

        bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(22, 0).cuboid(-1.25F, 3.25F, -4.5F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(18, 22).cuboid(28.7F, 3.25F, -4.5F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-4.6F, -9.0F, 14.5F, -0.7854F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 256, 256);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.push();
        poseStack.translate(-5.0 / 6.0, (-27.0 / 16.0), 30.0 / 16.0);
        poseStack.scale(1.2f, 1.2f, 1.2f);

        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.pop();
    }
}