package motobox.vehicle.render.wheel;

import motobox.Motobox;
import motobox.entity.VehicleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 4.6.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class SteelRimWheelModel extends EntityModel<VehicleEntity> {
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("wheel_steel_rim"), "main");

	private final ModelPart hexadecagon1;
	private final ModelPart hexadecagon2;
	private final ModelPart octagon1;
	private final ModelPart octagon2;
	private final ModelPart hexadecagon4;
	private final ModelPart bb_main;

	public SteelRimWheelModel(EntityRendererFactory.Context ctx) {
		this.hexadecagon1 = ctx.getPart(MODEL_LAYER).getChild("hexadecagon1");
		this.hexadecagon2 = ctx.getPart(MODEL_LAYER).getChild("hexadecagon2");
		this.octagon1 = ctx.getPart(MODEL_LAYER).getChild("octagon1");
		this.octagon2 = ctx.getPart(MODEL_LAYER).getChild("octagon2");
		this.hexadecagon4 = ctx.getPart(MODEL_LAYER).getChild("hexadecagon4");
		this.bb_main = ctx.getPart(MODEL_LAYER).getChild("bb_main");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData hexadecagon1 = modelPartData.addChild("hexadecagon1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 17.0F, 0.0F));

		ModelPartData hexadecagon2 = modelPartData.addChild("hexadecagon2", ModelPartBuilder.create().uv(40, 29).cuboid(0.9F, 10.5137F, -3.6701F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(40, 3).cuboid(0.9F, 11.5137F, 2.3573F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(40, 3).cuboid(1.9F, 9.5137F, -2.6427F, 2.0F, 5.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 39).cuboid(0.9F, 14.5273F, -1.6564F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(11, 38).cuboid(0.9F, 8.5F, -0.6564F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 12.0F, 0.0F));

		ModelPartData hexadecagon_r1 = hexadecagon2.addChild("hexadecagon_r1", ModelPartBuilder.create().uv(30, 25).cuboid(-2.5F, -4.3F, -0.6564F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 12.8F, -0.3128F, -0.3927F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r2 = hexadecagon2.addChild("hexadecagon_r2", ModelPartBuilder.create().uv(38, 35).cuboid(-2.5F, -4.3F, -0.6564F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 12.6803F, 0.289F, 0.3927F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r3 = hexadecagon2.addChild("hexadecagon_r3", ModelPartBuilder.create().uv(38, 38).cuboid(-2.5F, 3.3F, -1.6564F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 11.3471F, 0.289F, -0.3927F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r4 = hexadecagon2.addChild("hexadecagon_r4", ModelPartBuilder.create().uv(22, 39).cuboid(-2.5F, 3.3F, -1.6564F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 11.2273F, -0.3128F, 0.3927F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r5 = hexadecagon2.addChild("hexadecagon_r5", ModelPartBuilder.create().uv(39, 33).cuboid(-2.5F, -0.3436F, 3.3F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 11.3471F, -0.6018F, -0.7854F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r6 = hexadecagon2.addChild("hexadecagon_r6", ModelPartBuilder.create().uv(40, 0).cuboid(-2.5F, -0.3436F, 3.3F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 11.5682F, -0.823F, -0.3927F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r7 = hexadecagon2.addChild("hexadecagon_r7", ModelPartBuilder.create().uv(40, 6).cuboid(-2.5F, -0.3436F, 3.3F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 12.1701F, -0.9427F, 0.3927F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r8 = hexadecagon2.addChild("hexadecagon_r8", ModelPartBuilder.create().uv(40, 9).cuboid(-2.5F, -0.3436F, 3.3F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 12.4591F, -0.823F, 0.7854F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r9 = hexadecagon2.addChild("hexadecagon_r9", ModelPartBuilder.create().uv(11, 40).cuboid(-2.5F, -1.3436F, -4.3F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 12.4591F, 0.5102F, -0.7854F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r10 = hexadecagon2.addChild("hexadecagon_r10", ModelPartBuilder.create().uv(40, 13).cuboid(-2.5F, -1.3436F, -4.3F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 12.1701F, 0.6299F, -0.3927F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r11 = hexadecagon2.addChild("hexadecagon_r11", ModelPartBuilder.create().uv(33, 40).cuboid(-2.5F, -1.3436F, -4.3F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 11.5682F, 0.5102F, 0.3927F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r12 = hexadecagon2.addChild("hexadecagon_r12", ModelPartBuilder.create().uv(0, 41).cuboid(-2.5F, -1.3436F, -4.3F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.4F, 11.3471F, 0.289F, 0.7854F, 0.0F, 0.0F));

		ModelPartData octagon1 = modelPartData.addChild("octagon1", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 19.0F, 0.0F));

		ModelPartData octagon2 = modelPartData.addChild("octagon2", ModelPartBuilder.create().uv(0, 0).cuboid(-3.4F, 4.5571F, -1.4034F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 9).cuboid(-3.4F, 3.85F, -0.6963F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(4.4F, 19.0F, 0.0F));

		ModelPartData octagon_r1 = octagon2.addChild("octagon_r1", ModelPartBuilder.create().uv(4, 9).cuboid(-0.5F, -0.8447F, -0.4053F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(-0.5F, -0.5518F, -1.1124F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.9F, 5.0874F, -0.0928F, 2.3562F, 0.0F, 0.0F));

		ModelPartData octagon_r2 = octagon2.addChild("octagon_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -0.4697F, -1.1036F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 9).cuboid(-0.5F, -1.1768F, -0.3964F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.9F, 5.0874F, -0.0928F, -3.1416F, 0.0F, 0.0F));

		ModelPartData octagon_r3 = octagon2.addChild("octagon_r3", ModelPartBuilder.create().uv(4, 9).cuboid(0.0F, -0.5F, -0.6213F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.4F, 4.9358F, 0.0966F, -0.7854F, 0.0F, 0.0F));

		ModelPartData octagon_r4 = octagon2.addChild("octagon_r4", ModelPartBuilder.create().uv(0, 4).cuboid(0.0F, -0.3787F, -1.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.4F, 5.1784F, 0.0966F, -0.7854F, 0.0F, 0.0F));

		ModelPartData hexadecagon4 = modelPartData.addChild("hexadecagon4", ModelPartBuilder.create().uv(28, 3).cuboid(-14.0F, -5.0F, -0.1935F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(11.0F, 24.0F, -1.0F));

		ModelPartData hexadecagon_r13 = hexadecagon4.addChild("hexadecagon_r13", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -3.0137F, -0.8467F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 0.1806F, -1.2071F, 1.5708F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r14 = hexadecagon4.addChild("hexadecagon_r14", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -3.2256F, -0.0878F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 0.1806F, -1.2071F, 1.1781F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r15 = hexadecagon4.addChild("hexadecagon_r15", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -3.7118F, 0.5323F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 0.1806F, -1.2071F, 0.7854F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r16 = hexadecagon4.addChild("hexadecagon_r16", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -4.3984F, 0.919F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 0.1806F, -1.2071F, 0.3927F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r17 = hexadecagon4.addChild("hexadecagon_r17", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -3.4951F, -2.3155F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 0.1806F, -1.2071F, 2.3562F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r18 = hexadecagon4.addChild("hexadecagon_r18", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -3.1083F, -1.629F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 0.1806F, -1.2071F, 1.9635F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r19 = hexadecagon4.addChild("hexadecagon_r19", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -4.1151F, -2.8017F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 0.1806F, -1.2071F, 2.7489F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r20 = hexadecagon4.addChild("hexadecagon_r20", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -4.8741F, -3.0137F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 0.1806F, -1.2071F, 3.1416F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r21 = hexadecagon4.addChild("hexadecagon_r21", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -2.2431F, 0.8604F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 1.8877F, 3.5908F, -2.7489F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r22 = hexadecagon4.addChild("hexadecagon_r22", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -1.7431F, -0.3467F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 1.8877F, 3.5908F, -2.3562F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r23 = hexadecagon4.addChild("hexadecagon_r23", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -1.7431F, -1.6533F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 1.8877F, 3.5908F, -1.9635F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r24 = hexadecagon4.addChild("hexadecagon_r24", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -2.2431F, -2.8604F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, 1.8877F, 3.5908F, -1.5708F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r25 = hexadecagon4.addChild("hexadecagon_r25", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -1.1533F, -0.2294F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, -2.1671F, 4.0908F, -1.1781F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r26 = hexadecagon4.addChild("hexadecagon_r26", ModelPartBuilder.create().uv(28, 3).cuboid(-2.5F, -1.1533F, -1.7706F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.5F, -2.1671F, 4.0908F, -0.7854F, 0.0F, 0.0F));

		ModelPartData hexadecagon_r27 = hexadecagon4.addChild("hexadecagon_r27", ModelPartBuilder.create().uv(28, 3).cuboid(-4.0F, -6.0F, -1.1935F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, 1.0F, 0.6131F, -0.3927F, 0.0F, 0.0F));

		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(48, 48).cuboid(0.5F, -3.2F, -3.4456F, 1.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(VehicleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		hexadecagon1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		hexadecagon2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		octagon1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		octagon2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		hexadecagon4.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}