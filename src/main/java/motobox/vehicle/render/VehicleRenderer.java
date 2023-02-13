package motobox.vehicle.render;

import motobox.entity.VehicleEntity;
import motobox.vehicle.VehicleEngine;
import motobox.vehicle.VehicleWheel;
import motobox.vehicle.WheelBase;
import motobox.vehicle.render.attachment.front.FrontAttachmentRenderModel;
import motobox.vehicle.render.attachment.rear.RearAttachmentRenderModel;
import motobox.vehicle.render.wheel.WheelContextReceiver;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Quaternionf;

public enum VehicleRenderer {
    ;
    private static Model skidEffectModel;
    private static Model exhaustFumesModel;

    public static void render(
            MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, float tickDelta,
            EntityRendererFactory.Context ctx, RenderableVehicle vehicle
    ) {
        var frame = vehicle.getFrame();
        var wheels = vehicle.getWheels();
        var engine = vehicle.getEngine();

        if (skidEffectModel == null || exhaustFumesModel == null) {
            skidEffectModel = new SkidEffectModel<>(ctx);
            exhaustFumesModel = new ExhaustFumesModel<>(ctx);
        }

        matrices.push();

        matrices.multiply(new Quaternionf().rotateZ((float) Math.toRadians(180)));
        matrices.multiply(new Quaternionf().rotateY((float) Math.toRadians(vehicle.getVehicleYaw(tickDelta) + 180)));

        float chassisRaise = wheels.model().radius() / 16;
        float bounce = vehicle.getSuspensionBounce(tickDelta) * 0.048f;

        var frameModel = vehicle.getFrameModel(ctx);
        var wheelModel = vehicle.getWheelModel(ctx);
        var engineModel = vehicle.getEngineModel(ctx);
        var rearAttachmentModel = vehicle.getRearAttachmentModel(ctx);
        var frontAttachmentModel = vehicle.getFrontAttachmentModel(ctx);

        matrices.translate(0, -chassisRaise, 0);

        // Frame, engine, exhaust
        matrices.push();

        matrices.translate(0, bounce + (vehicle.engineRunning() ? (Math.cos((vehicle.getTime() + tickDelta) * 2.7) / 156) : 0), 0);
        var frameTexture = frame.model().texture();
        var engineTexture = engine.model().texture();
        if (!frame.isEmpty() && frameModel != null) {
            frameModel.render(matrices, vertexConsumers.getBuffer(frameModel.getLayer(frameTexture)), light, overlay, 1, 1, 1, 1);
            if (frameModel instanceof BaseModel base) {
                base.doOtherLayerRender(matrices, vertexConsumers, light, overlay);
            }
        }

        float eBack = frame.model().enginePosBack().getFloat() / 16;
        float eUp = frame.model().enginePosUp().getFloat() / 16;
        matrices.translate(0, -eUp, eBack);
        matrices.multiply(new Quaternionf().rotateY((float) Math.toRadians(180)));
        if (!engine.isEmpty() && engineModel != null) {
            engineModel.render(matrices, vertexConsumers.getBuffer(engineModel.getLayer(engineTexture)), light, overlay, 1, 1, 1, 1);
            if (engineModel instanceof BaseModel base) {
                base.doOtherLayerRender(matrices, vertexConsumers, light, overlay);
            }
        }

        VertexConsumer exhaustBuffer = null;
        Identifier[] exhaustTexes;
        if (vehicle.getBoostTimer() > 0) {
            exhaustTexes = ExhaustFumesModel.FLAME_TEXTURES;
            int index = (int) (vehicle.getTime() % exhaustTexes.length);
            exhaustBuffer = vertexConsumers.getBuffer(RenderLayer.getEyes(exhaustTexes[index]));
        } else if (vehicle.engineRunning()) {
            exhaustTexes = ExhaustFumesModel.SMOKE_TEXTURES;
            int index = (int) Math.floor(((vehicle.getTime() + tickDelta) / 1.5f) % exhaustTexes.length);
            exhaustBuffer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(exhaustTexes[index]));
        }
        if (exhaustBuffer != null) {
            for (VehicleEngine.ExhaustPos exhaust : engine.model().exhausts()) {
                matrices.push();

                matrices.translate(exhaust.x() / 16, -exhaust.y() / 16, exhaust.z() / 16);
                matrices.multiply(new Quaternionf().rotateY((float) Math.toRadians(exhaust.yaw())));
                matrices.multiply(new Quaternionf().rotateX((float) Math.toRadians(exhaust.pitch())));
                exhaustFumesModel.render(matrices, exhaustBuffer, light, overlay, 1, 1, 1, 1);

                matrices.pop();
            }
        }
        matrices.pop();

        // Rear Attachment
        var rearAtt = vehicle.getRearAttachmentType();
        if (!rearAtt.isEmpty()) {
            matrices.push();
            matrices.translate(0, chassisRaise, frame.model().rearAttachmentPos().getFloat() / 16);
            matrices.multiply(new Quaternionf().rotateY((float) -Math.toRadians(vehicle.getVehicleYaw(tickDelta) - vehicle.getRearAttachmentYaw(tickDelta))));

            matrices.translate(0, 0, rearAtt.model().pivotDistPx().getFloat() / 16);
            if (rearAttachmentModel instanceof RearAttachmentRenderModel rm) {
                rm.setRenderState(vehicle.getRearAttachment(), (float) Math.toRadians(vehicle.getWheelAngle(tickDelta)), tickDelta);
            }
            rearAttachmentModel.render(matrices, vertexConsumers.getBuffer(rearAttachmentModel.getLayer(rearAtt.model().texture())), light, overlay, 1, 1, 1, 1);
            if (rearAttachmentModel instanceof BaseModel base) {
                base.doOtherLayerRender(matrices, vertexConsumers, light, overlay);
            }
            matrices.pop();
        }

        // Front Attachment
        var frontAtt = vehicle.getFrontAttachmentType();
        if (!frontAtt.isEmpty()) {
            matrices.push();
            matrices.translate(0, 0, frame.model().frontAttachmentPos().getFloat() / -16);

            if (frontAttachmentModel instanceof FrontAttachmentRenderModel fm) {
                fm.setRenderState(vehicle.getFrontAttachment(), chassisRaise, tickDelta);
            }
            frontAttachmentModel.render(matrices, vertexConsumers.getBuffer(frontAttachmentModel.getLayer(frontAtt.model().texture())), light, overlay, 1, 1, 1, 1);
            if (frontAttachmentModel instanceof BaseModel base) {
                base.doOtherLayerRender(matrices, vertexConsumers, light, overlay);
            }
            matrices.pop();
        }

        // WHEELS ----------------------------------------
        var wPoses = frame.model().wheelBase().get().wheels;

        if (!wheels.isEmpty()) {
            var wheelBuffer = vertexConsumers.getBuffer(wheelModel.getLayer(wheels.model().texture()));
            float wheelAngle = vehicle.getWheelAngle(tickDelta);
            int wheelCount = vehicle.getWheelCount();

            for (var pos : wPoses) {
                if (wheelCount <= 0) {
                    break;
                }

                if (wheelModel instanceof WheelContextReceiver receiver) {
                    receiver.provideContext(pos);
                }
                float scale = pos.scale();
                float wheelRadius = wheels.model().radius() - (wheels.model().radius() * (scale - 1));
                matrices.push();

                matrices.translate(pos.right() / 16, wheelRadius / 16, -pos.forward() / 16);

                if (pos.end() == WheelBase.WheelEnd.FRONT)
                    matrices.multiply(new Quaternionf().rotateY((float) Math.toRadians(vehicle.getSteering(tickDelta) * 27)));
                matrices.translate(0, -chassisRaise, 0);
                matrices.multiply(new Quaternionf().rotateX((float) Math.toRadians(wheelAngle)));
                matrices.scale(scale, scale, scale);

                matrices.multiply(new Quaternionf().rotateY((float) Math.toRadians(180 + pos.yaw())));

                if (wheels == VehicleWheel.STEEL_RIM) {
                    matrices.translate(0.4f, -2.3f, 0.022f);
                    matrices.scale(1.65f, 1.65f, 1.65f);
                }

                wheelModel.render(matrices, wheelBuffer, light, overlay, 1, 1, 1, 1);
                if (wheelModel instanceof BaseModel base) {
                    base.doOtherLayerRender(matrices, vertexConsumers, light, overlay);
                }

                matrices.pop();

                wheelCount--;
            }
        }

        // Skid effects
        if ((vehicle.getTurboCharge() > VehicleEntity.SMALL_TURBO_TIME || vehicle.debris()) && vehicle.vehicleOnGround()) {
            var skidTexes = SkidEffectModel.COOL_SPARK_TEXTURES;
            boolean bright = true;
            float r = 1;
            float g = 1;
            float b = 1;
            if (vehicle.getTurboCharge() > VehicleEntity.LARGE_TURBO_TIME) {
                skidTexes = SkidEffectModel.FLAME_TEXTURES;
            } else if (vehicle.getTurboCharge() > VehicleEntity.MEDIUM_TURBO_TIME) {
                skidTexes = SkidEffectModel.HOT_SPARK_TEXTURES;
            } else if (vehicle.debris()) {
                skidTexes = SkidEffectModel.DEBRIS_TEXTURES;
                var c = vehicle.debrisColor();
                r = (c.getRed() / 255f) * 0.85f;
                g = (c.getGreen() / 255f) * 0.85f;
                b = (c.getBlue() / 255f) * 0.85f;
                bright = false;
            }
            int index = (int) Math.floor(((vehicle.getTime() + tickDelta) / 1.5f) % skidTexes.length);
            var skidEffectBuffer = vertexConsumers.getBuffer(bright ? RenderLayer.getEyes(skidTexes[index]) : RenderLayer.getEntitySmoothCutout(skidTexes[index]));

            for (var pos : wPoses) {
                if (pos.end() == WheelBase.WheelEnd.BACK) {
                    float scale = pos.scale();
                    float heightOffset = wheels.model().radius();
                    float wheelRadius = wheels.model().radius() * scale;
                    float wheelWidth =  (wheels.model().width() / 16) * scale;
                    float back = (wheelRadius > 2) ? (float) (Math.sqrt((wheelRadius * wheelRadius) - Math.pow(wheelRadius - 2, 2)) - 0.85) / 16 : 0.08f;
                    matrices.push();
                    matrices.translate((pos.right() / 16) + (wheelWidth * (pos.side() == WheelBase.WheelSide.RIGHT ? 1 : -1)), heightOffset / 16, (-pos.forward() / 16) + back);
                    matrices.scale(pos.side() == WheelBase.WheelSide.LEFT ? -1 : 1, 1, -1);
                    skidEffectModel.render(matrices, skidEffectBuffer, light, overlay, r, g, b, 0.6f);
                    matrices.pop();
                }
            }
        }
        // -----------------------------------------------

        matrices.pop();
    }
}
