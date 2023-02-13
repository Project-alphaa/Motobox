package motobox.vehicle.attachment.front;

import motobox.entity.VehicleEntity;
import motobox.vehicle.attachment.FrontAttachmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;

public class MobControllerFrontAttachment extends FrontAttachment {
    public MobControllerFrontAttachment(FrontAttachmentType<?> type, VehicleEntity vehicle) {
        super(type, vehicle);
    }

    @Override
    public boolean canDrive(Entity entity) {
        return super.canDrive(entity) || (entity instanceof MobEntity);
    }
}
