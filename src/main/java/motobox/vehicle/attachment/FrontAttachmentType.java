package motobox.vehicle.attachment;

import motobox.Motobox;
import motobox.entity.VehicleEntity;
import motobox.render.MotoboxModels;
import motobox.util.SimpleMapContentRegistry;
import motobox.vehicle.DisplayStat;
import motobox.vehicle.VehicleComponent;
import motobox.vehicle.attachment.front.EmptyFrontAttachment;
import motobox.vehicle.attachment.front.FrontAttachment;
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

public final class FrontAttachmentType<T extends FrontAttachment> implements VehicleComponent<FrontAttachmentType<?>> {
    public static final Identifier ID = Motobox.id("front_attachment");
    public static final SimpleMapContentRegistry<FrontAttachmentType<?>> REGISTRY = new SimpleMapContentRegistry<>();

    public static final FrontAttachmentType<EmptyFrontAttachment> EMPTY = register(new FrontAttachmentType<>(
            Motobox.id("empty"), EmptyFrontAttachment::new, new FrontAttachmentModel(new Identifier("empty"), Motobox.id("empty"), 1)
    ));
    private final Identifier id;
    private final BiFunction<FrontAttachmentType<T>, VehicleEntity, T> constructor;
    private final FrontAttachmentModel model;
    private final Supplier<FeatureSet> requiredFeatures;

    public FrontAttachmentType(Identifier id,
                               BiFunction<FrontAttachmentType<T>, VehicleEntity, T> constructor,
                               FrontAttachmentModel model) {
        this(id, constructor, model, FeatureSet::empty);
    }

    public FrontAttachmentType(Identifier id,
                               BiFunction<FrontAttachmentType<T>, VehicleEntity, T> constructor,
                               FrontAttachmentModel model,
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
    public void forEachStat(Consumer<DisplayStat<FrontAttachmentType<?>>> action) {
    }

    @Override
    public Identifier getId() {
        return this.id();
    }

    private static <T extends FrontAttachment> FrontAttachmentType<T> register(FrontAttachmentType<T> entry) {
        REGISTRY.register(entry);
        return entry;
    }

    public Identifier id() {
        return id;
    }

    public BiFunction<FrontAttachmentType<T>, VehicleEntity, T> constructor() {
        return constructor;
    }

    public FrontAttachmentModel model() {
        return model;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FrontAttachmentType<?>) obj;
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
        return "FrontAttachmentType[" +
                "id=" + id + ", " +
                "constructor=" + constructor + ", " +
                "model=" + model + ']';
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return requiredFeatures.get();
    }


    public record FrontAttachmentModel(Identifier texture, Identifier modelId, float scale) {
        @Environment(EnvType.CLIENT)
        public Function<EntityRendererFactory.Context, Model> model() {
            return MotoboxModels.MODELS.get(modelId);
        }
    }
}
