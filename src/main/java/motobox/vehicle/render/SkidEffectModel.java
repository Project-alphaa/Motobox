package motobox.vehicle.render;// Made with Blockbench 4.5.2
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
import net.minecraft.util.Identifier;

public class SkidEffectModel<T extends VehicleEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("vehicle_skid_effect"), "main");

	public static final Identifier[] COOL_SPARK_TEXTURES = new Identifier[]{
			Motobox.id("textures/entity/vehicle/skid_effect/skid_cool_sparks_0.png"),
			Motobox.id("textures/entity/vehicle/skid_effect/skid_cool_sparks_1.png"),
			Motobox.id("textures/entity/vehicle/skid_effect/skid_cool_sparks_2.png")
	};
	public static final Identifier[] HOT_SPARK_TEXTURES = new Identifier[]{
			Motobox.id("textures/entity/vehicle/skid_effect/skid_hot_sparks_0.png"),
			Motobox.id("textures/entity/vehicle/skid_effect/skid_hot_sparks_1.png"),
			Motobox.id("textures/entity/vehicle/skid_effect/skid_hot_sparks_2.png")
	};
	public static final Identifier[] FLAME_TEXTURES = new Identifier[]{
			Motobox.id("textures/entity/vehicle/skid_effect/skid_flames_0.png"),
			Motobox.id("textures/entity/vehicle/skid_effect/skid_flames_1.png"),
			Motobox.id("textures/entity/vehicle/skid_effect/skid_flames_2.png")
	};
	public static final Identifier[] DEBRIS_TEXTURES = new Identifier[]{
			Motobox.id("textures/entity/vehicle/skid_effect/skid_debris_0.png"),
			Motobox.id("textures/entity/vehicle/skid_effect/skid_debris_1.png"),
			Motobox.id("textures/entity/vehicle/skid_effect/skid_debris_2.png")
	};

	private final ModelPart main;

	public SkidEffectModel(EntityRendererFactory.Context root) {
		this.main = root.getPart(MODEL_LAYER).getChild("main");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		ModelPartData main = root.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData sect_1 = main.addChild("sect_1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -7.0F, 0.0F, 0.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1789F, 0.2489F, 0.6333F));

		ModelPartData sect_3 = main.addChild("sect_3", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -7.0F, -4.0F, 0.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.3591F, 0.87F, 1.3646F));

		ModelPartData sect_2 = main.addChild("sect_2", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -7.0F, -2.0F, 0.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.5649F, 0.6342F, 0.7179F));

		return TexturedModelData.of(modelData, 16, 16);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}