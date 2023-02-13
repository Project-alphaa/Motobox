// Made with Blockbench 4.6.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
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

public class RustySteelWheelModel extends EntityModel<VehicleEntity> {
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("wheel_rusty_steel"), "main");

	private final ModelPart front;

	public RustySteelWheelModel(EntityRendererFactory.Context ctx) {
		this.front = ctx.getPart(MODEL_LAYER).getChild("front");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData front = modelPartData.addChild("front", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 22.0632F, -1.6F));

		ModelPartData fronttire = front.addChild("fronttire", ModelPartBuilder.create(), ModelTransform.pivot(-26.4133F, -8.3147F, -21.7881F));

		ModelPartData ftl8 = fronttire.addChild("ftl8", ModelPartBuilder.create().uv(30, 32).cuboid(16.9223F, 8.1925F, 19.0224F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F))
		.uv(27, 0).cuboid(30.0043F, 8.1925F, 19.0224F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F))
		.uv(23, 16).cuboid(22.9633F, 15.2335F, 19.0224F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(13, 13).cuboid(22.9633F, 2.1515F, 19.0224F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(1.95F, 0.6F, 2.3407F));

		ModelPartData ftl_r1 = ftl8.addChild("ftl_r1", ModelPartBuilder.create().uv(0, 13).cuboid(-1.25F, -5.5F, -1.75F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(25.0134F, 7.7112F, 20.7724F, 0.0F, 0.0F, 0.3927F));

		ModelPartData ftl_r2 = ftl8.addChild("ftl_r2", ModelPartBuilder.create().uv(0, 19).cuboid(-1.25F, -5.5F, -1.75F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(23.4513F, 7.9025F, 20.7724F, 0.0F, 0.0F, -0.3927F));

		ModelPartData ftl_r3 = ftl8.addChild("ftl_r3", ModelPartBuilder.create().uv(13, 19).cuboid(-1.75F, 3.5F, -1.75F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(23.9132F, 11.6738F, 20.7724F, 0.0F, 0.0F, 0.3927F));

		ModelPartData ftl_r4 = ftl8.addChild("ftl_r4", ModelPartBuilder.create().uv(23, 22).cuboid(-1.75F, 3.5F, -1.75F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(25.4753F, 11.4824F, 20.7724F, 0.0F, 0.0F, -0.3927F));

		ModelPartData ftl_r5 = ftl8.addChild("ftl_r5", ModelPartBuilder.create().uv(0, 25).cuboid(3.5F, -1.25F, -1.75F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(26.0833F, 10.9589F, 20.7724F, 0.0F, 0.0F, 0.7854F));

		ModelPartData ftl_r6 = ftl8.addChild("ftl_r6", ModelPartBuilder.create().uv(11, 25).cuboid(3.5F, -1.25F, -1.75F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(26.4446F, 10.2426F, 20.7724F, 0.0F, 0.0F, 0.3927F));

		ModelPartData ftl_r7 = ftl8.addChild("ftl_r7", ModelPartBuilder.create().uv(27, 7).cuboid(3.5F, -1.25F, -1.75F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(26.2533F, 8.6804F, 20.7724F, 0.0F, 0.0F, -0.3927F));

		ModelPartData ftl_r8 = ftl8.addChild("ftl_r8", ModelPartBuilder.create().uv(22, 28).cuboid(3.5F, -1.25F, -1.75F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(25.7297F, 8.0725F, 20.7724F, 0.0F, 0.0F, -0.7854F));

		ModelPartData ftl_r9 = ftl8.addChild("ftl_r9", ModelPartBuilder.create().uv(0, 32).cuboid(-5.5F, -1.75F, -1.75F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(22.8433F, 8.426F, 20.7724F, 0.0F, 0.0F, 0.7854F));

		ModelPartData ftl_r10 = ftl8.addChild("ftl_r10", ModelPartBuilder.create().uv(11, 32).cuboid(-5.5F, -2.25F, -1.75F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(22.2906F, 9.6043F, 20.7724F, 0.0F, 0.0F, 0.3927F));

		ModelPartData ftl_r11 = ftl8.addChild("ftl_r11", ModelPartBuilder.create().uv(33, 25).cuboid(-5.5F, -2.25F, -1.75F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(22.8647F, 11.1664F, 20.7724F, 0.0F, 0.0F, -0.3927F));

		ModelPartData ftl_r12 = ftl8.addChild("ftl_r12", ModelPartBuilder.create().uv(35, 11).cuboid(-5.5F, -2.25F, -1.75F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(23.5504F, 11.666F, 20.7724F, 0.0F, 0.0F, -0.7854F));

		ModelPartData frl8 = fronttire.addChild("frl8", ModelPartBuilder.create().uv(41, 23).cuboid(10.3875F, -2.6458F, 3.9111F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(29, 39).cuboid(10.3875F, 6.4088F, 3.9111F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(44, 27).cuboid(6.3602F, 1.3815F, 3.9111F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(23, 42).cuboid(15.4149F, 1.3815F, 3.9111F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(15.0258F, 8.1473F, 17.952F));

		ModelPartData cube_r1 = frl8.addChild("cube_r1", ModelPartBuilder.create().uv(7, 39).cuboid(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(13.1201F, 6.5642F, 4.9111F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r2 = frl8.addChild("cube_r2", ModelPartBuilder.create().uv(43, 8).cuboid(0.0F, -1.5F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(13.8817F, 5.5828F, 4.9111F, 0.0F, 0.0F, 0.7854F));

		ModelPartData cube_r3 = frl8.addChild("cube_r3", ModelPartBuilder.create().uv(5, 43).cuboid(0.5F, -2.9F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(13.9193F, 5.4867F, 4.9111F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r4 = frl8.addChild("cube_r4", ModelPartBuilder.create().uv(36, 41).cuboid(0.5F, 0.9F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(13.9193F, -0.7237F, 4.9111F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r5 = frl8.addChild("cube_r5", ModelPartBuilder.create().uv(41, 32).cuboid(0.0F, -0.5F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(13.8817F, -0.8198F, 4.9111F, 0.0F, 0.0F, -0.7854F));

		ModelPartData cube_r6 = frl8.addChild("cube_r6", ModelPartBuilder.create().uv(36, 6).cuboid(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(13.1201F, -1.8012F, 4.9111F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r7 = frl8.addChild("cube_r7", ModelPartBuilder.create().uv(39, 37).cuboid(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(9.655F, 6.5642F, 4.9111F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r8 = frl8.addChild("cube_r8", ModelPartBuilder.create().uv(43, 41).cuboid(-1.0F, -1.5F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(8.8933F, 5.5828F, 4.9111F, 0.0F, 0.0F, -0.7854F));

		ModelPartData cube_r9 = frl8.addChild("cube_r9", ModelPartBuilder.create().uv(43, 18).cuboid(-1.5F, -2.9F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(8.8558F, 5.4867F, 4.9111F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r10 = frl8.addChild("cube_r10", ModelPartBuilder.create().uv(30, 44).cuboid(-1.5F, 0.9F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(8.8558F, -0.7237F, 4.9111F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r11 = frl8.addChild("cube_r11", ModelPartBuilder.create().uv(45, 0).cuboid(-1.0F, -0.5F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(8.8933F, -0.8198F, 4.9111F, 0.0F, 0.0F, 0.7854F));

		ModelPartData cube_r12 = frl8.addChild("cube_r12", ModelPartBuilder.create().uv(14, 41).cuboid(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(9.655F, -1.8012F, 4.9111F, 0.0F, 0.0F, -0.3927F));

		ModelPartData firl14 = fronttire.addChild("firl14", ModelPartBuilder.create().uv(0, 0).cuboid(4.7605F, -2.1458F, 5.0064F, 13.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(15.1528F, 6.1473F, 17.9067F));

		ModelPartData firl15 = fronttire.addChild("firl15", ModelPartBuilder.create().uv(18, 47).cuboid(41.9461F, 5.942F, 4.2111F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(44, 46).cuboid(41.9461F, 6.942F, 4.2111F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(44, 5).cuboid(38.4854F, 2.3964F, 4.1763F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 45).cuboid(35.0289F, 5.942F, 4.2111F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(46, 13).cuboid(35.0289F, 6.942F, 4.2111F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(37, 46).cuboid(38.4854F, 9.112F, 4.1763F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-13.0742F, 3.7473F, 18.552F));

		ModelPartData firl7_r1 = firl15.addChild("firl7_r1", ModelPartBuilder.create().uv(38, 0).cuboid(-0.6406F, 1.9219F, -0.2813F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(38.7665F, 6.9707F, 4.4576F, 0.0F, 0.0F, 0.4014F));

		ModelPartData frl_r1 = firl15.addChild("frl_r1", ModelPartBuilder.create().uv(10, 19).cuboid(-3.35F, -0.4F, -1.25F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(39.0875F, 6.8278F, 5.4263F, 0.0F, 0.0F, -0.7854F));

		ModelPartData frl_r2 = firl15.addChild("frl_r2", ModelPartBuilder.create().uv(25, 47).cuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(36.7065F, 8.2605F, 4.6763F, 0.0F, 0.0F, -0.3927F));

		ModelPartData frl_r3 = firl15.addChild("frl_r3", ModelPartBuilder.create().uv(0, 45).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(36.6822F, 5.4957F, 4.7111F, 0.0F, 0.0F, 0.3927F));

		ModelPartData frl_r4 = firl15.addChild("frl_r4", ModelPartBuilder.create().uv(10, 13).cuboid(-3.35F, 0.0F, -1.25F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(39.4875F, 6.5042F, 5.4611F, 0.0F, 0.0F, 0.7854F));

		ModelPartData firl4_r1 = firl15.addChild("firl4_r1", ModelPartBuilder.create().uv(22, 35).cuboid(-0.6406F, -6.9219F, -0.2813F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(38.7665F, 6.5376F, 4.4576F, 0.0F, 0.0F, -0.4014F));

		ModelPartData firl6_r1 = firl15.addChild("firl6_r1", ModelPartBuilder.create().uv(0, 39).cuboid(-1.3594F, 1.9219F, -0.2813F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(40.2086F, 6.9707F, 4.4576F, 0.0F, 0.0F, -0.4014F));

		ModelPartData firl3_r1 = firl15.addChild("firl3_r1", ModelPartBuilder.create().uv(36, 18).cuboid(-1.3594F, -6.9219F, -0.2813F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(40.2086F, 6.5376F, 4.4576F, 0.0F, 0.0F, 0.4014F));

		ModelPartData frl_r5 = firl15.addChild("frl_r5", ModelPartBuilder.create().uv(23, 13).cuboid(2.35F, -0.4F, -1.25F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(39.8875F, 6.8278F, 5.4263F, 0.0F, 0.0F, 0.7854F));

		ModelPartData frl_r6 = firl15.addChild("frl_r6", ModelPartBuilder.create().uv(4, 48).cuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(42.2686F, 8.2605F, 4.6763F, 0.0F, 0.0F, 0.3927F));

		ModelPartData frl_r7 = firl15.addChild("frl_r7", ModelPartBuilder.create().uv(9, 48).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(42.2928F, 5.4957F, 4.7111F, 0.0F, 0.0F, -0.3927F));

		ModelPartData frl_r8 = firl15.addChild("frl_r8", ModelPartBuilder.create().uv(8, 25).cuboid(2.35F, 0.0F, -1.25F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(39.4875F, 6.5042F, 5.4611F, 0.0F, 0.0F, -0.7854F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	
	@Override
	public void setAngles(VehicleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrices.push();
		matrices.scale(1.1f, 1.1f, 1.1f);
		matrices.translate(0.2, -24 / 16.0, 0 / 16.0);
		matrices.multiply(new Quaternionf().rotateY((float) Math.toRadians(270)));
		matrices.multiply(new Quaternionf().rotateZ((float) Math.toRadians(0)));

		front.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);

		matrices.pop();
	}
}