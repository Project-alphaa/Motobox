package motobox.entity.render;

import motobox.entity.VehicleEntity;
import motobox.vehicle.render.VehicleRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Quaternionf;

public class VehicleEntityRenderer extends EntityRenderer<VehicleEntity> {
    private final EntityRendererFactory.Context ctx;

    public VehicleEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    @Override
    public Identifier getTexture(VehicleEntity entity) {
        return null;
    }

    @Override
    public void render(VehicleEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        float angX = entity.getDisplacement().getAngularX(tickDelta);
        float angZ = entity.getDisplacement().getAngularZ(tickDelta);
        float offsetY = entity.getDisplacement().getVertical(tickDelta);

        matrices.translate(0, offsetY, 0);
        matrices.multiply(new Quaternionf().rotationXYZ((float) Math.toRadians(angX), 0, (float) Math.toRadians(angZ)));

        VehicleRenderer.render(matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV, tickDelta, ctx, entity);
        matrices.pop();
    }
}
