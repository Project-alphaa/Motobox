package motobox.vehicle.render.frame;// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import motobox.Motobox;
import motobox.entity.VehicleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

public class RustyCarFrameModel extends EntityModel<VehicleEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("frame_rusty_car"), "main");
	private final ModelPart group;

	public RustyCarFrameModel(EntityRendererFactory.Context root) {
		super(RenderLayer::getEntityTranslucent);
		this.group = root.getPart(MODEL_LAYER).getChild("group");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		ModelPartData group = root.addChild("group", ModelPartBuilder.create().uv(93, 100).cuboid(-13.5F, -2.5F, -47.5F, 35.0F, 2.0F, 11.0F, new Dilation(-1.0F, 0.0F, -1.0F))
				.uv(77, 116).cuboid(1.0F, -2.5F, -47.5F, 6.0F, 2.0F, 1.0F, new Dilation(3.0F, 0.0F, 0.0F))
				.uv(0, 116).cuboid(-13.0F, -5.0F, -45.0F, 34.0F, 3.0F, 8.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(85, 114).cuboid(-14.0F, -9.0F, -45.0F, 36.0F, 4.0F, 8.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(95, 61).cuboid(-13.0F, -4.0F, -46.0F, 9.0F, 2.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 90).cuboid(12.0F, -4.0F, -46.0F, 9.0F, 2.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(97, 87).cuboid(-13.0F, -5.0F, -46.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 178).cuboid(-4.0F, -11.0F, -46.5F, 1.0F, 9.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(19, 87).cuboid(20.0F, -5.0F, -46.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(121, 187).cuboid(20.0F, -8.0F, -46.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(19, 56).cuboid(-14.0F, -8.0F, -46.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 128).cuboid(11.0F, -11.0F, -46.5F, 1.0F, 9.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(197, 130).cuboid(-3.0F, -10.5F, -46.0F, 14.0F, 9.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(19, 43).cuboid(3.5F, -11.0F, -46.5F, 1.0F, 10.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(99, 177).cuboid(-4.0F, -11.25F, -44.5F, 16.0F, 2.0F, 1.0F, new Dilation(0.025F, 0.0F, 0.0F))
				.uv(0, 64).cuboid(-14.0F, -9.0F, -46.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 61).cuboid(12.0F, -9.0F, -46.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(19, 82).cuboid(-8.5F, -7.0F, -45.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(81, 100).cuboid(-12.0F, -8.0F, -45.5F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(95, 65).cuboid(12.0F, -8.0F, -45.5F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(95, 65).cuboid(11.0F, -9.0F, -19.0F, 8.0F, 1.0F, 1.0F, new Dilation(-3.5F, 1.5F, 0.0F))
				.uv(95, 65).cuboid(6.0F, -9.0F, -19.0F, 8.0F, 1.0F, 1.0F, new Dilation(-3.5F, 1.5F, 0.0F))
				.uv(95, 65).cuboid(8.5F, -9.5F, -19.0F, 8.0F, 1.0F, 1.0F, new Dilation(-2.0F, 0.0F, 0.0F))
				.uv(95, 65).cuboid(8.5F, -6.5F, -19.0F, 8.0F, 1.0F, 1.0F, new Dilation(-2.0F, 0.0F, 0.0F))
				.uv(95, 65).cuboid(8.5F, -11.5F, -19.0F, 8.0F, 1.0F, 1.0F, new Dilation(-2.0F, 0.0F, 0.0F))
				.uv(19, 77).cuboid(15.5F, -7.0F, -45.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(111, 29).cuboid(-14.0F, -10.0F, -45.0F, 36.0F, 1.0F, 8.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(95, 43).cuboid(-14.0F, -11.0F, -37.0F, 36.0F, 3.0F, 14.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(193, 179).cuboid(-4.0F, -11.0F, -44.0F, 16.0F, 1.0F, 7.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(35, 35).cuboid(3.5F, -12.0F, -46.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(81, 184).cuboid(2.5F, -15.0F, -46.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(154, 152).cuboid(-13.5F, -4.5F, -40.5F, 35.0F, 2.0F, 3.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(166, 114).cuboid(-14.0F, -8.0F, -37.0F, 36.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(140, 131).cuboid(-7.0F, -8.0F, -37.0F, 22.0F, 8.0F, 12.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(125, 171).cuboid(-4.0F, -11.5F, -37.0F, 16.0F, 1.0F, 14.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(77, 152).cuboid(-14.0F, -8.0F, -25.0F, 36.0F, 3.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(151, 39).cuboid(-14.0F, -8.0F, -26.0F, 36.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(193, 6).cuboid(7.0F, -5.0F, -16.0F, 11.0F, 5.0F, 9.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 0).cuboid(-13.5F, -1.5F, -24.5F, 35.0F, 2.0F, 40.0F, new Dilation(-1.0F, 0.0F, 0.0F))
				.uv(0, 149).cuboid(-14.0F, -10.0F, 37.0F, 36.0F, 7.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(111, 0).cuboid(-14.0F, -8.0F, 28.0F, 36.0F, 5.0F, 9.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(142, 127).cuboid(-14.0F, -8.0F, 16.0F, 36.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(121, 96).cuboid(-14.0F, -8.0F, 27.0F, 36.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 100).cuboid(-13.0F, -3.5F, 28.0F, 34.0F, 3.0F, 12.0F, new Dilation(0.0F, -0.5F, 0.0F))
				.uv(34, 0).cuboid(21.0F, -10.0F, 39.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(11, 0).cuboid(-14.0F, -10.0F, 39.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(97, 72).cuboid(-13.5F, -2.5F, 28.5F, 35.0F, 2.0F, 12.0F, new Dilation(-1.0F, 0.0F, 0.0F))
				.uv(174, 120).cuboid(-13.0F, -10.0F, 39.0F, 34.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(121, 61).cuboid(-13.0F, -5.5F, 11.0F, 34.0F, 5.0F, 5.0F, new Dilation(0.0F, -0.5F, 0.0F))
				.uv(97, 87).cuboid(-14.0F, -8.0F, 11.0F, 36.0F, 3.0F, 5.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 72).cuboid(-14.0F, -11.0F, 11.0F, 36.0F, 3.0F, 24.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 43).cuboid(-13.0F, -21.435F, -13.3934F, 34.0F, 2.0F, 26.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(166, 117).cuboid(-13.0F, -4.0F, 39.0F, 34.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(174, 123).cuboid(-10.0F, -5.5F, 38.5F, 28.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(75, 148).cuboid(-10.0F, -8.5F, 38.5F, 28.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(111, 29).cuboid(9.0F, -9.0F, 39.0F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 72).cuboid(-3.0F, -9.0F, 39.0F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(139, 158).cuboid(-14.0F, -10.0F, 35.0F, 36.0F, 2.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(69, 143).cuboid(-12.0F, -11.0F, 35.0F, 32.0F, 1.0F, 3.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(95, 43).cuboid(-13.025F, -20.0208F, 10.9792F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 100).cuboid(-12.975F, -18.0208F, 12.9792F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(38, 184).cuboid(-12.975F, -16.0208F, 14.9792F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(172, 179).cuboid(-12.975F, -14.0208F, 16.9792F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(77, 120).cuboid(-12.975F, -12.0208F, 18.9792F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(29, 23).cuboid(19.025F, -20.0208F, 10.9792F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(97, 72).cuboid(18.975F, -18.0208F, 12.9792F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(182, 42).cuboid(18.975F, -16.0208F, 14.9792F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(142, 130).cuboid(18.975F, -14.0208F, 16.9792F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(114, 66).cuboid(18.975F, -12.0208F, 18.9792F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 128).cuboid(-7.0F, -8.0F, 16.0F, 22.0F, 8.0F, 12.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(31, 178).cuboid(19.0F, -19.5F, -6.0F, 2.0F, 9.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(31, 178).cuboid(11.5F, -13.0F, -20.0F, 2.0F, 9.0F, 1.0F, new Dilation(0.0F, -3.5F, 0.0F))
				.uv(31, 178).cuboid(14.5F, -8.0F, -22.0F, 2.0F, 9.0F, 1.0F, new Dilation(0.0F, -3.5F, 0.0F))
				.uv(31, 178).cuboid(11.5F, -8.0F, -22.0F, 2.0F, 9.0F, 1.0F, new Dilation(0.0F, -3.5F, 0.0F))
				.uv(31, 178).cuboid(8.5F, -8.0F, -22.0F, 2.0F, 9.0F, 1.0F, new Dilation(0.0F, -3.5F, 0.0F))
				.uv(170, 179).cuboid(19.0F, -11.0F, -22.5F, 3.0F, 6.0F, 16.0F, new Dilation(0.0F, 0.0F, 0.5F))
				.uv(137, 187).cuboid(19.0F, -5.0F, -22.5F, 2.0F, 4.0F, 16.0F, new Dilation(0.0F, 0.0F, 0.5F))
				.uv(170, 179).cuboid(19.0F, -11.0F, -5.5F, 3.0F, 6.0F, 16.0F, new Dilation(0.0F, 0.0F, 0.5F))
				.uv(137, 187).cuboid(19.0F, -5.0F, -5.5F, 2.0F, 4.0F, 16.0F, new Dilation(0.0F, 0.0F, 0.5F))
				.uv(97, 106).cuboid(22.0F, -11.0F, -20.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(57, 132).cuboid(22.0F, -14.0F, -20.0F, 5.0F, 3.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(72, 184).cuboid(21.5F, -10.0F, -10.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(116, 61).cuboid(21.5F, -10.0F, 7.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(68, 201).cuboid(19.75F, -19.5F, -5.5F, 1.0F, 9.0F, 12.0F, new Dilation(0.0F, 0.0F, -0.5F))
				.uv(0, 72).cuboid(19.75F, -19.5F, -14.5F, 1.0F, 9.0F, 8.0F, new Dilation(0.0F, 0.0F, 0.5F))
				.uv(99, 181).cuboid(19.75F, -17.5F, -17.0F, 1.0F, 7.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(11, 43).cuboid(19.75F, -15.5F, -19.0F, 1.0F, 5.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(132, 179).cuboid(19.75F, -13.5F, -21.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(24, 178).cuboid(19.0F, -19.5F, 6.0F, 2.0F, 9.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(23, 0).cuboid(19.75F, -19.5F, 7.0F, 1.0F, 9.0F, 4.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(139, 163).cuboid(-13.0F, -5.5F, -25.0F, 34.0F, 5.0F, 2.0F, new Dilation(0.0F, -0.5F, 0.0F))
				.uv(172, 171).cuboid(-11.0F, -10.0F, -23.0F, 30.0F, 4.0F, 3.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(72, 130).cuboid(2.0F, -6.0F, -23.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(11, 9).cuboid(3.5F, -7.0F, -18.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(43, 159).cuboid(2.0F, -4.0F, -23.0F, 4.0F, 4.0F, 20.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(175, 99).cuboid(-10.0F, -12.0F, 9.0F, 28.0F, 7.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(75, 127).cuboid(-10.0F, -5.0F, 1.0F, 28.0F, 5.0F, 10.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 202).cuboid(7.0F, -13.5F, -7.0F, 11.0F, 12.0F, 3.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 23).cuboid(-10.0F, -13.5F, -7.0F, 11.0F, 12.0F, 3.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(182, 42).cuboid(-10.0F, -5.0F, -16.0F, 11.0F, 5.0F, 9.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(7, 178).cuboid(-13.0F, -19.5F, 6.0F, 2.0F, 9.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 0).cuboid(-12.75F, -19.5F, 7.0F, 1.0F, 9.0F, 4.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(29, 35).cuboid(-14.5F, -10.0F, 7.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(197, 190).cuboid(-12.75F, -19.5F, -5.5F, 1.0F, 9.0F, 12.0F, new Dilation(0.0F, 0.0F, -0.5F))
				.uv(0, 116).cuboid(-12.75F, -13.5F, -21.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 43).cuboid(-12.75F, -15.5F, -19.0F, 1.0F, 5.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(180, 72).cuboid(-12.75F, -17.5F, -17.0F, 1.0F, 7.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(111, 15).cuboid(-13.0F, -19.5F, -6.0F, 2.0F, 9.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(0, 43).cuboid(-12.75F, -19.5F, -14.5F, 1.0F, 9.0F, 8.0F, new Dilation(0.0F, 0.0F, 0.5F))
				.uv(11, 77).cuboid(-16.0F, -11.0F, -20.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(11, 72).cuboid(-19.0F, -14.0F, -20.0F, 5.0F, 3.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(31, 11).cuboid(-14.5F, -10.0F, -10.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(100, 185).cuboid(-13.0F, -5.0F, -22.5F, 2.0F, 4.0F, 16.0F, new Dilation(0.0F, 0.0F, 0.5F))
				.uv(0, 0).cuboid(-14.0F, -11.0F, -22.5F, 3.0F, 6.0F, 16.0F, new Dilation(0.0F, 0.0F, 0.5F))
				.uv(0, 0).cuboid(-14.0F, -11.0F, -5.5F, 3.0F, 6.0F, 16.0F, new Dilation(0.0F, 0.0F, 0.5F))
				.uv(100, 185).cuboid(-13.0F, -5.0F, -5.5F, 2.0F, 4.0F, 16.0F, new Dilation(0.0F, 0.0F, 0.5F)), ModelTransform.pivot(-12.0F, 21.0F, 12.0F));

		group.addChild("cube_r1", ModelPartBuilder.create().uv(0, 159).cuboid(-18.0F, -25.0F, -3.75F, 30.0F, 17.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(174, 202).cuboid(-20.0F, -25.0F, -4.0F, 2.0F, 17.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(183, 202).cuboid(12.0F, -25.0F, -4.0F, 2.0F, 17.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F)), ModelTransform.of(7.0F, -0.9289F, -28.2426F, -0.7854F, 0.0F, 0.0F));

		group.addChild("cube_r2", ModelPartBuilder.create().uv(76, 158).cuboid(-12.0F, -25.0F, 2.75F, 30.0F, 17.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(29, 202).cuboid(-14.0F, -25.0F, 2.0F, 2.0F, 17.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F))
				.uv(202, 57).cuboid(18.0F, -25.0F, 2.0F, 2.0F, 17.0F, 2.0F, new Dilation(0.0F, 0.0F, 0.0F)), ModelTransform.of(1.0F, -0.9289F, 27.2426F, 0.7854F, 0.0F, 0.0F));

		group.addChild("cube_r3", ModelPartBuilder.create().uv(115, 181).cuboid(-3.5F, 0.0F, -1.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F)), ModelTransform.of(12.8509F, -2.5F, -45.2367F, 0.0F, -0.3927F, 0.0F));

		group.addChild("cube_r4", ModelPartBuilder.create().uv(186, 109).cuboid(0.5F, 0.0F, -1.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F, 0.0F, 0.0F)), ModelTransform.of(-4.8509F, -2.5F, -45.2367F, 0.0F, 0.3927F, 0.0F));

		return TexturedModelData.of(modelData, 256, 256);
	}

	@Override
	public void setAngles(VehicleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.push();
		poseStack.translate(8 / 16.0, -17.5 / 16.0, -8 / 16.0);
		group.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.pop();
	}
}