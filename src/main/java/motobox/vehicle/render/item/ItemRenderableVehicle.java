package motobox.vehicle.render.item;

import motobox.util.EntityRenderHelper;
import motobox.vehicle.VehicleData;
import motobox.vehicle.VehicleEngine;
import motobox.vehicle.VehicleFrame;
import motobox.vehicle.VehicleWheel;
import motobox.vehicle.attachment.FrontAttachmentType;
import motobox.vehicle.attachment.RearAttachmentType;
import motobox.vehicle.attachment.front.FrontAttachment;
import motobox.vehicle.attachment.rear.RearAttachment;
import motobox.vehicle.render.RenderableVehicle;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ItemRenderableVehicle implements RenderableVehicle {
    private final VehicleData reader;
    private final Map<Identifier, Model> frameModelCache = new HashMap<>();
    private final Map<Identifier, Model> wheelModelCache = new HashMap<>();
    private final Map<Identifier, Model> engineModelCache = new HashMap<>();
    private Model emptyRearAttModel;
    private Model emptyFrontAttModel;

    public ItemRenderableVehicle(VehicleData reader) {
        this.reader = reader;
        EntityRenderHelper.registerContextListener(ctx -> {
            frameModelCache.clear();
            wheelModelCache.clear();
            engineModelCache.clear();
            emptyRearAttModel = null;
            emptyFrontAttModel = null;
        });
    }

    @Override
    public VehicleFrame getFrame() {
        return reader.getFrame();
    }

    @Override
    public VehicleEngine getEngine() {
        return reader.getEngine();
    }

    @Override
    public VehicleWheel getWheels() {
        return reader.getWheel();
    }

    @Override
    public @Nullable RearAttachment getRearAttachment() {
        return null;
    }

    @Override
    public @Nullable FrontAttachment getFrontAttachment() {
        return null;
    }

    @Override
    public Model getFrameModel(EntityRendererFactory.Context ctx) {
        if (!frameModelCache.containsKey(reader.getFrame().getId())) frameModelCache.put(reader.getFrame().getId(), reader.getFrame().model().model().apply(ctx));
        return frameModelCache.get(reader.getFrame().getId());
    }

    @Override
    public Model getWheelModel(EntityRendererFactory.Context ctx) {
        if (!wheelModelCache.containsKey(reader.getWheel().getId())) wheelModelCache.put(reader.getWheel().getId(), reader.getWheel().model().model().apply(ctx));
        return wheelModelCache.get(reader.getWheel().getId());
    }

    @Override
    public Model getEngineModel(EntityRendererFactory.Context ctx) {
        if (!engineModelCache.containsKey(reader.getEngine().getId())) engineModelCache.put(reader.getEngine().getId(), reader.getEngine().model().model().apply(ctx));
        return engineModelCache.get(reader.getEngine().getId());
    }

    @Override
    public Model getRearAttachmentModel(EntityRendererFactory.Context ctx) {
        if (emptyRearAttModel == null) emptyRearAttModel = RearAttachmentType.EMPTY.model().model().apply(ctx);
        return emptyRearAttModel;
    }

    @Override
    public Model getFrontAttachmentModel(EntityRendererFactory.Context ctx) {
        if (emptyFrontAttModel == null) emptyFrontAttModel = FrontAttachmentType.EMPTY.model().model().apply(ctx);
        return emptyFrontAttModel;
    }

    @Override
    public float getVehicleYaw(float tickDelta) {
        return 0;
    }

    @Override
    public float getRearAttachmentYaw(float tickDelta) {
        return 0;
    }

    @Override
    public float getWheelAngle(float tickDelta) {
        return 0;
    }

    @Override
    public float getSteering(float tickDelta) {
        return 0;
    }

    @Override
    public float getSuspensionBounce(float tickDelta) {
        return 0;
    }

    @Override
    public boolean engineRunning() {
        return false;
    }

    @Override
    public int getBoostTimer() {
        return 0;
    }

    @Override
    public int getTurboCharge() {
        return 0;
    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public boolean vehicleOnGround() {
        return true;
    }

    @Override
    public boolean debris() {
        return false;
    }

    @Override
    public Color debrisColor() {
        return new Color(0, 0, 0);
    }
}
