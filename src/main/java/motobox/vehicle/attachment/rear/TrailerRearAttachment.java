package motobox.vehicle.attachment.rear;

import motobox.entity.VehicleEntity;
import motobox.vehicle.attachment.RearAttachmentType;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import org.jetbrains.annotations.Nullable;

public class TrailerRearAttachment extends RearAttachment {
    public TrailerRearAttachment(RearAttachmentType<?> type, VehicleEntity entity) {
        super(type, entity);
    }

    @Override
    public boolean hasMenu() {
        return super.hasMenu();
    }

    @Override
    public @Nullable NamedScreenHandlerFactory createMenu(ScreenHandlerContext ctx) {
        return super.createMenu(ctx);
    }
}
