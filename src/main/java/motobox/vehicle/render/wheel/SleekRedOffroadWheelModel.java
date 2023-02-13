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
 * Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
 * Paste this class into your mod and generate all required imports
 */
public class SleekRedOffroadWheelModel<T extends VehicleEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("wheel_sleek_red_offroad"), "main");

	private final ModelPart bone2;
	private final ModelPart bone3;
	private final ModelPart bone4;
	private final ModelPart bone;

	public SleekRedOffroadWheelModel(EntityRendererFactory.Context context) {
		ModelPart root = context.getPart(MODEL_LAYER);
		this.bone2 = root.getChild("bone2");
		this.bone3 = root.getChild("bone3");
		this.bone4 = root.getChild("bone4");
		this.bone = root.getChild("bone");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		ModelPartData bone2 = root.addChild("bone2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.05F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		bone2.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0852F, 0.1831F, 0.0F, 0.0F, 0.0F, -0.3927F));

		bone2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.1574F, 0.1348F, 0.0F, 0.0F, 0.0F, -0.7854F));

		bone2.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.2057F, 0.0625F, 0.0F, 0.0F, 0.0F, -1.1781F));

		bone2.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.2227F, -0.0227F, 0.0F, 0.0F, 0.0F, -1.5708F));

		bone2.addChild("cube_r5", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.2057F, -0.1079F, 0.0F, 0.0F, 0.0F, -1.9635F));

		bone2.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.1574F, -0.1801F, 0.0F, 0.0F, 0.0F, -2.3562F));

		bone2.addChild("cube_r7", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0852F, -0.2284F, 0.0F, 0.0F, 0.0F, -2.7489F));

		bone2.addChild("cube_r8", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.2453F, 0.0F, 0.0F, 0.0F, -3.1416F));

		bone2.addChild("cube_r9", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.0852F, -0.2284F, 0.0F, 0.0F, 0.0F, 2.7489F));

		bone2.addChild("cube_r10", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.1574F, -0.1801F, 0.0F, 0.0F, 0.0F, 2.3562F));

		bone2.addChild("cube_r11", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.2057F, -0.1079F, 0.0F, 0.0F, 0.0F, 1.9635F));

		bone2.addChild("cube_r12", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.2227F, -0.0227F, 0.0F, 0.0F, 0.0F, 1.5708F));

		bone2.addChild("cube_r13", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.2057F, 0.0625F, 0.0F, 0.0F, 0.0F, 1.1781F));

		bone2.addChild("cube_r14", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.1574F, 0.1348F, 0.0F, 0.0F, 0.0F, 0.7854F));

		bone2.addChild("cube_r15", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.25F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.0852F, 0.1831F, 0.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData bone3 = root.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 20.0F, 0.0F));

		bone3.addChild("cube_r16", ModelPartBuilder.create().uv(0, 10).cuboid(-1.0F, -5.25F, -2.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 10).cuboid(-1.0F, -5.25F, -3.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.2453F, 0.0F, 0.0F, 0.0F, -3.1416F));

		ModelPartData bone4 = root.addChild("bone4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 20.0F, 0.0F));

		bone4.addChild("cube_r17", ModelPartBuilder.create().uv(0, 6).cuboid(1.0F, -5.25F, -3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 6).cuboid(-3.0F, -5.25F, -3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 6).cuboid(-1.0F, -3.25F, -3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 6).cuboid(-1.0F, -7.25F, -3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.2453F, 0.0F, 0.0F, 0.0F, -3.1416F));

		ModelPartData bone = root.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 26.0F, 0.0F));

		bone.addChild("cube_r18", ModelPartBuilder.create().uv(0, 4).cuboid(1.0F, -1.25F, -2.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 4).cuboid(-3.0F, -1.25F, -2.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 4).cuboid(-3.0F, -5.25F, -2.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 4).cuboid(1.0F, -5.25F, -2.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.2453F, 0.0F, 0.0F, 0.0F, -3.1416F));

		return TexturedModelData.of(modelData, 16, 16);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//		wheel.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

		poseStack.push();
		poseStack.scale(1.7f, 1.7f, 1.7f);
		poseStack.translate(0 / 16.0, -24 / 16.0, 0 / 16.0);
		poseStack.multiply(new Quaternionf().rotateY((float) Math.toRadians(270)));
		poseStack.multiply(new Quaternionf().rotateZ((float) Math.toRadians(0)));
		bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

		poseStack.pop();
	}
}














