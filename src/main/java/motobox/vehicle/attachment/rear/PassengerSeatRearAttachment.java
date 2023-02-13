package motobox.vehicle.attachment.rear;

import motobox.entity.VehicleEntity;
import motobox.vehicle.attachment.RearAttachmentType;

public class PassengerSeatRearAttachment extends RearAttachment {
    public PassengerSeatRearAttachment(RearAttachmentType<?> type, VehicleEntity entity) {
        super(type, entity);
    }

    @Override
    public boolean isRideable() {
        return true;
    }

    @Override
    public double getPassengerHeightOffset() {
        return 0.69;
    }
}
