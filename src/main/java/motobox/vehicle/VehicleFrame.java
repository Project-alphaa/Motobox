package motobox.vehicle;

import motobox.Motobox;
import motobox.common.FloatSupplier;
import motobox.render.MotoboxModels;
import motobox.util.SimpleMapContentRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class VehicleFrame implements VehicleComponent<VehicleFrame> {
    public static final Identifier ID = Motobox.id("frame");
    public static final SimpleMapContentRegistry<VehicleFrame> REGISTRY = new SimpleMapContentRegistry<>();

    public static final VehicleFrame EMPTY = REGISTRY.register(
            new VehicleFrame(
                    Motobox.id("empty"),
                    0.25f,
                    new FrameModel(
                            new Identifier("empty"),
                            Motobox.id("empty"),
                            () -> WheelBase.basic(16, 16),
                            () -> 16,
                            () -> 8,
                            () -> 8,
                            () -> 4,
                            () -> 8,
                            () -> 8
                    )
            )
    );

    public static final VehicleFrame TRUCK = REGISTRY.register(truck());
    public static final VehicleFrame MOTORBIKE = REGISTRY.register(motorbike());

    public static final VehicleFrame RUSTY_CAR = REGISTRY.register(rustyCar());
    private static VehicleFrame truck() {
        return new VehicleFrame(
                Motobox.id("truck"),
                0.73f,
                new FrameModel(
                        Motobox.id("textures/entity/vehicle/frame/truck.png"),
                        Motobox.id("frame_truck"),
                        () -> new WheelBase(
                                new WheelBase.WheelPos(-20, -12, 0.65f, 0, WheelBase.WheelEnd.BACK, WheelBase.WheelSide.LEFT),
                                new WheelBase.WheelPos(-20, 12, 0.65f, 180, WheelBase.WheelEnd.BACK, WheelBase.WheelSide.RIGHT),
                                new WheelBase.WheelPos(22, -12, 0.65f, 0, WheelBase.WheelEnd.FRONT, WheelBase.WheelSide.LEFT),
                                new WheelBase.WheelPos(22, 12, 0.65f, 180, WheelBase.WheelEnd.FRONT, WheelBase.WheelSide.RIGHT)
                        ),
                        () -> 28,
                        () -> 3.5f,
                        () -> -21,
                        () -> 1,
                        () -> 34,
                        () -> 31
                )
        );
    }

    private static VehicleFrame motorbike() {
        return new VehicleFrame(
                Motobox.id("motorbike"),
                0.34f,
                new FrameModel(
                        Motobox.id("textures/entity/vehicle/frame/motorbike.png"),
                        Motobox.id("frame_motorbike"),
                        () -> WheelBase.bicycleOffset(26, 1),
                        () -> 28,
                        () -> 9.5f,
                        () -> 1.1f,
                        () -> 3,
                        () -> 21,
                        () -> 22
                )
        );
    }

    private static VehicleFrame rustyCar() {
        return new VehicleFrame(
                Motobox.id("rusty_car"),
                0.84f,
                new FrameModel(
                        Motobox.id("textures/entity/vehicle/frame/rusty_car.png"),
                        Motobox.id("frame_rusty_car"),
                        () -> new WheelBase(
                                new WheelBase.WheelPos(-26, -11f, 0.7f, 0, WheelBase.WheelEnd.BACK, WheelBase.WheelSide.LEFT),
                                new WheelBase.WheelPos(-26, 11f, 0.7f, 180, WheelBase.WheelEnd.BACK, WheelBase.WheelSide.RIGHT),
                                new WheelBase.WheelPos(27, -11f, 0.7f, 0, WheelBase.WheelEnd.FRONT, WheelBase.WheelSide.LEFT),
                                new WheelBase.WheelPos(27, 11f, 0.7f, 180, WheelBase.WheelEnd.FRONT, WheelBase.WheelSide.RIGHT)
                        ),
                        () -> 28,
                        () -> -2.6f,
                        () -> -24.2f,
                        () -> -2f,
                        () -> 44,
                        () -> 31
                )
        );
    }


    public static final DisplayStat<VehicleFrame> STAT_WEIGHT = new DisplayStat<>("weight", VehicleFrame::weight);
    private final Identifier id;
    private final float weight;
    private final FrameModel model;
    private final Supplier<FeatureSet> requiredFeatures;

    public VehicleFrame(Identifier id, float weight, FrameModel model) {
        this(id, weight, model, FeatureSet::empty);
    }

    public VehicleFrame(Identifier id, float weight, FrameModel model, Supplier<FeatureSet> requiredFeatures) {
        this.id = id;
        this.weight = weight;
        this.model = model;
        this.requiredFeatures = requiredFeatures;
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY;
    }

    @Override
    public Identifier containerId() {
        return ID;
    }

    @Override
    public void forEachStat(Consumer<DisplayStat<VehicleFrame>> action) {
        action.accept(STAT_WEIGHT);
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    public String getTranslationKey() {
        return "frame." + id.getNamespace() + "." + id.getPath();
    }

    public Identifier id() {
        return id;
    }

    public float weight() {
        return weight;
    }

    public FrameModel model() {
        return model;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (VehicleFrame) obj;
        return Objects.equals(this.id, that.id) &&
                Float.floatToIntBits(this.weight) == Float.floatToIntBits(that.weight) &&
                Objects.equals(this.model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weight, model);
    }

    @Override
    public String toString() {
        return "VehicleFrame[" +
                "id=" + id + ", " +
                "weight=" + weight + ", " +
                "model=" + model + ']';
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return requiredFeatures.get();
    }


    @SuppressWarnings("unused")
    public static final class FrameModel {
        private final Identifier texture;
        private final Identifier modelId;
        private final Supplier<WheelBase> wheelBase;
        private final FloatSupplier lengthPx;
        private final FloatSupplier seatHeight;
        private final FloatSupplier enginePosBack;
        private final FloatSupplier enginePosUp;
        private final FloatSupplier rearAttachmentPos;
        private final FloatSupplier frontAttachmentPos;

        @Deprecated
        public FrameModel(
                Identifier texture,
                Identifier modelId,
                Supplier<WheelBase> wheelBase,
                FloatSupplier lengthPx,
                FloatSupplier seatHeight,
                FloatSupplier enginePosBack,
                FloatSupplier enginePosUp,
                FloatSupplier rearAttachmentPos,
                FloatSupplier frontAttachmentPos
        ) {
            this.texture = texture;
            this.modelId = modelId;
            this.wheelBase = wheelBase;
            this.lengthPx = lengthPx;
            this.seatHeight = seatHeight;
            this.enginePosBack = enginePosBack;
            this.enginePosUp = enginePosUp;
            this.rearAttachmentPos = rearAttachmentPos;
            this.frontAttachmentPos = frontAttachmentPos;
        }

        public FrameModel(
                Identifier texture,
                Identifier modelId,
                WheelBase wheelBase,
                float lengthPx,
                float seatHeight,
                float enginePosBack,
                float enginePosUp,
                float rearAttachmentPos,
                float frontAttachmentPos
        ) {
            this(
                    texture,
                    modelId,
                    () -> wheelBase,
                    FloatSupplier.direct(lengthPx),
                    FloatSupplier.direct(seatHeight),
                    FloatSupplier.direct(enginePosBack),
                    FloatSupplier.direct(enginePosUp),
                    FloatSupplier.direct(rearAttachmentPos),
                    FloatSupplier.direct(frontAttachmentPos)
            );
        }

        @Environment(EnvType.CLIENT)
        public Function<EntityRendererFactory.Context, Model> model() {
            return MotoboxModels.MODELS.get(modelId);
        }

        public Identifier texture() {
            return texture;
        }

        public Identifier modelId() {
            return modelId;
        }

        public Supplier<WheelBase> wheelBase() {
            return wheelBase;
        }

        public FloatSupplier lengthPx() {
            return lengthPx;
        }

        public FloatSupplier seatHeight() {
            return seatHeight;
        }

        public FloatSupplier enginePosBack() {
            return enginePosBack;
        }

        public FloatSupplier enginePosUp() {
            return enginePosUp;
        }

        public FloatSupplier rearAttachmentPos() {
            return rearAttachmentPos;
        }

        public FloatSupplier frontAttachmentPos() {
            return frontAttachmentPos;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (FrameModel) obj;
            return Objects.equals(this.texture, that.texture) &&
                    Objects.equals(this.modelId, that.modelId) &&
                    Objects.equals(this.wheelBase, that.wheelBase) &&
                    Objects.equals(this.lengthPx, that.lengthPx) &&
                    Objects.equals(this.seatHeight, that.seatHeight) &&
                    Objects.equals(this.enginePosBack, that.enginePosBack) &&
                    Objects.equals(this.enginePosUp, that.enginePosUp) &&
                    Objects.equals(this.rearAttachmentPos, that.rearAttachmentPos) &&
                    Objects.equals(this.frontAttachmentPos, that.frontAttachmentPos);
        }

        @Override
        public int hashCode() {
            return Objects.hash(texture, modelId, wheelBase, lengthPx, seatHeight, enginePosBack, enginePosUp, rearAttachmentPos, frontAttachmentPos);
        }

        @Override
        public String toString() {
            return "FrameModel[" +
                    "texture=" + texture + ", " +
                    "modelId=" + modelId + ", " +
                    "wheelBase=" + wheelBase + ", " +
                    "lengthPx=" + lengthPx + ", " +
                    "seatHeight=" + seatHeight + ", " +
                    "enginePosBack=" + enginePosBack + ", " +
                    "enginePosUp=" + enginePosUp + ", " +
                    "rearAttachmentPos=" + rearAttachmentPos + ", " +
                    "frontAttachmentPos=" + frontAttachmentPos + ']';
        }

    }
}
