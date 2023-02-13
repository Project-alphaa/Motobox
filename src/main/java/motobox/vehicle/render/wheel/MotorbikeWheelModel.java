package motobox.vehicle.render.wheel;

import motobox.Motobox;
import motobox.entity.VehicleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Quaternionf;

/**
 * Made with Blockbench 4.5.2
 * Exported for Minecraft version 1.17+ for Yarn
 * Paste this class into your mod and generate all required imports
 */
public class MotorbikeWheelModel extends EntityModel<VehicleEntity> {
	private final ModelPart wheel;
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("wheel_motorbike"), "main");

	public MotorbikeWheelModel(EntityRendererFactory.Context context) {
		this.wheel = context.getPart(MODEL_LAYER).getChild("wheel");
	}

	@SuppressWarnings("unused")
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData wheel = modelPartData.addChild("wheel", ModelPartBuilder.create().uv(20, 20).cuboid(-1.0F, -10.0F, -1.475F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(8, 0).cuboid(-1.0F, -6.0F, -1.475F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(20, 12).cuboid(-1.0F, -5.9727F, 2.5523F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(14, 10).cuboid(-1.0F, -5.9727F, -5.5023F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 12).cuboid(-1.0F, -1.9453F, -1.475F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 24).cuboid(-0.4F, -8.9453F, -0.975F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 28.9453F, -0.525F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r1 = wheel.addChild("cube_r1", ModelPartBuilder.create().uv(12, 24).cuboid(-0.5F, -6.5F, -0.5F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.6953F, 1.525F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r2 = wheel.addChild("cube_r2", ModelPartBuilder.create().uv(4, 24).cuboid(-0.5F, -3.5F, 0.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.1F, -5.4453F, -0.475F, -1.5708F, 0.0F, 0.0F));

		ModelPartData cube_r3 = wheel.addChild("cube_r3", ModelPartBuilder.create().uv(8, 12).cuboid(-1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.025F, -1.7422F, -5.6609F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r4 = wheel.addChild("cube_r4", ModelPartBuilder.create().uv(14, 2).cuboid(-1.0F, -2.0F, 7.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.2391F, -8.2725F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r5 = wheel.addChild("cube_r5", ModelPartBuilder.create().uv(14, 6).cuboid(-1.0F, -5.0F, 7.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.025F, 7.6699F, -1.3821F, 1.1781F, 0.0F, 0.0F));

		ModelPartData cube_r6 = wheel.addChild("cube_r6", ModelPartBuilder.create().uv(14, 14).cuboid(-1.0F, -5.0F, 1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.025F, -4.2326F, -2.3332F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r7 = wheel.addChild("cube_r7", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, -2.0F, 7.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.4565F, -8.2725F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r8 = wheel.addChild("cube_r8", ModelPartBuilder.create().uv(8, 16).cuboid(-1.0F, -5.0F, 7.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.025F, 2.4079F, -3.5617F, 1.1781F, 0.0F, 0.0F));

		ModelPartData cube_r9 = wheel.addChild("cube_r9", ModelPartBuilder.create().uv(20, 0).cuboid(-1.0F, -5.0F, 7.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.025F, 5.4903F, 3.8798F, 1.1781F, 0.0F, 0.0F));

		ModelPartData cube_r10 = wheel.addChild("cube_r10", ModelPartBuilder.create().uv(20, 8).cuboid(-1.0F, -2.0F, 7.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.4565F, -2.577F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r11 = wheel.addChild("cube_r11", ModelPartBuilder.create().uv(0, 20).cuboid(-1.0F, -5.0F, 1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.025F, -2.053F, 2.9288F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r12 = wheel.addChild("cube_r12", ModelPartBuilder.create().uv(14, 18).cuboid(-1.025F, -5.0F, 7.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.2283F, 1.7002F, 1.1781F, 0.0F, 0.0F));

		ModelPartData cube_r13 = wheel.addChild("cube_r13", ModelPartBuilder.create().uv(20, 4).cuboid(-1.0F, -2.0F, 7.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.2391F, -2.577F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r14 = wheel.addChild("cube_r14", ModelPartBuilder.create().uv(20, 16).cuboid(-1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.025F, 0.4374F, -0.3989F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r15 = wheel.addChild("cube_r15", ModelPartBuilder.create().uv(8, 24).cuboid(-0.5F, -3.5F, -3.5F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.6953F, 1.525F, -0.7854F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(VehicleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrices.push();
		matrices.scale(1.15f, 1.15f, 1.15f);
		matrices.translate(-1 / 16.0, -24 / 16.0, -0.5 / 16.0);
		matrices.multiply(new Quaternionf().rotateY((float) Math.toRadians(270)));
		matrices.multiply(new Quaternionf().rotateZ((float) Math.toRadians(0)));

		wheel.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);

		matrices.pop();
	}
}