// Made with Blockbench 4.5.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class custom_model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custom_model"), "main");
	private final ModelPart bb_main;

	public custom_model(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -4.0F, -6.0F, 20.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).addBox(-1.0F, -4.1F, -6.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 21).addBox(-2.0F, -3.1F, -6.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(13, 25).addBox(3.0F, -3.1F, -6.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 25).addBox(3.0F, -3.1F, 5.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -4.1F, 5.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 4).addBox(-2.0F, -3.1F, 5.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-9.0F, -7.0F, -6.0F, 18.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(0.5F, -2.0F, -5.5F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 15).addBox(-9.0F, -7.0F, 6.0F, 18.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(26, 26).addBox(9.0F, -7.0F, -6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(5, 24).addBox(9.0F, -7.0F, 5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(13, 10).addBox(10.0F, -5.0F, -5.0F, 0.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(13, 9).addBox(10.0F, -7.0F, -5.0F, 0.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(5, 7).addBox(10.0F, -6.0F, 2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 2).addBox(10.0F, -6.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(4, 4).addBox(10.0F, -6.0F, -3.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(-9.0F, -5.0F, 6.0F, 18.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-9.0F, -5.0F, -6.0F, 18.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(23, 23).addBox(-10.0F, -7.0F, 5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 23).addBox(-10.0F, -7.0F, -6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 21).addBox(6.0F, -6.0F, -6.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(9, 17).addBox(3.0F, -6.0F, -6.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(17, 27).addBox(-1.0F, -6.0F, -6.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(10, 7).addBox(-4.0F, -6.0F, -6.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(5, 10).addBox(-7.0F, -6.0F, -6.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(10, 3).addBox(6.0F, -6.0F, 6.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(10, 1).addBox(3.0F, -6.0F, 6.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(9, 2).addBox(-4.0F, -6.0F, 6.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(9, 0).addBox(-7.0F, -6.0F, 6.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(5, 17).addBox(-1.0F, -6.0F, 6.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(13, 8).addBox(-10.0F, -7.0F, -5.0F, 0.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-10.0F, -6.0F, -3.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).addBox(-10.0F, -6.0F, 2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(13, 7).addBox(-10.0F, -5.0F, -5.0F, 0.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(6, 8).addBox(6.0F, -3.6F, 5.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(6.0F, -3.6F, -6.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 5).addBox(9.5F, -3.6F, -6.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(9.5F, -3.6F, 4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-19.0F, -4.0F, -0.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}