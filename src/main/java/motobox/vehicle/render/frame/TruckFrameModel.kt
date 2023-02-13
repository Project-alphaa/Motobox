package motobox.vehicle.render.frame

import motobox.Motobox
import motobox.entity.VehicleEntity
import net.minecraft.client.model.*
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.util.math.MatrixStack
import java.util.function.Function

/**
 * Made with Blockbench 4.5.2
 * Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
 * Paste this class into your mod and generate all required imports
 */
class TruckFrameModel(root: ModelPart) : EntityModel<VehicleEntity>(Function(RenderLayer::getEntityTranslucent)) {
    private val bone10: ModelPart

    init {
        bone10 = root.getChild("bone10")
    }

    constructor(context: EntityRendererFactory.Context) : this(context.getPart(MODEL_LAYER))

    override fun setAngles(
            entity: VehicleEntity,
            limbSwing: Float,
            limbSwingAmount: Float,
            ageInTicks: Float,
            netHeadYaw: Float,
            headPitch: Float
    ) {
    }

    override fun render(
        matrices: MatrixStack,
        vertexConsumer: VertexConsumer?,
        packedLight: Int,
        packedOverlay: Int,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float
    ) {
        matrices.push()
        run {
            matrices.translate(0.0, -1.30, -0.3)
            bone10.render(matrices, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha)
        }
        matrices.pop()
    }

    companion object {
        // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
        @JvmField
        val MODEL_LAYER = EntityModelLayer(Motobox.id("frame_truck"), "main")

        @JvmStatic
        val texturedModelData: TexturedModelData
            get() {
                val modelData: ModelData = ModelData()
                val root: ModelPartData = modelData.root
                val bone10 = root.addChild(
                    "bone10",
                    ModelPartBuilder.create().uv(72, 98).cuboid(22.5f, -9.0f, -16.0f, 5.5f, 4.0f, 32.0f, Dilation(0.0f))
                        .uv(106, 31).cuboid(23.25f, -5.0f, -16.0f, 5.0f, 3.0f, 32.0f, Dilation(0.25f, 0f, 0f))
                        .uv(0, 96).cuboid(7.5f, -8.625f, -16.0f, 4f, 7.0f, 32.0f, Dilation(0.25f, 0.375f, 0f))
                        .uv(68, 66).cuboid(-10.0f, -3.0f, -15.0f, 17.0f, 2.0f, 30.0f, Dilation(0.0f))
                        .uv(148, 41).cuboid(-9.0f, -7.0f, -15.0f, 11.0f, 3.0f, 12.0f, Dilation(0.0f))
                        .uv(114, 102).cuboid(-9.0f, -15.0f, 3.0f, 4.0f, 8.0f, 12.0f, Dilation(0.0f))
                        .uv(40, 113).cuboid(-9.0f, -7.0f, 3.0f, 11.0f, 3.0f, 12.0f, Dilation(0.0f))
                        .uv(0, 96).cuboid(-9.0f, -15.0f, -15.0f, 4.0f, 8.0f, 12.0f, Dilation(0.0f))
                        .uv(0, 36).cuboid(-35.0f, -3.0f, -14.0f, 25.0f, 2.0f, 28.0f, Dilation(0.0f))
                        .uv(0, 0).cuboid(-13.0f, -17.0f, -2.0f, 3.0f, 11.0f, 12.0f, Dilation(0.0f))
                        .uv(40, 98).cuboid(-22.0f, -6.0f, -2.0f, 12.0f, 3.0f, 12.0f, Dilation(0.0f))
                        .uv(76, 9).cuboid(-31.0f, -9.0f, -14.0f, 12.0f, 6.0f, 3.0f, Dilation(0.0f))
                        .uv(0, 0).cuboid(7.0f, -13.0f, -16.0f, 22.0f, 4.0f, 32.0f, Dilation(0.0f))
                        .uv(47, 134).cuboid(11.25f, -9.0f, -12.0f, 11.0f, 8.0f, 25.0f, Dilation(0.5f, 0f, 0f))
                        .uv(0, 83).cuboid(28.0f, -12.0f, 8.75f, 2.0f, 4.0f, 6.0f, Dilation(0.0f, 0f, 0.25f))
                        .uv(82, 82).cuboid(28.0f, -12.0f, -15.25f, 2.0f, 4.0f, 6.0f, Dilation(0.0f, 0f, 0.25f))
                        .uv(34, 135).cuboid(29.0f, -12.0f, -9.0f, 1.0f, 4.0f, 18.0f, Dilation(0.0f))
                        .uv(148, 125).cuboid(-10.0f, -13.0f, 15.0f, 17.0f, 7.0f, 2.0f, Dilation(0.0f))
                        .uv(132, 68).cuboid(4.0f, -13.0f, -15.0f, 3.0f, 4.0f, 30.0f, Dilation(0.0f))
                        .uv(70, 76).cuboid(4.0f, -9.0f, -3.0f, 3.0f, 6.0f, 6.0f, Dilation(0.0f))
                        .uv(0, 23).cuboid(-6.0f, -6.0f, -3.0f, 10.0f, 3.0f, 6.0f, Dilation(0.0f))
                        .uv(148, 116).cuboid(-10.0f, -13.0f, -17.0f, 17.0f, 7.0f, 2.0f, Dilation(0.0f))
                        .uv(0, 23).cuboid(5.0f, -18.0f, -15.0f, 2.0f, 5.0f, 0.0f, Dilation(0.0f))
                        .uv(18, 0).cuboid(5.0f, -18.0f, 15.0f, 2.0f, 5.0f, 0.0f, Dilation(0.0f))
                        .uv(76, 0).cuboid(-10.25f, -23.5f, -15f, 16.0f, 1.0f, 30.0f, Dilation(0.0f, 0f, 0.25f))
                        .uv(2, 2).cuboid(6.25f, -14.0f, 16.0f, 0f, 1.0f, 1.0f, Dilation(0.25f, 0f, 0f))
                        .uv(92, 36).cuboid(6.25f, -15.25f, 17.0f, 1.0f, 2.0f, 4.0f, Dilation(0.0f, 0.25f, 0f))
                        .uv(18, 85).cuboid(6.25f, -15.25f, -21.0f, 1.0f, 2f, 4.0f, Dilation(0.0f, 0.25f, 0f))
                        .uv(0, 2).cuboid(6.25f, -14.0f, -17.0f, 0f, 1.0f, 1.0f, Dilation(0.25f, 0f, 0f))
                        .uv(89, 142).cuboid(-10.0f, -13.0f, -15.0f, 1.0f, 10.0f, 30.0f, Dilation(0.0f))
                        .uv(0, 137).cuboid(-9.5f, -23.0f, -15.0f, 1.0f, 10.0f, 30.0f, Dilation(0.0f))
                        .uv(138, 5).cuboid(-10.0f, -6.0f, 15.0f, 17.0f, 4.0f, 1.0f, Dilation(0.0f))
                        .uv(138, 0).cuboid(-10.0f, -6.0f, -16.0f, 17.0f, 4.0f, 1.0f, Dilation(0.0f))
                        .uv(0, 50).cuboid(-19.25f, -13.0f, 14.0f, 9f, 12.0f, 2.0f, Dilation(0.25f, 0f, 0f))
                        .uv(138, 16).cuboid(-34.75f, -13.0f, 14.0f, 15.0f, 4.0f, 2.0f, Dilation(0.25f, 0f, 0f))
                        .uv(70, 88).cuboid(-34.75f, -9.0f, 14.0f, 4f, 4.0f, 2.0f, Dilation(0.25f, 0f, 0f))
                        .uv(10, 83).cuboid(-34.75f, -5.0f, 14.0f, 4f, 4.0f, 2.0f, Dilation(0.25f, 0f, 0f))
                        .uv(138, 10).cuboid(-34.75f, -13.0f, -16.0f, 15f, 4.0f, 2.0f, Dilation(0.25f, 0f, 0f))
                        .uv(82, 76).cuboid(-34.75f, -9.0f, -16.0f, 4f, 4.0f, 2.0f, Dilation(0.25f, 0f, 0f))
                        .uv(78, 48).cuboid(-34.75f, -5.0f, -16.0f, 4f, 4.0f, 2.0f, Dilation(0.25f, 0f, 0f))
                        .uv(0, 36).cuboid(-19.25f, -13.0f, -16.0f, 9f, 12.0f, 2.0f, Dilation(0.25f, 0f, 0f))
                        .uv(76, 0).cuboid(-31.0f, -9.0f, 11.0f, 12.0f, 6.0f, 3.0f, Dilation(0.0f))
                        .uv(80, 31).cuboid(-36.25f, -5.0f, -14.0f, 1.0f, 4.0f, 28.0f, Dilation(0.25f, 0f, 0f))
                        .uv(114, 102).cuboid(-36.25f, -13.0f, -16.0f, 1.0f, 8.0f, 32.0f, Dilation(0.25f, 0f, 0f))
                        .uv(76, 18).cuboid(-22.0f, -10.0f, -3.0f, 9.0f, 4.0f, 3.0f, Dilation(0.0f))
                        .uv(0, 76).cuboid(-22.0f, -10.0f, 8.0f, 9.0f, 4.0f, 3.0f, Dilation(0.0f))
                        .uv(0, 0).cuboid(3.0f, -13.0f, 6.0f, 1.0f, 4.0f, 4.0f, Dilation(0.0f))
                        .uv(18, 0).cuboid(2.0f, -14.0f, 5.0f, 1.0f, 6.0f, 6.0f, Dilation(0.0f))
                        .uv(121, 142).cuboid(-35.0f, -10.0f, -10.0f, 11.0f, 7.0f, 11.0f, Dilation(0.0f))
                        .uv(148, 102).cuboid(-35.0f, -13.0f, -10.0f, 11.0f, 3.0f, 11.0f, Dilation(0.0f))
                        .uv(0, 0).cuboid(-30.125f, -11.375f, 1.0f, 1f, 2f, 1.0f, Dilation(0.375f, 0.375f, 0f))
                        .uv(11, 199).cuboid(-40.0f, -3.0f, -3.0f, 1.0f, 1.0f, 4.0f, Dilation(0.0f))
                        .uv(1, 200).cuboid(-39.0f, -3.0f, -2.0f, 3.0f, 1.0f, 2.0f, Dilation(0.0f)),
                    ModelTransform.of(0.0f, 24.0f, 0.0f, 0.0f, 1.5708f, 0.0f)
                )
                bone10.addChild(
                    "bone2",
                    ModelPartBuilder.create().uv(0, 66)
                        .cuboid(-3.5f, -6.4217f, 16.9223f, 10.0f, 9.0f, 1.0f, Dilation(0.0f, 0.25f, 0f))
                        .uv(90, 46).cuboid(-8.75f, -7.1832f, 16.1608f, 5f, 10.0f, 2.0f, Dilation(0.25f, 0f, 0f)),
                    ModelTransform.of(-1.0f, -14.5f, -2.0f, 0.0873f, 0.0f, 0.0f)
                )
                bone10.addChild(
                    "bone3",
                    ModelPartBuilder.create().uv(22, 48)
                        .cuboid(6.6072f, -5.0556f, 15.8784f, 1f, 10.0f, 2.0f, Dilation(0.25f, 0f, 0f)),
                    ModelTransform.of(-1.0f, -14.5f, -2.0f, 0.0868f, 0.0091f, -0.2352f)
                )
                bone10.addChild(
                    "bone6",
                    ModelPartBuilder.create().uv(22, 36)
                        .cuboid(6.6088f, -5.6078f, -13.6284f, 1f, 10.0f, 2.0f, Dilation(0.25f, 0f, 0f)),
                    ModelTransform.of(-1.0f, -14.7f, -2.0f, -0.0698f, -0.0023f, -0.2006f)
                )
                bone10.addChild(
                    "bone4",
                    ModelPartBuilder.create().uv(148, 4)
                        .cuboid(7.4297f, -5.9888f, -11.375f, 1.0f, 10.0f, 27f, Dilation(0.0f, 0f, 0.375f)),
                    ModelTransform.of(-1.0f, -14.5f, -2.0f, 0.0f, 0.0f, -0.2182f)
                )
                bone10.addChild(
                    "bone5",
                    ModelPartBuilder.create().uv(70, 66)
                        .cuboid(-3.5f, -6.5572f, -13.6608f, 10.0f, 9f, 1.0f, Dilation(0.0f, 0.375f, 0f))
                        .uv(78, 36).cuboid(-8.75f, -7.1832f, -14.1608f, 5f, 10.0f, 2.0f, Dilation(0.25f, 0f, 0f)),
                    ModelTransform.of(-1.0f, -14.5f, -2.0f, -0.0873f, 0.0f, 0.0f)
                )
                bone10.addChild(
                    "bone",
                    ModelPartBuilder.create().uv(0, 66)
                        .cuboid(7.8272f, -1.7072f, -12.0f, 21.0f, 2.0f, 28.0f, Dilation(0.0f, 0.25f, 0f)),
                    ModelTransform.of(-1.0f, -14.5f, -2.0f, 0.0f, 0.0f, 0.0873f)
                )
                return TexturedModelData.of(modelData, 256, 256)
            }
    }
}