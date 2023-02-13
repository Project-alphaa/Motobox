package motobox.vehicle.attachment.rear;

import motobox.entity.VehicleEntity;
import motobox.vehicle.attachment.RearAttachmentType;

public class EmptyRearAttachment extends RearAttachment {
    public EmptyRearAttachment(RearAttachmentType<?> type, VehicleEntity entity) {
        super(type, entity);
    }

    @Override
    public void tick() {
    }
}
