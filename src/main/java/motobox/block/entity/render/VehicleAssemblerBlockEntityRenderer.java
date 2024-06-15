package motobox.block.entity.render;

import motobox.block.entity.VehicleAssemblerBlockEntity;
import motobox.util.EntityRenderHelper;
import motobox.vehicle.render.VehicleRenderer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.state.property.Properties;
import org.joml.Quaternionf;

public class VehicleAssemblerBlockEntityRenderer implements BlockEntityRenderer<VehicleAssemblerBlockEntity> {
    private final TextRenderer textRenderer;
    private EntityRendererFactory.Context context = null;

    public VehicleAssemblerBlockEntityRenderer(BlockEntityRendererFactory.Context blockEntityCtx) {
        EntityRenderHelper.registerContextListener(this::setContext);

        this.textRenderer = blockEntityCtx.getTextRenderer();
    }

    public void setContext(EntityRendererFactory.Context context) {
        this.context = context;
    }

    @Override
    public void render(VehicleAssemblerBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (this.context != null) {
            matrices.push();
            matrices.translate(0.5, 0.75 - (entity.getWheels().model().radius() / 16), 0.5);
            VehicleRenderer.render(matrices, vertexConsumers, light, overlay, tickDelta, this.context, entity);
            matrices.pop();

            matrices.push();
            matrices.translate(0.5, 0, 0.5);
            matrices.multiply(new Quaternionf().rotateY((float) Math.toRadians(-entity.getCachedState().get(Properties.HORIZONTAL_FACING).asRotation())));
            matrices.translate(0, 0.372, 0.501);
            matrices.scale(0.008f, -0.008f, -0.008f);

            for (var text : entity.label) {
                matrices.push();
                matrices.translate(-0.5 * textRenderer.getWidth(text), 0, 0);
                textRenderer.draw(text, 0, 0, 0xFFFFFF, true, matrices.peek().getPositionMatrix(), vertexConsumers, TextRenderer.TextLayerType.SEE_THROUGH, light, overlay);
                matrices.pop();
                matrices.translate(0, 12, 0);
            }

            matrices.pop();
        }
    }
}
