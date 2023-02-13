package motobox.vehicle.attachment.front;

import motobox.entity.VehicleEntity;
import motobox.vehicle.attachment.FrontAttachmentType;

public class EmptyFrontAttachment extends FrontAttachment {
    public EmptyFrontAttachment(FrontAttachmentType<?> type, VehicleEntity vehicle) {
        super(type, vehicle);
    }
}
