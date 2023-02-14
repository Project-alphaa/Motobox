package motobox.vehicle.render.attachment.rear;

import motobox.Motobox;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class TrailerRearAttachmentRenderModel extends RearAttachmentRenderModel {

    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Motobox.id("vehicle/rear_attachment/trailer"), "main");

    public TrailerRearAttachmentRenderModel(EntityRendererFactory.Context ctx) {
        super(RenderLayer::getEntityCutoutNoCull, ctx, MODEL_LAYER);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(56, 84).cuboid(3.0F, -25.0F, -1.0F, 16.0F, 15.0F, 16.0F, new Dilation(0.0F))
                .uv(0, 42).cuboid(-3.0F, -10.0F, -16.0F, 27.0F, 2.0F, 40.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(24.0F, -12.0F, 0.0F, 3.0F, 2.0F, 9.0F, new Dilation(0.0F))
                .uv(7, 3).cuboid(-6.0F, -12.0F, 0.0F, 3.0F, 2.0F, 9.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(9.0F, -9.0F, 23.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 34).cuboid(9.0F, -8.0F, -18.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(9.0F, -6.0F, -30.0F, 2.0F, 0.0F, 12.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(8.0F, -7.0F, 25.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F))
                .uv(16, 42).cuboid(-3.0F, -21.0F, 22.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F))
                .uv(8, 42).cuboid(-3.0F, -21.0F, -16.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 58).cuboid(-2.5F, -20.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 56).cuboid(-2.5F, -17.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 54).cuboid(-2.5F, -14.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 60).cuboid(22.5F, -11.0F, -14.25F, 1.0F, 1.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 48).cuboid(22.5F, -20.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 50).cuboid(22.5F, -17.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 52).cuboid(22.5F, -14.0F, -14.25F, 1.0F, 2.0F, 36.0F, new Dilation(0.0F))
                .uv(0, 61).cuboid(-2.5F, -11.0F, -14.25F, 1.0F, 1.0F, 36.0F, new Dilation(0.0F))
                .uv(31, 8).cuboid(22.0F, -21.0F, 22.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 42).cuboid(22.0F, -21.0F, -16.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.0F))
                .uv(94, 10).cuboid(-1.25F, -20.0F, -15.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 8).cuboid(-1.25F, -17.0F, -15.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 6).cuboid(-1.25F, -14.0F, -15.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 12).cuboid(-1.25F, -11.0F, 22.5F, 23.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 0).cuboid(-1.25F, -17.0F, 22.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 2).cuboid(-1.25F, -20.0F, 22.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 4).cuboid(-1.25F, -14.0F, 22.5F, 23.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 13).cuboid(-1.25F, -11.0F, -15.5F, 23.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, 0.0F));

        ModelPartData cube_r1 = main.addChild("cube_r1", ModelPartBuilder.create().uv(18, 22).cuboid(-1.5F, 2.7F, -5.5F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(22, 0).cuboid(-31.45F, 2.7F, -5.5F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(25.6F, -10.0F, 14.5F, -0.7854F, 0.0F, 0.0F));

        ModelPartData cube_r2 = main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 25).cuboid(-1.5F, 2.7F, -0.5F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(15, 14).cuboid(-31.45F, 2.7F, -0.5F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(25.6F, -10.0F, -5.5F, 0.7854F, 0.0F, 0.0F));

        ModelPartData wheels = main.addChild("wheels", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, -4.6F, 4.6F));

        ModelPartData wheel1 = wheels.addChild("wheel1", ModelPartBuilder.create().uv(191, 56).cuboid(23.805F, -5.2236F, -1.2148F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(191, 56).cuboid(23.805F, 2.831F, -1.2148F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(191, 56).cuboid(23.805F, -1.1963F, 2.8125F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(191, 56).cuboid(23.805F, -1.1963F, -5.2421F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(24.394F, -4.554F, -0.9928F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(24.394F, 2.5884F, -0.9928F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(24.394F, -0.9877F, 2.5735F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(24.394F, -0.9877F, -4.5689F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(24.2088F, -1.6856F, -0.3913F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(24.2088F, -0.8392F, -1.5449F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(24.394F, 1.424F, 0.3185F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(24.394F, -0.2067F, 1.361F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(24.394F, -3.2492F, -0.702F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(24.394F, 0.8137F, -3.3123F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r1 = wheel1.addChild("hexadecagon_r1", ModelPartBuilder.create().uv(155, 67).cuboid(-1.356F, 0.3008F, -1.6744F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, 1.2462F, -2.7466F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -1.8332F, -0.2543F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -2.9054F, -1.1997F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, 0.269F, -0.1203F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -0.9264F, 0.9519F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, 0.793F, 0.9729F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -0.2791F, -0.2225F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -1.1333F, -4.4613F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -1.1333F, 2.6811F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, 2.4427F, -0.8853F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -4.6997F, -0.8853F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, 0.145F, -0.0992F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r2 = wheel1.addChild("hexadecagon_r2", ModelPartBuilder.create().uv(155, 67).cuboid(-1.356F, -0.2595F, -0.088F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, 0.3849F, -1.6653F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -2.8746F, -0.0596F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, 0.1632F, -0.0502F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -1.129F, -4.4772F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -1.129F, 2.6652F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, 2.4471F, -0.9011F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -4.6954F, -0.9011F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, 0.145F, -0.0992F, -0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r3 = wheel1.addChild("hexadecagon_r3", ModelPartBuilder.create().uv(155, 67).cuboid(-1.5412F, -0.7305F, -1.4532F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.5412F, -1.577F, -0.2996F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.105F, -0.0992F, -0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r4 = wheel1.addChild("hexadecagon_r4", ModelPartBuilder.create().uv(155, 67).cuboid(-1.5412F, -0.7348F, -1.4373F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.5412F, -1.5813F, -0.2838F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.105F, -0.0992F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r5 = wheel1.addChild("hexadecagon_r5", ModelPartBuilder.create().uv(155, 67).cuboid(-1.5412F, -1.5706F, -0.3051F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.105F, -0.0992F, -0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r6 = wheel1.addChild("hexadecagon_r6", ModelPartBuilder.create().uv(155, 67).cuboid(-1.5412F, -1.5786F, -0.2758F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.105F, -0.0992F, 0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r7 = wheel1.addChild("hexadecagon_r7", ModelPartBuilder.create().uv(155, 67).cuboid(-1.356F, 2.4534F, -0.9066F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -4.689F, -0.9066F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, 0.145F, -0.0992F, -0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r8 = wheel1.addChild("hexadecagon_r8", ModelPartBuilder.create().uv(155, 67).cuboid(-1.356F, 2.4454F, -0.8773F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(155, 67).cuboid(-1.356F, -4.697F, -0.8773F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, 0.145F, -0.0992F, 0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r9 = wheel1.addChild("hexadecagon_r9", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, -0.907F, -5.5932F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.0657F, 0.3435F, -0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r10 = wheel1.addChild("hexadecagon_r10", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, -0.9114F, -5.5773F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.4887F, 0.2594F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r11 = wheel1.addChild("hexadecagon_r11", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, -0.907F, 3.3348F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.3999F, -0.4633F, -0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r12 = wheel1.addChild("hexadecagon_r12", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, -0.9114F, 3.3507F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.1545F, -0.5474F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r13 = wheel1.addChild("hexadecagon_r13", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, 3.5694F, -1.1286F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.4887F, 0.2594F, -0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r14 = wheel1.addChild("hexadecagon_r14", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, 3.5631F, -1.1231F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.6441F, 0.1039F, -0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r15 = wheel1.addChild("hexadecagon_r15", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, 3.5587F, -1.1072F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.7283F, -0.3191F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r16 = wheel1.addChild("hexadecagon_r16", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, 3.5614F, -1.0993F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.6441F, -0.5222F, 0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r17 = wheel1.addChild("hexadecagon_r17", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, -5.3586F, -1.1286F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, 0.1289F, -0.3581F, -0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r18 = wheel1.addChild("hexadecagon_r18", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, -5.365F, -1.1231F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, 0.1627F, -0.2303F, -0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r19 = wheel1.addChild("hexadecagon_r19", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, -5.3693F, -1.1072F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, 0.0786F, 0.0151F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r20 = wheel1.addChild("hexadecagon_r20", ModelPartBuilder.create().uv(191, 56).cuboid(-1.945F, -5.3666F, -1.0993F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.75F, -0.0266F, 0.0953F, 0.7854F, 0.0F, 0.0F));

        ModelPartData wheel2 = wheels.addChild("wheel2", ModelPartBuilder.create().uv(0, 105).cuboid(-4.195F, -5.2236F, -1.2148F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 242).cuboid(-4.195F, 2.831F, -1.2148F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 105).cuboid(-4.195F, -1.1963F, 2.8125F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 239).cuboid(-4.195F, -1.1963F, -5.2421F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-3.606F, -4.554F, -0.9928F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 241).cuboid(-3.606F, 2.5884F, -0.9928F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-3.606F, -0.9877F, 2.5735F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-3.606F, -0.9877F, -4.5689F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-3.7912F, -1.4356F, -0.3913F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-3.7912F, -0.5892F, -1.5449F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-3.606F, 1.424F, 0.0685F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-3.606F, -0.2067F, 1.361F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-3.606F, -3.2492F, -0.952F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-3.606F, 0.8137F, -3.3123F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r21 = wheel2.addChild("hexadecagon_r21", ModelPartBuilder.create().uv(27, 106).cuboid(-1.356F, 0.3008F, -1.6744F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, 0.9962F, -2.9966F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -1.8332F, -0.2543F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -3.1554F, -1.1997F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, 0.269F, -0.1203F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -0.9264F, 0.9519F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, 0.793F, 0.7229F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -0.2791F, -0.2225F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.5412F, -0.7348F, -1.4373F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.5412F, -1.5813F, -0.2838F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -1.1333F, -4.4613F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -1.1333F, 2.6811F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, 2.4427F, -0.8853F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -4.6997F, -0.8853F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, 0.145F, -0.0992F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r22 = wheel2.addChild("hexadecagon_r22", ModelPartBuilder.create().uv(27, 106).cuboid(-1.356F, -0.2595F, -0.088F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, 0.3849F, -1.6653F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -2.8746F, -0.3096F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -0.0868F, 0.9498F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.5412F, -0.7305F, -1.4532F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.5412F, -1.577F, -0.2996F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -1.129F, -4.4772F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -1.129F, 2.6652F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, 2.4471F, -0.9011F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -4.6954F, -0.9011F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, 0.145F, -0.0992F, -0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r23 = wheel2.addChild("hexadecagon_r23", ModelPartBuilder.create().uv(27, 106).cuboid(-1.5412F, -1.5706F, -0.3051F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, 2.4534F, -0.9066F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -4.689F, -0.9066F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, 0.145F, -0.0992F, -0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r24 = wheel2.addChild("hexadecagon_r24", ModelPartBuilder.create().uv(27, 106).cuboid(-1.5412F, -1.5786F, -0.2758F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, 2.4454F, -0.8773F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(27, 106).cuboid(-1.356F, -4.697F, -0.8773F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, 0.145F, -0.0992F, 0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r25 = wheel2.addChild("hexadecagon_r25", ModelPartBuilder.create().uv(0, 252).cuboid(-1.945F, -0.907F, -5.5932F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -0.0657F, 0.3435F, -0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r26 = wheel2.addChild("hexadecagon_r26", ModelPartBuilder.create().uv(0, 232).cuboid(-1.945F, -0.9114F, -5.5773F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -0.4887F, 0.2594F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r27 = wheel2.addChild("hexadecagon_r27", ModelPartBuilder.create().uv(0, 105).cuboid(-1.945F, -0.907F, 3.3348F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -0.3999F, -0.4633F, -0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r28 = wheel2.addChild("hexadecagon_r28", ModelPartBuilder.create().uv(0, 105).cuboid(-1.945F, -0.9114F, 3.3507F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -0.1545F, -0.5474F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r29 = wheel2.addChild("hexadecagon_r29", ModelPartBuilder.create().uv(0, 223).cuboid(-1.945F, 3.5694F, -1.1286F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -0.4887F, 0.2594F, -0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r30 = wheel2.addChild("hexadecagon_r30", ModelPartBuilder.create().uv(0, 246).cuboid(-1.945F, 3.5631F, -1.1231F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -0.6441F, 0.1039F, -0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r31 = wheel2.addChild("hexadecagon_r31", ModelPartBuilder.create().uv(0, 235).cuboid(-1.945F, 3.5587F, -1.1072F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -0.7283F, -0.3191F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r32 = wheel2.addChild("hexadecagon_r32", ModelPartBuilder.create().uv(0, 231).cuboid(-1.945F, 3.5614F, -1.0993F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -0.6441F, -0.5222F, 0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r33 = wheel2.addChild("hexadecagon_r33", ModelPartBuilder.create().uv(0, 105).cuboid(-1.945F, -5.3586F, -1.1286F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, 0.1289F, -0.3581F, -0.7854F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r34 = wheel2.addChild("hexadecagon_r34", ModelPartBuilder.create().uv(0, 105).cuboid(-1.945F, -5.365F, -1.1231F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, 0.1627F, -0.2303F, -0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r35 = wheel2.addChild("hexadecagon_r35", ModelPartBuilder.create().uv(0, 105).cuboid(-1.945F, -5.3693F, -1.1072F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, 0.0786F, 0.0151F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hexadecagon_r36 = wheel2.addChild("hexadecagon_r36", ModelPartBuilder.create().uv(0, 238).cuboid(-1.945F, -5.3666F, -1.0993F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -0.0266F, 0.0953F, 0.7854F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }

    @Override
    protected void prepare(MatrixStack matrices) {
        matrices.translate(-5.0 / 6.0, (-27.0 / 16.0), 30.0 / 16.0);
        matrices.scale(1.2f, 1.2f, 1.2f);
    }
}