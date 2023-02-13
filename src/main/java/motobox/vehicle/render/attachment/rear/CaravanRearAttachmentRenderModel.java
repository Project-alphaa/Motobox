package motobox.vehicle.render.attachment.rear;// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import motobox.Motobox;
import motobox.entity.VehicleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Quaternionf;

public class CaravanRearAttachmentRenderModel<T extends VehicleEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("vehicle/rear_attachment/caravan"), "main");
	private final ModelPart group;
	private final ModelPart group22;
	private final ModelPart group12;
	private final ModelPart group9;

	public CaravanRearAttachmentRenderModel(EntityRendererFactory.Context root) {
		this.group = root.getPart(MODEL_LAYER).getChild("group");
		this.group22 = root.getPart(MODEL_LAYER).getChild("group22");
		this.group12 = root.getPart(MODEL_LAYER).getChild("group12");
		this.group9 = root.getPart(MODEL_LAYER).getChild("group9");
	}

	@SuppressWarnings("unused")
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		ModelPartData group = root.addChild("group", ModelPartBuilder.create(), ModelTransform.pivot(-8.0F, 16.0F, 8.0F));

		ModelPartData group53 = group.addChild("group53", ModelPartBuilder.create().uv(0, 0).cuboid(-1.75F, -4.0F, 5.75F, 14.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-17.0F, -4.0F, 5.75F, 9.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.5F, -7.0F, -5.75F, 0.0F, 3.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.5F, -4.0F, -5.75F, 0.0F, 2.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-18.9134F, -10.4694F, -5.75F, 0.0F, 3.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-18.9134F, -14.4694F, -5.75F, 0.0F, 4.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-17.0F, -7.0F, 5.75F, 30.0F, 3.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-18.9F, -7.0F, 5.65F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-18.9F, -12.0F, 5.75F, 28.0F, 5.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-18.9F, -13.975F, 5.75F, 28.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-18.9F, -14.475F, 5.75F, 28.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-16.95F, -2.5F, -3.75F, 29.0F, 0.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.7F, -2.5F, -5.75F, 14.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-16.95F, -2.5F, -5.75F, 9.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.7F, -2.5F, 3.75F, 14.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-16.95F, -2.5F, 3.75F, 9.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-18.9F, -14.475F, 0.0F, 28.0F, 0.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-18.9F, -14.475F, 3.0F, 28.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-18.9F, -14.475F, -5.75F, 28.0F, 0.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-18.9F, -14.475F, -2.75F, 28.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 8.0F, -8.0F));

		ModelPartData cube_r1 = group53.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.25F, -5.575F, -5.775F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.25F, -5.575F, 4.225F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.25F, -5.575F, -4.225F, 0.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.275F, -4.0F, -4.225F, 0.0F, 5.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.25F, 1.0F, -4.225F, 0.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(11.8123F, -9.214F, 0.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r2 = group53.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(11.375F, -4.3875F, -0.225F, 3.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.6919F, -4.9057F, 6.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r3 = group53.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-0.25F, -2.4F, -6.225F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.25F, -2.4F, -17.825F, 0.0F, 4.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(-17.7257F, -4.4054F, 12.025F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r4 = group53.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-0.875F, -1.5F, -0.2F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0581F, -3.6794F, -6.025F, 0.0F, 0.0F, 0.7854F));

		ModelPartData cube_r5 = group53.addChild("cube_r5", ModelPartBuilder.create().uv(0, 0).cuboid(-7.375F, -2.0F, -0.225F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(2.7578F, -7.922F, -6.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData group52 = group.addChild("group52", ModelPartBuilder.create().uv(0, 0).cuboid(17.9144F, -2.1F, -0.7329F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(15.5394F, -1.9F, -1.6421F, 0.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 8.0F, -8.0F));

		ModelPartData cube_r6 = group52.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-5.6161F, -0.25F, 0.6339F, 7.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(17.0597F, -1.75F, 0.0F, 0.0F, 0.3927F, 0.0F));

		ModelPartData cube_r7 = group52.addChild("cube_r7", ModelPartBuilder.create().uv(0, 0).cuboid(-0.25F, -1.35F, -0.275F, 0.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(18.7894F, -3.45F, -0.0329F, 0.0F, -0.7854F, 0.0F));

		ModelPartData cube_r8 = group52.addChild("cube_r8", ModelPartBuilder.create().uv(0, 0).cuboid(-5.6161F, -0.25F, -1.1339F, 7.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(17.0597F, -1.75F, 0.0F, 0.0F, -0.3927F, 0.0F));

		ModelPartData group51 = group.addChild("group51", ModelPartBuilder.create(), ModelTransform.pivot(13.7894F, 5.55F, -0.0329F));

		ModelPartData cube_r9 = group51.addChild("cube_r9", ModelPartBuilder.create().uv(0, 0).cuboid(-0.35F, -1.0F, -0.375F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.35F, -1.0F, -0.375F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.35F, -1.0F, 0.375F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.35F, -1.0F, -0.375F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, -1.0F, -8.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData group43 = group.addChild("group43", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData group42 = group43.addChild("group42", ModelPartBuilder.create().uv(0, 0).cuboid(2.7422F, 3.422F, -10.25F, 5.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.4922F, 3.422F, -10.5F, 6.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(8.5422F, 3.422F, -10.25F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.4922F, 3.422F, -10.25F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.2422F, 0.078F, 6.0F));

		ModelPartData cube_r10 = group42.addChild("cube_r10", ModelPartBuilder.create().uv(0, 0).cuboid(-7.375F, -2.0F, -0.225F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, -8.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData cube_r11 = group42.addChild("cube_r11", ModelPartBuilder.create().uv(0, 0).cuboid(-0.875F, -1.5F, -0.2F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.1841F, 4.2426F, -8.025F, 0.0F, 0.0F, 0.7854F));

		ModelPartData group41 = group43.addChild("group41", ModelPartBuilder.create(), ModelTransform.pivot(-2.2422F, 0.078F, 6.0F));

		ModelPartData group40 = group41.addChild("group40", ModelPartBuilder.create().uv(0, 0).cuboid(1.85F, -2.375F, 0.25F, 5.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.6F, -2.375F, 0.25F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.6F, -2.375F, 2.25F, 6.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.65F, -2.375F, 0.25F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.6F, -2.375F, 0.25F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.8922F, 5.797F, -20.0F));

		ModelPartData group39 = group41.addChild("group39", ModelPartBuilder.create(), ModelTransform.pivot(0.8922F, 5.797F, -20.0F));

		ModelPartData group38 = group41.addChild("group38", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData group37 = group41.addChild("group37", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData group36 = group37.addChild("group36", ModelPartBuilder.create(), ModelTransform.pivot(0.8922F, 5.797F, -20.0F));

		ModelPartData group35 = group37.addChild("group35", ModelPartBuilder.create(), ModelTransform.pivot(0.8922F, 5.797F, -20.0F));

		ModelPartData group34 = group35.addChild("group34", ModelPartBuilder.create(), ModelTransform.pivot(-9.775F, -5.075F, 9.875F));

		ModelPartData group33 = group35.addChild("group33", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData group32 = group.addChild("group32", ModelPartBuilder.create().uv(0, 0).cuboid(6.775F, 6.7358F, -15.676F, 4.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r12 = group32.addChild("cube_r12", ModelPartBuilder.create().uv(0, 0).cuboid(4.5F, 1.35F, -1.5F, 0.0F, 0.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.05F, 1.35F, -1.5F, 0.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(6.575F, 5.0F, -14.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData group31 = group.addChild("group31", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData group30 = group31.addChild("group30", ModelPartBuilder.create(), ModelTransform.pivot(-2.125F, -0.25F, -6.5F));

		ModelPartData group29 = group.addChild("group29", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r13 = group29.addChild("cube_r13", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, 5.025F, 6.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, 0.0F, -4.724F, 6.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(18.5526F, -5.5638F, -8.151F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r14 = group29.addChild("cube_r14", ModelPartBuilder.create().uv(0, 0).cuboid(4.25F, 0.0F, -4.875F, 0.0F, 0.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(20.1694F, -4.8941F, -8.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r15 = group29.addChild("cube_r15", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, -4.875F, 6.0F, 0.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(18.5522F, -5.5628F, -8.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r16 = group29.addChild("cube_r16", ModelPartBuilder.create().uv(0, 0).cuboid(0.25F, -4.5F, -4.225F, 0.0F, 0.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.25F, 1.0F, -4.225F, 0.0F, 0.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.25F, -4.5F, -4.775F, 0.0F, 6.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.25F, -4.5F, 4.225F, 0.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(19.8123F, -1.214F, -8.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r17 = group29.addChild("cube_r17", ModelPartBuilder.create().uv(0, 0).cuboid(1.35F, -2.2F, 9.875F, 0.0F, 4.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.35F, -2.2F, 0.175F, 0.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(19.5847F, -2.7433F, -13.025F, 0.0F, 0.0F, 0.3927F));

		ModelPartData group28 = group.addChild("group28", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData group27 = group.addChild("group27", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData group26 = group27.addChild("group26", ModelPartBuilder.create(), ModelTransform.pivot(-3.95F, 7.0F, -8.0F));

		ModelPartData group25 = group27.addChild("group25", ModelPartBuilder.create(), ModelTransform.pivot(-3.95F, 7.0F, -8.0F));

		ModelPartData group24 = group.addChild("group24", ModelPartBuilder.create().uv(0, 0).cuboid(-14.0787F, 3.133F, -8.25F, 16.0F, 3.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-8.4787F, -3.117F, -8.25F, 10.0F, 3.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-8.4787F, 0.383F, -8.25F, 10.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-8.4787F, -4.342F, -8.25F, 10.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.9213F, -4.342F, -8.25F, 3.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.9213F, -3.117F, -8.25F, 3.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.6713F, -3.117F, -8.25F, 1.0F, 6.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.6713F, -4.342F, -8.25F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.9213F, -2.867F, -8.5F, 3.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.6713F, -2.867F, -8.5F, 0.0F, 9.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.4213F, -2.867F, -8.5F, 0.0F, 9.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.2713F, -3.117F, -8.25F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(6.8963F, -3.117F, -8.475F, 0.0F, 6.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.2713F, -3.492F, -8.475F, 4.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(11.5213F, -3.117F, -8.475F, 0.0F, 6.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.2713F, 3.133F, -8.475F, 4.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.6713F, 3.133F, -8.25F, 10.0F, 3.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.1713F, 6.133F, -8.25F, 14.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.9213F, 6.133F, -8.5F, 3.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-14.0787F, 6.133F, -8.25F, 9.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-15.9787F, 3.133F, -8.15F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-15.9787F, -3.117F, -8.25F, 3.0F, 6.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-15.9787F, -4.342F, -8.25F, 3.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-13.1037F, -3.117F, -8.475F, 0.0F, 6.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-12.7287F, -3.117F, -8.25F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-12.7287F, 3.133F, -8.475F, 4.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-8.4787F, -3.117F, -8.475F, 0.0F, 6.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-12.7287F, -3.492F, -8.475F, 4.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-12.7287F, -4.342F, -8.25F, 4.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.2713F, -4.342F, -8.25F, 5.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(11.5213F, -3.117F, -8.25F, 1.0F, 6.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.3287F, -3.767F, -8.5F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0787F, -3.617F, -8.75F, 2.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(14.1713F, 7.233F, -8.5F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-13.8287F, 7.233F, -8.5F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(11.4213F, -4.117F, -8.5F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-15.5787F, -4.117F, -8.5F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(11.4213F, -4.117F, 4.25F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(14.1713F, 7.233F, 4.25F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-13.8287F, 7.233F, 4.25F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-15.5787F, -4.117F, 4.25F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0787F, -2.133F, -6.0F));

		ModelPartData cube_r18 = group24.addChild("cube_r18", ModelPartBuilder.create().uv(0, 0).cuboid(-1.4F, -0.4625F, -0.225F, 1.0F, 4.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, 1.9375F, -0.225F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, -0.0625F, -0.225F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, -4.0625F, -0.225F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, -2.0625F, -0.225F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, -8.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r19 = group24.addChild("cube_r19", ModelPartBuilder.create().uv(0, 0).cuboid(-1.125F, -0.5F, -0.25F, 2.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.9537F, -2.5007F, -8.3277F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r20 = group24.addChild("cube_r20", ModelPartBuilder.create().uv(0, 0).cuboid(-2.125F, -1.125F, -0.25F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(17.875F, -1.125F, -0.25F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-10.6037F, 2.2681F, -8.4496F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r21 = group24.addChild("cube_r21", ModelPartBuilder.create().uv(0, 0).cuboid(-2.125F, -1.125F, -0.25F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(17.875F, -1.125F, -0.25F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-10.6037F, 0.2681F, -8.4496F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r22 = group24.addChild("cube_r22", ModelPartBuilder.create().uv(0, 0).cuboid(-0.25F, -2.4F, -6.225F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-14.8044F, 5.7277F, -2.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData group23 = group24.addChild("group23", ModelPartBuilder.create(), ModelTransform.pivot(-7.2037F, 1.883F, -0.5F));

		ModelPartData cube_r23 = group23.addChild("cube_r23", ModelPartBuilder.create().uv(0, 0).cuboid(-3.5F, -0.35F, -0.175F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-3.875F, -4.25F, -0.125F, 3.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, -8.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData group22 = root.addChild("group22", ModelPartBuilder.create(), ModelTransform.pivot(-8.0F, 16.0F, 8.0F));

		ModelPartData group21 = group22.addChild("group21", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData group20 = group21.addChild("group20", ModelPartBuilder.create().uv(0, 0).cuboid(17.9351F, -4.766F, -8.0F, 0.0F, 5.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(17.9351F, -4.766F, -12.475F, 0.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r24 = group20.addChild("cube_r24", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -4.5F, -4.475F, 0.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(19.8123F, -1.214F, -8.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData group19 = group20.addChild("group19", ModelPartBuilder.create().uv(0, 0).cuboid(10.75F, -6.275F, -13.75F, 0.0F, 11.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(10.75F, -6.275F, -6.0F, 0.0F, 11.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData group18 = group21.addChild("group18", ModelPartBuilder.create().uv(0, 0).cuboid(12.75F, -1.8789F, 3.0243F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(19.0F, -1.8789F, 3.0243F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(15.8F, -1.8789F, 3.0243F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(19.0F, -1.8789F, -5.9757F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(15.8F, -1.8789F, -5.9757F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.75F, -1.8789F, -5.9757F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.75F, -2.6289F, 2.7993F, 9.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.75F, -4.125F, 5.05F, 9.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.75F, -4.125F, -5.7F, 9.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.75F, -2.625F, -4.9F, 9.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(13.75F, -4.125F, -2.625F, 8.0F, 0.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(16.825F, -3.975F, -0.375F, 0.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.35F, 5.8789F, -8.0243F));

		ModelPartData group17 = group22.addChild("group17", ModelPartBuilder.create().uv(0, 0).cuboid(6.275F, -6.275F, -6.0F, 0.0F, 11.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(6.75F, 0.725F, -5.75F, 1.0F, 4.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(6.75F, 0.375F, -5.75F, 0.0F, 0.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(6.85F, -0.625F, -4.175F, 0.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.0F, 0.375F, -3.5F, 0.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.0F, 0.375F, -5.75F, 0.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.75F, 0.375F, -5.75F, 0.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r25 = group17.addChild("cube_r25", ModelPartBuilder.create().uv(0, 0).cuboid(-0.075F, -0.125F, -0.075F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.332F, -0.7929F, -4.1F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r26 = group17.addChild("cube_r26", ModelPartBuilder.create().uv(0, 0).cuboid(-0.2309F, -0.0961F, -0.075F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.299F, -0.8556F, -4.1F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r27 = group17.addChild("cube_r27", ModelPartBuilder.create().uv(0, 0).cuboid(-0.075F, -0.125F, -0.075F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0871F, -0.8913F, -4.1F, 0.0F, 0.0F, 0.7854F));

		ModelPartData cube_r28 = group17.addChild("cube_r28", ModelPartBuilder.create().uv(0, 0).cuboid(-0.075F, 0.25F, -0.075F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.1106F, -1.0582F, -4.1F, 0.0F, 0.0F, 0.3927F));

		ModelPartData group16 = group17.addChild("group16", ModelPartBuilder.create(), ModelTransform.pivot(-6.25F, 7.0F, 2.25F));

		ModelPartData cube_r29 = group16.addChild("cube_r29", ModelPartBuilder.create().uv(0, 0).cuboid(2.75F, -7.625F, -0.35F, 1.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, -13.275F, -0.25F, 4.0F, 11.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, -8.0F, 0.0F, 0.3927F, 0.0F));

		ModelPartData group15 = group22.addChild("group15", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData group14 = group15.addChild("group14", ModelPartBuilder.create().uv(0, 0).cuboid(13.0F, -3.9F, -8.2F, 3.0F, 7.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(16.15F, -0.65F, -8.45F, 0.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(16.15F, -0.65F, -8.3F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(16.15F, 0.5F, -8.3F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-14.3F, -2.05F, 3.2F));

		ModelPartData group13 = group15.addChild("group13", ModelPartBuilder.create().uv(0, 0).cuboid(2.7F, -13.15F, 3.0F, 7.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(6.3F, -12.95F, 3.0F, 0.0F, 7.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.7F, -12.95F, 5.5F, 7.0F, 7.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.5F, -13.15F, 3.0F, 0.0F, 8.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.5F, -3.75F, 3.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(4.25F, -3.75F, 3.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.5F, -5.15F, 3.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(8.85F, -12.975F, 2.0F, 1.0F, 11.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(8.85F, -13.175F, 2.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 7.0F, -8.0F));

		ModelPartData cube_r30 = group13.addChild("cube_r30", ModelPartBuilder.create().uv(0, 0).cuboid(-0.45F, -8.75F, -0.1F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.35F, -8.75F, -0.1F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.05F, -3.35F, -0.15F, 2.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, 1.65F, -0.15F, 2.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, -9.8F, -0.15F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.1F, -9.625F, -0.025F, 2.0F, 11.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(8.3956F, -3.325F, 2.3501F, 0.0F, 0.3927F, 0.0F));

		ModelPartData group12 = root.addChild("group12", ModelPartBuilder.create(), ModelTransform.pivot(-8.0F, 16.0F, 8.0F));

		ModelPartData group11 = group12.addChild("group11", ModelPartBuilder.create().uv(0, 0).cuboid(-0.15F, -5.125F, 0.25F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.25F, -5.225F, 0.25F, 0.0F, 0.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.25F, -9.475F, 0.25F, 0.0F, 0.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.15F, -9.875F, 3.9F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.25F, -12.25F, 0.25F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.1F, -4.175F, 3.25F, 2.0F, 3.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.2F, -3.825F, 3.55F, 2.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.6F, -3.825F, 3.45F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.2F, -3.825F, 3.45F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.6F, -5.125F, 2.75F, 3.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.6F, -4.375F, 2.5F, 7.0F, 4.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.6F, -5.125F, 1.0F, 0.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.45F, -5.075F, 1.6F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.725F, -5.175F, 2.1F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.35F, -5.175F, 1.875F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.725F, -5.175F, 1.5F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.95F, -5.175F, 1.875F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.75F, -5.175F, 1.875F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.85F, -5.075F, 1.5F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.6F, -4.875F, 0.25F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.225F, -5.175F, 1.4F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.225F, -5.175F, 2.2F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.55F, -5.175F, 1.875F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.6F, -5.125F, 0.25F, 3.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.7F, -7.6796F, 0.2206F, 7.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.85F, -5.125F, 0.25F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.95F, -5.15F, 0.35F, 1.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.35F, -5.125F, 2.75F, 2.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.6F, -4.375F, 0.25F, 7.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.9F, -2.475F, 0.25F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(5.35F, -5.125F, 0.25F, 2.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(6.2785F, -5.9F, 0.7465F, 0.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.35F, -5.125F, 0.25F, 0.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.35F, 5.875F, -14.0F));

		ModelPartData cube_r31 = group11.addChild("cube_r31", ModelPartBuilder.create().uv(0, 0).cuboid(-0.075F, -0.2402F, -0.2973F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.075F, -0.4711F, -0.2017F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.3535F, -5.7953F, 1.025F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r32 = group11.addChild("cube_r32", ModelPartBuilder.create().uv(0, 0).cuboid(-0.075F, -0.3581F, -0.3666F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.3535F, -5.7953F, 1.025F, -0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r33 = group11.addChild("cube_r33", ModelPartBuilder.create().uv(0, 0).cuboid(-0.075F, -0.299F, 0.2173F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.3535F, -5.7953F, 1.025F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r34 = group11.addChild("cube_r34", ModelPartBuilder.create().uv(0, 0).cuboid(-3.55F, -0.75F, -0.1F, 7.0F, 0.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.25F, -5.125F, 0.6F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r35 = group11.addChild("cube_r35", ModelPartBuilder.create().uv(0, 0).cuboid(-3.1F, -1.25F, -0.1F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.2F, -5.125F, 1.0F, 0.3927F, 0.0F, 0.0F));

		ModelPartData group10 = group12.addChild("group10", ModelPartBuilder.create().uv(0, 0).cuboid(4.0F, -3.675F, -13.75F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(4.0F, -6.375F, -13.75F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.65F, -6.375F, -13.75F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(6.85F, -6.375F, -13.75F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r36 = group10.addChild("cube_r36", ModelPartBuilder.create().uv(0, 0).cuboid(-0.725F, -1.2375F, -0.05F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.6889F, -5.0375F, -12.2688F, 0.0F, -0.3927F, 0.0F));

		ModelPartData cube_r37 = group10.addChild("cube_r37", ModelPartBuilder.create().uv(0, 0).cuboid(-1.45F, -1.3375F, -0.05F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.9F, -4.9375F, -12.55F, 0.0F, 0.3927F, 0.0F));

		ModelPartData group9 = root.addChild("group9", ModelPartBuilder.create(), ModelTransform.pivot(-8.0F, 16.0F, 8.0F));

		ModelPartData group8 = group9.addChild("group8", ModelPartBuilder.create().uv(0, 0).cuboid(13.65F, 1.694F, -13.0766F, 5.0F, 1.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.4F, 0.494F, -10.0766F, 8.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.4F, 0.494F, -7.0766F, 8.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.4F, 0.494F, -4.5766F, 8.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.4F, 0.494F, -13.0766F, 8.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.4F, 0.244F, -5.3266F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-22.1F, 2.306F, -0.6734F));

		ModelPartData cube_r38 = group8.addChild("cube_r38", ModelPartBuilder.create().uv(0, 0).cuboid(0.4F, -0.5F, -0.75F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-3.35F, -0.5F, -0.75F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(16.5F, 0.0F, -12.5F, -0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r39 = group8.addChild("cube_r39", ModelPartBuilder.create().uv(0, 0).cuboid(0.4F, -0.75F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-3.35F, -0.75F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(16.5F, 0.0F, -2.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r40 = group8.addChild("cube_r40", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -0.75F, 0.65F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.5F, -0.75F, -3.85F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, -7.25F, 0.0F, 0.0F, -0.7854F));

		ModelPartData group7 = group9.addChild("group7", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData group6 = group7.addChild("group6", ModelPartBuilder.create().uv(0, 0).cuboid(-6.5F, -8.55F, 5.55F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-6.5F, -8.55F, 3.3F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-6.5F, -8.55F, 0.425F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-6.5F, -8.55F, -2.45F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-6.5F, -5.7F, -5.75F, 1.0F, 0.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-6.4F, -8.6F, -5.75F, 0.0F, 2.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-6.5F, -8.75F, -5.75F, 1.0F, 0.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-5.075F, -8.75F, -5.75F, 7.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-5.075F, -7.2F, -5.75F, 7.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.15F, -8.75F, -5.75F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-5.25F, -8.75F, -5.75F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.9134F, 2.3806F, -8.0F));

		ModelPartData group5 = group7.addChild("group5", ModelPartBuilder.create(), ModelTransform.pivot(-22.2634F, -6.1444F, 4.5F));

		ModelPartData cube_r41 = group5.addChild("cube_r41", ModelPartBuilder.create().uv(0, 0).cuboid(2.4F, -0.2F, -0.5F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.4F, -0.2F, 0.45F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.4F, -0.3F, -0.5F, 0.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.025F, -0.1F, -1.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, -8.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData group4 = group7.addChild("group4", ModelPartBuilder.create(), ModelTransform.pivot(-22.2634F, -6.1444F, 4.5F));

		ModelPartData cube_r42 = group4.addChild("cube_r42", ModelPartBuilder.create().uv(0, 0).cuboid(0.1F, 2.4F, -3.05F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.1F, 2.4F, -2.1F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.2F, 2.4F, -3.05F, 0.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.1F, -0.025F, -3.875F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, -8.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData group3 = group7.addChild("group3", ModelPartBuilder.create(), ModelTransform.pivot(-22.2634F, -6.1444F, 4.5F));

		ModelPartData cube_r43 = group3.addChild("cube_r43", ModelPartBuilder.create().uv(0, 0).cuboid(0.1F, 2.4F, -5.95F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.1F, 2.4F, -5.0F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.2F, 2.4F, -5.95F, 0.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.1F, -0.025F, -6.75F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, -8.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData group2 = group7.addChild("group2", ModelPartBuilder.create(), ModelTransform.pivot(-22.2634F, -6.1444F, 4.5F));

		ModelPartData cube_r44 = group2.addChild("cube_r44", ModelPartBuilder.create().uv(0, 0).cuboid(0.1F, 2.4F, -8.5F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.1F, 2.4F, -7.55F, 0.0F, 0.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.2F, 2.4F, -8.5F, 0.0F, 0.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.1F, -0.025F, -9.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, -8.0F, 0.0F, 0.0F, -0.3927F));

		return TexturedModelData.of(modelData, 16, 16);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		matrices.push();
		matrices.multiply(new Quaternionf().rotationY((float) Math.toRadians(-90f)));
		matrices.translate(3.125, -4.55, 0);
		matrices.scale(3, 3, 3);
		group.render(matrices, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		group22.render(matrices, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		group12.render(matrices, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		group9.render(matrices, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		matrices.pop();
	}
}