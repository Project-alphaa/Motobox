package motobox.entity;

import motobox.Motobox;
import motobox.entity.render.UfoEntityRenderer;
import motobox.entity.render.VehicleEntityRenderer;
import motobox.entity.ufo.UfoEntity;
import motobox.vehicle.render.ExhaustFumesModel;
import motobox.vehicle.render.SkidEffectModel;
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
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;

public enum MotoboxEntities {
    ;
    public static final EntityType<VehicleEntity> VEHICLE_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            Motobox.id("vehicle"),
            FabricEntityTypeBuilder.<VehicleEntity>create(SpawnGroup.MISC, VehicleEntity::new).dimensions(new EntityDimensions(1f, 0.66f, true)).trackedUpdateRate(3).trackRangeChunks(10).build()
    );

    private static final float ufoScale = 4.0f;
    public static final EntityType<UfoEntity> UFO = MotoboxEntities.register(
            "ufo",
            FabricEntityTypeBuilder
                    .create(SpawnGroup.MISC, UfoEntity::new)
                    .dimensions(EntityDimensions.fixed(ufoScale * 3, ufoScale * 0.2f))
    );

    public static final TagKey<EntityType<?>> DASH_PANEL_BOOSTABLES = TagKey.of(Registries.ENTITY_TYPE.getKey(), Motobox.id("dash_panel_boostables"));

    public static final RegistryKey<DamageType> VEHICLE_DAMAGE_TYPE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Motobox.id("vehicle"));

    public static void init() {

    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        EntityRendererRegistry.register(UFO, UfoEntityRenderer::new);
        EntityRendererRegistry.register(VEHICLE_ENTITY, VehicleEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(TruckFrameModel.MODEL_LAYER, TruckFrameModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MotorbikeFrameModel.MODEL_LAYER, MotorbikeFrameModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RustyCarFrameModel.MODEL_LAYER, RustyCarFrameModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(TrailerRearAttachmentRenderModel.MODEL_LAYER, TrailerRearAttachmentRenderModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CaravanRearAttachmentRenderModel.MODEL_LAYER, CaravanRearAttachmentRenderModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(SleekRedOffroadWheelModel.MODEL_LAYER, SleekRedOffroadWheelModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MotorbikeWheelModel.MODEL_LAYER, MotorbikeWheelModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RustySteelWheelModel.MODEL_LAYER, RustySteelWheelModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SteelRimWheelModel.MODEL_LAYER, SteelRimWheelModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(DieselFourCylinderEngineModel.MODEL_LAYER, DieselFourCylinderEngineModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MotorbikeEngineModel.MODEL_LAYER, MotorbikeEngineModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(SkidEffectModel.MODEL_LAYER, SkidEffectModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ExhaustFumesModel.MODEL_LAYER, ExhaustFumesModel::getTexturedModelData);
    }

    private static <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> type) {
        return Registry.register(
                Registries.ENTITY_TYPE,
                Motobox.id(name),
                type.build()
        );
    }
}
