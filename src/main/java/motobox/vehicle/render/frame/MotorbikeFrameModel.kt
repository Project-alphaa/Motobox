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
class MotorbikeFrameModel(root: ModelPart) : EntityModel<VehicleEntity>(Function(RenderLayer::getEntityTranslucent)) {
    private val bone7: ModelPart

    init {
        bone7 = root.getChild("bone7")
    }

    constructor(root: EntityRendererFactory.Context) : this(root.getPart(MODEL_LAYER))

    override fun setAngles(
            entity: VehicleEntity?,
            limbSwing: Float,
            limbSwingAmount: Float,
            ageInTicks: Float,
            netHeadYaw: Float,
            headPitch: Float
    ) {
    }

    override fun render(
        poseStack: MatrixStack,
        vertexConsumer: VertexConsumer,
        packedLight: Int,
        packedOverlay: Int,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float
    ) {
        poseStack.push()
        poseStack.translate(0.0, -18.20 / 16.0, 2.15 / 16.0)
        bone7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha)
        poseStack.pop()
    }

    companion object {
        // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
        @JvmField
        val MODEL_LAYER = EntityModelLayer(Motobox.id("frame_motorbike"), "main")

        @JvmStatic
        val texturedModelData: TexturedModelData
            get() {
                val modelData = ModelData()
                val root = modelData.root
                val bone7 = root.addChild(
                    "bone7",
                    ModelPartBuilder.create()
                        .uv(32, 32).cuboid(-1.0f, -1.75f, -9.4f, 2.0f, 2.0f, 9.0f, Dilation(0.0f, 0.0f, 0.0f))
                        .uv(19, 30).cuboid(-1.0f, -1.75f, 16.6f, 2.0f, 2.0f, 9.0f, Dilation(0.0f, 0.0f, 0.0f))
                        .uv(0, 0).cuboid(-3.0f, -3.75f, 5.6f, 6.0f, 7.0f, 8.0f, Dilation(0.0f, 0.0f, 0.0f))
                        .uv(0, 15).cuboid(-1.0f, -9.75f, -2.4f, 2.0f, 3.0f, 1.0f, Dilation(0.0f, 0.0f, 0.0f))
                        .uv(0, 15).cuboid(-4.0f, -4.0f, 5.6f, 8.0f, 1.0f, 8.0f, Dilation(0.0f, 0.25f, 0.0f))
                        .uv(24, 9).cuboid(-2.5f, 2.375f, 5.6f, 5.0f, 3.0f, 8.0f, Dilation(0.25f, 0.125f, 0.25f))
                        .uv(0, 48).cuboid(2.375f, 3.375f, 13.85f, 2.0f, 2.0f, 3.0f, Dilation(0.125f, 0.125f, 0.0f))
                        .uv(10, 48).cuboid(-3.625f, 3.375f, 13.85f, 2.0f, 2.0f, 3.0f, Dilation(0.125f, 0.125f, 0.0f))
                        .uv(31, 54).cuboid(-3.5f, 5.875f, 4.6f, 7.0f, 0.0f, 9.0f, Dilation(0.25f, 0.425f, 0.25f))
                        .uv(0, 24).cuboid(-3.0f, -5.75f, 13.6f, 6.0f, 5.0f, 8.0f, Dilation(0.0f, 0.0f, 0.0f))
                        .uv(24, 20).cuboid(-2.0f, -6.75f, 16.6f, 4.0f, 2.0f, 8.0f, Dilation(0.0f, 0.0f, 0.0f)),
                    ModelTransform.pivot(0.0f, 12.75f, -10.6f)
                )
                bone7.addChild(
                    "bone6",
                    ModelPartBuilder.create()
                        .uv(0, 4).cuboid(-0.5f, -0.5f, -1.5f, 1.0f, 1.0f, 3.0f, Dilation(0.125f, 0.125f, 0.0f))
                        .uv(0, 0).cuboid(5.5f, -0.5f, -1.5f, 1.0f, 1.0f, 3.0f, Dilation(0.125f, 0.125f, 0.0f)),
                    ModelTransform.of(-2.625f, 3.875f, 17.85f, 0.48f, 0.0f, 0.0f)
                )
                bone7.addChild(
                    "bone5",
                    ModelPartBuilder.create()
                        .uv(42, 0).cuboid(-1.0f, -34.2f, 1.95f, 2.0f, 2.0f, 5.0f, Dilation(0.1f, 0.0f, 0.0f)),
                    ModelTransform.of(0.0f, 21.0f, 0.0f, -0.7854f, 0.0f, 0.0f)
                )
                bone7.addChild("bone4", ModelPartBuilder.create(), ModelTransform.pivot(0.0f, 11.25f, 10.6f))
                bone7.addChild(
                    "bone3",
                    ModelPartBuilder.create()
                        .uv(26, 41).cuboid(1.5f, -9.0f, -0.5f, 1.0f, 18.0f, 1.0f, Dilation(0.0f, 0.0f, 0.0f))
                        .uv(0, 19).cuboid(-1.5f, 7.0f, -0.5f, 3.0f, 1.0f, 1.0f, Dilation(0.0f, 0.0f, 0.0f))
                        .uv(30, 43).cuboid(-1.5f, -10.0f, -1.5f, 3.0f, 7.0f, 2.0f, Dilation(0.0f, 0.0f, 0.0f))
                        .uv(40, 20).cuboid(-1.5f, -6.0f, 0.5f, 3.0f, 3.0f, 5.0f, Dilation(0.0f, 0.0f, 0.0f))
                        .uv(0, 37).cuboid(-3.5f, -7.0f, 5.5f, 7.0f, 7.0f, 4.0f, Dilation(0.0f, 0.0f, 0.0f))
                        .uv(32, 30).cuboid(-4.5f, -11.0f, -0.5f, 9.0f, 1.0f, 1.0f, Dilation(0.0f, 0.0f, 0.0f))
                        .uv(20, 26).cuboid(4.5f, -11.0f, -0.5f, 4.0f, 1.0f, 1.0f, Dilation(0.0f, 0.25f, 0.25f))
                        .uv(20, 24).cuboid(-8.5f, -11.0f, -0.5f, 4.0f, 1.0f, 1.0f, Dilation(0.0f, 0.25f, 0.25f))
                        .uv(22, 41).cuboid(-2.5f, -9.0f, -0.5f, 1.0f, 18.0f, 1.0f, Dilation(0.0f, 0.0f, 0.0f)),
                    ModelTransform.of(0.0f, -1.75f, -2.9f, -0.3491f, 0.0f, 0.0f)
                )
                bone7.addChild(
                    "bone2",
                    ModelPartBuilder.create()
                        .uv(45, 32).cuboid(-1.0f, 8.0f, -0.5f, 2.0f, 2.0f, 4.0f, Dilation(0.1f, 0.0f, 0.0f))
                        .uv(40, 43).cuboid(-1.0f, 26.25f, 17.25f, 2.0f, 2.0f, 4.0f, Dilation(0.1f, 0.0f, 0.0f)),
                    ModelTransform.of(0.0f, -5.0f, -17.5f, 0.7854f, 0.0f, 0.0f)
                )
                bone7.addChild(
                    "bone",
                    ModelPartBuilder.create()
                        .uv(42, 7).cuboid(-1.0f, -1.0f, -1.5f, 2.0f, 2.0f, 5.0f, Dilation(0.1f, 0.0f, 0.0f)),
                    ModelTransform.of(0.0f, 0.0f, 0.0f, -0.7854f, 0.0f, 0.0f)
                )
                return TexturedModelData.of(modelData, 64, 64)
            }
    }
}