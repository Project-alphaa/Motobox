package motobox.vehicle.render;

import motobox.vehicle.VehicleEngine;
import motobox.vehicle.VehicleFrame;
import motobox.vehicle.VehicleWheel;
import motobox.vehicle.attachment.FrontAttachmentType;
import motobox.vehicle.attachment.RearAttachmentType;
import motobox.vehicle.attachment.front.FrontAttachment;
import motobox.vehicle.attachment.rear.RearAttachment;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.EntityRendererFactory;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public interface RenderableVehicle {
    VehicleFrame getFrame();

    VehicleEngine getEngine();

    VehicleWheel getWheels();

    @Nullable RearAttachment getRearAttachment();

    @Nullable FrontAttachment getFrontAttachment();

    default RearAttachmentType<?> getRearAttachmentType() {
        if (this.getRearAttachment() == null) {
            return RearAttachmentType.EMPTY;
        }
        return this.getRearAttachment().type;
    }

    default FrontAttachmentType<?> getFrontAttachmentType() {
        if (this.getFrontAttachment() == null) {
            return FrontAttachmentType.EMPTY;
        }
        return this.getFrontAttachment().type;
    }

    Model getFrameModel(EntityRendererFactory.Context ctx);

    Model getWheelModel(EntityRendererFactory.Context ctx);

    Model getEngineModel(EntityRendererFactory.Context ctx);

    Model getRearAttachmentModel(EntityRendererFactory.Context ctx);

    Model getFrontAttachmentModel(EntityRendererFactory.Context ctx);

    float getVehicleYaw(float tickDelta);

    float getRearAttachmentYaw(float tickDelta);

    float getWheelAngle(float tickDelta);

    float getSteering(float tickDelta);

    float getSuspensionBounce(float tickDelta);

    boolean engineRunning();

    default int getWheelCount() {
        return this.getFrame().model().wheelBase().get().wheelCount;
    }

    int getBoostTimer();

    int getTurboCharge();

    long getTime();

    boolean vehicleOnGround();

    boolean debris();

    Color debrisColor();
}
