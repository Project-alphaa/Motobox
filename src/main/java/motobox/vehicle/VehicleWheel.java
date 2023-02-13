package motobox.vehicle;

import motobox.Motobox;
import motobox.render.MotoboxModels;
import motobox.util.SimpleMapContentRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class VehicleWheel implements VehicleComponent<VehicleWheel> {
    public static final Identifier ID = Motobox.id("wheel");
    public static final SimpleMapContentRegistry<VehicleWheel> REGISTRY = new SimpleMapContentRegistry<>();

    public static final VehicleWheel EMPTY = REGISTRY.register(
            new VehicleWheel(Motobox.id("empty"), 0.01f, 0.01f, new WheelModel(1, 1, new Identifier("empty"), Motobox.id("empty")))
    );

    public static final VehicleWheel SLEEK_RED_OFFROAD = REGISTRY.register(
            new VehicleWheel(Motobox.id("sleek_red_offroad"), 1.1f, 0.8f, new WheelModel(8.4f, 5, Motobox.id("textures/entity/vehicle/wheel/sleek_red_offroad.png"), Motobox.id("wheel_sleek_red_offroad")))
    );

    public static final VehicleWheel MOTORBIKE = REGISTRY.register(
            new VehicleWheel(Motobox.id("motorbike"), 1.0f, 1.0f, new WheelModel(6.0f, 5, Motobox.id("textures/entity/vehicle/wheel/motorbike.png"), Motobox.id("wheel_motorbike")))
    );

    public static final VehicleWheel RUSTY_STEEL = REGISTRY.register(
            new VehicleWheel(Motobox.id("rusty_steel"), 1.3f, 0.8f, new WheelModel(8.4f, 5, Motobox.id("textures/entity/vehicle/wheel/rusty_steel.png"), Motobox.id("wheel_rusty_steel")))
    );

    public static final VehicleWheel STEEL_RIM = REGISTRY.register(
            new VehicleWheel(Motobox.id("steel_rim"), 1.1f, 1.0f, new WheelModel(1, 5, Motobox.id("textures/entity/vehicle/wheel/steel_rim.png"), Motobox.id("wheel_steel_rim")))
    );

    public static final DisplayStat<VehicleWheel> STAT_SIZE = new DisplayStat<>("size", VehicleWheel::size);
    public static final DisplayStat<VehicleWheel> STAT_GRIP = new DisplayStat<>("grip", VehicleWheel::grip);
    private final Identifier id;
    private final float size;
    private final float grip;
    private final WheelModel model;
    private final Supplier<FeatureSet> requiredFeatures;
    private final Ability[] abilities;

    public VehicleWheel(
            Identifier id,
            float size,
            float grip,
            WheelModel model,
            Ability... abilities
    ) {
        this(id, size, grip, model, FeatureSet::empty, abilities);
    }

    public VehicleWheel(
            Identifier id,
            float size,
            float grip,
            WheelModel model,
            Supplier<FeatureSet> requiredFeatures,
            Ability... abilities
    ) {
        this.id = id;
        this.size = size;
        this.grip = grip;
        this.model = model;
        this.requiredFeatures = requiredFeatures;
        this.abilities = abilities;
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
    public void forEachStat(Consumer<DisplayStat<VehicleWheel>> action) {
        action.accept(STAT_SIZE);
        action.accept(STAT_GRIP);
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    public String getTranslationKey() {
        return "wheel." + id.getNamespace() + "." + id.getPath();
    }

    public Identifier id() {
        return id;
    }

    public float size() {
        return size;
    }

    public float grip() {
        return grip;
    }

    public WheelModel model() {
        return model;
    }

    public Ability[] abilities() {
        return abilities;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (VehicleWheel) obj;
        return Objects.equals(this.id, that.id) &&
                Float.floatToIntBits(this.size) == Float.floatToIntBits(that.size) &&
                Float.floatToIntBits(this.grip) == Float.floatToIntBits(that.grip) &&
                Objects.equals(this.model, that.model) &&
                Arrays.equals(this.abilities, that.abilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, grip, model, Arrays.hashCode(abilities));
    }

    @Override
    public String toString() {
        return "VehicleWheel[" +
                "id=" + id + ", " +
                "size=" + size + ", " +
                "grip=" + grip + ", " +
                "model=" + model + ", " +
                "abilities=" + Arrays.toString(abilities) + ']';
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return requiredFeatures.get();
    }


    public enum Ability {
    }

    public record WheelModel(
            float radius,
            float width,
            Identifier texture,
            Identifier modelId
    ) {
        @Environment(EnvType.CLIENT)
        public Function<EntityRendererFactory.Context, Model> model() {
            return MotoboxModels.MODELS.get(modelId);
        }
    }
}
