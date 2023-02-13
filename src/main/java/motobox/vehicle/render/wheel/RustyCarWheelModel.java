package motobox.vehicle.render.wheel;

import motobox.entity.VehicleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Quaternionf;

/**
 * Made with Blockbench 4.5.2
 * Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
 * Paste this class into your mod and generate all required imports
 */
public class RustyCarWheelModel<T extends VehicleEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(new Identifier("modid", "rusty_car_1_converted"), "main");
	private final ModelPart bb_main;

	public RustyCarWheelModel(EntityRendererFactory.Context context) {
		this.bb_main = context.getPart(MODEL_LAYER).getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 5).cuboid(-4.2339F, -0.7388F, -2.8F, 8.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(17, 15).cuboid(-0.5F, -4.0F, -2.8705F, 1.0F, 8.0F, 3.0F, new Dilation(0.0F))
				.uv(32, 14).cuboid(-5.7526F, -1.1458F, -2.9216F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(16, 28).cuboid(3.883F, -1.1458F, -2.9216F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(23, 24).cuboid(-1.1443F, -5.7541F, -2.9216F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(20, 7).cuboid(-1.1443F, 3.8816F, -2.9216F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 15).cuboid(-4.0F, -4.0F, -2.2F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData wheel_r1 = bb_main.addChild("wheel_r1", ModelPartBuilder.create().uv(20, 2).cuboid(-1.1541F, 3.9032F, -1.6264F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 24).cuboid(-1.1541F, -5.7325F, -1.6264F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(28, 4).cuboid(3.8732F, -1.1241F, -1.6264F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(31, 26).cuboid(-5.7625F, -1.1241F, -1.6264F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0014F, -0.0239F, -1.3121F, 0.0F, 0.0F, -0.3927F));

		ModelPartData wheel_r2 = bb_main.addChild("wheel_r2", ModelPartBuilder.create().uv(23, 12).cuboid(-1.137F, 3.9043F, -1.6264F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(8, 26).cuboid(-1.137F, -5.7314F, -1.6264F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 29).cuboid(3.8903F, -1.1231F, -1.6264F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(32, 20).cuboid(-5.7454F, -1.1231F, -1.6264F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0014F, -0.0239F, -1.3121F, 0.0F, 0.0F, 0.3927F));

		ModelPartData wheel_r3 = bb_main.addChild("wheel_r3", ModelPartBuilder.create().uv(26, 17).cuboid(3.8662F, -1.1294F, -1.6264F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(9, 31).cuboid(-5.7695F, -1.1294F, -1.6264F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0014F, -0.0239F, -1.2952F, 0.0F, 0.0F, -0.7854F));

		ModelPartData wheel_r4 = bb_main.addChild("wheel_r4", ModelPartBuilder.create().uv(25, 29).cuboid(3.8979F, -1.1275F, -1.6264F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(31, 32).cuboid(-5.7378F, -1.1275F, -1.6264F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0014F, -0.0239F, -1.2952F, 0.0F, 0.0F, 0.7854F));

		ModelPartData wheel_r5 = bb_main.addChild("wheel_r5", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0962F, -0.7057F, -1.4753F, 8.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0344F, 0.1621F, -1.331F, 0.0F, 0.0F, -0.7854F));

		ModelPartData wheel_r6 = bb_main.addChild("wheel_r6", ModelPartBuilder.create().uv(0, 10).cuboid(-4.3736F, -0.7038F, -1.4753F, 8.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0344F, 0.2429F, -1.381F, 0.0F, 0.0F, 0.7854F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.push();
		poseStack.scale(1.7f, 1.7f, 1.7f);
		poseStack.translate(0 / 16.0, -24 / 16.0, 0 / 16.0);
		poseStack.multiply(new Quaternionf().rotateY((float) Math.toRadians(270)));
		poseStack.multiply(new Quaternionf().rotateZ((float) Math.toRadians(0)));

		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

		poseStack.pop();
	}
}