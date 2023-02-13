package motobox.vehicle.render.engine;

import motobox.Motobox;
import motobox.entity.VehicleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

/**
 * Made with Blockbench 4.5.2
 * Exported for Minecraft version 1.17+ for Yarn
 * Paste this class into your mod and generate all required imports
 */
public class MotorbikeEngineModel extends EntityModel<VehicleEntity> {
	private final ModelPart bb_main;

	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("engine_motorbike"), "main");

	public MotorbikeEngineModel(EntityRendererFactory.Context context) {
		this.bb_main = context.getPart(MODEL_LAYER).getChild("bone");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(27, 26).cuboid(-0.3784F, 5.101F, 0.3866F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(6, 4).cuboid(-0.7568F, 0.6216F, -1.2432F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F))
				.uv(5, 17).cuboid(-2.1352F, 0.0F, -0.6216F, 4.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		bone.addChild("cube_r1", ModelPartBuilder.create().uv(24, 12).cuboid(-0.7568F, -1.9146F, -1.8648F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
				.uv(22, 2).cuboid(-0.7568F, 0.9932F, -2.0202F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.3512F, 0.6216F, 0.3491F, 0.0F, 0.0F));

		bone.addChild("cube_r2", ModelPartBuilder.create().uv(0, 2).cuboid(-0.6892F, -0.2432F, -0.8108F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.8578F, 2.2514F, 0.1309F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(VehicleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrices.push();
		matrices.translate(0, -0.3 , -3 / 16.0);
		matrices.scale(1.7f, 1.7f, 1.7f);
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		matrices.pop();
	}
}