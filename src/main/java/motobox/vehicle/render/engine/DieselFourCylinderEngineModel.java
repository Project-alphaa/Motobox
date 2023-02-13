package motobox.vehicle.render.engine;

import motobox.Motobox;
import motobox.entity.VehicleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class DieselFourCylinderEngineModel extends EntityModel<VehicleEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("diesel_four_cylinder_engine"), "main");
	private final ModelPart bb_main;

	public DieselFourCylinderEngineModel(EntityRendererFactory.Context ctx) {
		this.bb_main = ctx.getPart(MODEL_LAYER).getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -2.0F, -5.0F, 6.0F, 2.0F, 13.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-2.0F, -9.0F, -5.0F, 4.0F, 7.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -1.25F, -4.5F, 4.0F, 2.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-2.5911F, -4.8549F, 0.0F, 0.0F, 0.0F, -1.9635F));

		bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-4.65F, -6.4F, -4.75F, 3.0F, 5.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-2.1569F, -11.1569F, 0.0F, 0.0F, 0.0F, -2.3562F));

		bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, 0.6F, -4.5F, 4.0F, 2.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(1.0251F, -4.4393F, 0.0F, 0.0F, 0.0F, -1.1781F));

		bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-1.6F, -3.5F, -4.75F, 3.0F, 5.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(3.5F, -5.5F, 0.0F, 0.0F, 0.0F, -0.7854F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(VehicleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrices.push();
		matrices.translate(0, -1.5, 0);
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		matrices.pop();
	}
}