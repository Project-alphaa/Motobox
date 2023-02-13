package motobox.render;

import motobox.Motobox;
import motobox.vehicle.render.EmptyModel;
import motobox.vehicle.render.attachment.rear.CaravanRearAttachmentRenderModel;
import motobox.vehicle.render.attachment.rear.TrailerRearAttachmentRenderModel;
import motobox.vehicle.render.engine.MotorbikeEngineModel;
import motobox.vehicle.render.engine.DieselFourCylinderEngineModel;
import motobox.vehicle.render.frame.MotorbikeFrameModel;
import motobox.vehicle.render.frame.RustyCarFrameModel;
import motobox.vehicle.render.frame.TruckFrameModel;
import motobox.vehicle.render.wheel.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public enum MotoboxModels {
    ;
    public static final Map<Identifier, Function<EntityRendererFactory.Context, Model>> MODELS = new HashMap<>();

    @Environment(EnvType.CLIENT)
    public static void init() {
        MODELS.put(Motobox.id("empty"), EmptyModel::new);

        MODELS.put(Motobox.id("frame_truck"), TruckFrameModel::new);
        MODELS.put(Motobox.id("frame_motorbike"), MotorbikeFrameModel::new);
        MODELS.put(Motobox.id("frame_rusty_car"), RustyCarFrameModel::new);

        MODELS.put(Motobox.id("wheel_sleek_red_offroad"), SleekRedOffroadWheelModel::new);
        MODELS.put(Motobox.id("wheel_motorbike"), MotorbikeWheelModel::new);
        MODELS.put(Motobox.id("wheel_rusty_steel"), RustySteelWheelModel::new);
        MODELS.put(Motobox.id("wheel_steel_rim"), SteelRimWheelModel::new);

        MODELS.put(Motobox.id("diesel_four_cylinder_engine"), DieselFourCylinderEngineModel::new);
        MODELS.put(Motobox.id("motorbike_engine"), MotorbikeEngineModel::new);
        MODELS.put(Motobox.id("rusty_car_engine"), DieselFourCylinderEngineModel::new);

        MODELS.put(Motobox.id("rearatt_trailer"), TrailerRearAttachmentRenderModel::new);
        MODELS.put(Motobox.id("rearatt_caravan"), CaravanRearAttachmentRenderModel::new);
    }
}
