package motobox.vehicle.attachment;

import motobox.Motobox;
import motobox.common.FloatSupplier;
import motobox.entity.VehicleEntity;
import motobox.featuretoggle.MotoboxFeatureFlags;
import motobox.render.MotoboxModels;
import motobox.util.SimpleMapContentRegistry;
import motobox.vehicle.DisplayStat;
import motobox.vehicle.VehicleComponent;
import motobox.vehicle.attachment.rear.BaseChestRearAttachment;
import motobox.vehicle.attachment.rear.EmptyRearAttachment;
import motobox.vehicle.attachment.rear.RearAttachment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class RearAttachmentType<T extends RearAttachment> implements VehicleComponent<RearAttachmentType<?>> {
    public static final Identifier ID = Motobox.id("rear_attachment");
    public static final SimpleMapContentRegistry<RearAttachmentType<?>> REGISTRY = new SimpleMapContentRegistry<>();

    public static final RearAttachmentType<EmptyRearAttachment> EMPTY = register(new RearAttachmentType<>(
            Motobox.id("empty"), EmptyRearAttachment::new, new RearAttachmentModel(new Identifier("empty"), Motobox.id("empty"), 0, 0)
    ));

    public static final RearAttachmentType<BaseChestRearAttachment> TRAILER = register(new RearAttachmentType<>(Motobox.id("trailer"),
            BaseChestRearAttachment::chest, new RearAttachmentModel(Motobox.id("textures/entity/vehicle/rear_attachment/trailer.png"), Motobox.id("rearatt_trailer"), () -> 3, () -> 1 / 2f)));

    public static final RearAttachmentType<BaseChestRearAttachment> CARAVAN = register(new RearAttachmentType<>(Motobox.id("caravan"),
            BaseChestRearAttachment::saddledBarrel, new RearAttachmentModel(Motobox.id("textures/entity/vehicle/rear_attachment/caravan.png"), Motobox.id("rearatt_caravan"), () -> 3, () -> 1 / 4f), () -> FeatureSet.of(MotoboxFeatureFlags.CARAVAN)));
    private final Identifier id;
    private final BiFunction<RearAttachmentType<T>, VehicleEntity, T> constructor;
    private final RearAttachmentModel model;
    private final Supplier<FeatureSet> requiredFeatures;

    public RearAttachmentType(Identifier id,
                              BiFunction<RearAttachmentType<T>, VehicleEntity, T> constructor,
                              RearAttachmentModel model) {
        this(id, constructor, model, FeatureSet::empty);
    }

    public RearAttachmentType(Identifier id,
                              BiFunction<RearAttachmentType<T>, VehicleEntity, T> constructor,
                              RearAttachmentModel model,
                              Supplier<FeatureSet> requiredFeatures) {
        this.id = id;
        this.constructor = constructor;
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
    public void forEachStat(Consumer<DisplayStat<RearAttachmentType<?>>> action) {
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    private static <T extends RearAttachment> RearAttachmentType<T> register(RearAttachmentType<T> entry) {
        REGISTRY.register(entry);
        return entry;
    }

    public Identifier id() {
        return id;
    }

    public BiFunction<RearAttachmentType<T>, VehicleEntity, T> constructor() {
        return constructor;
    }

    public RearAttachmentModel model() {
        return model;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RearAttachmentType<?>) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.constructor, that.constructor) &&
                Objects.equals(this.model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, constructor, model);
    }

    @Override
    public String toString() {
        return "RearAttachmentType[" +
                "id=" + id + ", " +
                "constructor=" + constructor + ", " +
                "model=" + model + ']';
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return requiredFeatures.get();
    }


    public static final class RearAttachmentModel {
        private final Identifier texture;
        private final Identifier modelId;
        private final FloatSupplier pivotDistPx;
        private final FloatSupplier scale;

        public RearAttachmentModel(Identifier texture, Identifier modelId, float pivotDistPx, float scale) {
            this.texture = texture;
            this.modelId = modelId;
            this.pivotDistPx = () -> pivotDistPx;
            this.scale = () -> scale;
        }

        public RearAttachmentModel(Identifier texture, Identifier modelId, FloatSupplier pivotDistPx, FloatSupplier scale) {
            this.texture = texture;
            this.modelId = modelId;
            this.pivotDistPx = pivotDistPx;
            this.scale = scale;
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

        public FloatSupplier pivotDistPx() {
            return pivotDistPx;
        }

        public FloatSupplier scale() {
            return scale;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (RearAttachmentModel) obj;
            return Objects.equals(this.texture, that.texture) &&
                    Objects.equals(this.modelId, that.modelId) &&
                    Float.floatToIntBits(this.pivotDistPx.getFloat()) == Float.floatToIntBits(that.pivotDistPx.getFloat());
        }

        @Override
        public int hashCode() {
            return Objects.hash(texture, modelId, pivotDistPx);
        }

        @Override
        public String toString() {
            return "RearAttachmentModel[" +
                    "texture=" + texture + ", " +
                    "modelId=" + modelId + ", " +
                    "pivotDistPx=" + pivotDistPx + ']';
        }

    }
}
