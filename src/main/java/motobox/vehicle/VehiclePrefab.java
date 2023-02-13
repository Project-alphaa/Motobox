package motobox.vehicle;

import motobox.item.MotoboxItems;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.ToggleableFeature;
import net.minecraft.util.Identifier;

import java.util.Objects;
import java.util.function.Supplier;

public final class VehiclePrefab implements ToggleableFeature {
    private final Identifier id;
    private final VehicleFrame frame;
    private final VehicleWheel wheel;
    private final VehicleEngine engine;
    private final Supplier<FeatureSet> requiredFeatures;

    public VehiclePrefab(Identifier id, VehicleFrame frame, VehicleWheel wheel, VehicleEngine engine) {
        this(id, frame, wheel, engine, FeatureSet::empty);
    }

    public VehiclePrefab(Identifier id, VehicleFrame frame, VehicleWheel wheel, VehicleEngine engine, Supplier<FeatureSet> requiredFeatures) {
        this.id = id;
        this.frame = frame;
        this.wheel = wheel;
        this.engine = engine;
        this.requiredFeatures = requiredFeatures;
    }

    public ItemStack toStack() {
        var stack = new ItemStack(MotoboxItems.VEHICLE);
        var vehicle = stack.getOrCreateSubNbt("Vehicle");
        vehicle.putString("frame", frame().getId().toString());
        vehicle.putString("wheels", wheel().getId().toString());
        vehicle.putString("engine", engine().getId().toString());
        vehicle.putBoolean("isPrefab", true);
        var display = stack.getOrCreateSubNbt("display");
        display.putString("Name", String.format("{\"translate\":\"prefab.%s.%s\",\"italic\":\"false\"}", id().getNamespace(), id().getPath()));
        return stack;
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return requiredFeatures.get();
    }

    public Identifier id() {
        return id;
    }

    public VehicleFrame frame() {
        return frame;
    }

    public VehicleWheel wheel() {
        return wheel;
    }

    public VehicleEngine engine() {
        return engine;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (VehiclePrefab) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.frame, that.frame) &&
                Objects.equals(this.wheel, that.wheel) &&
                Objects.equals(this.engine, that.engine) &&
                Objects.equals(this.requiredFeatures, that.requiredFeatures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, frame, wheel, engine, requiredFeatures);
    }

    @Override
    public String toString() {
        return "VehiclePrefab[" +
                "id=" + id + ", " +
                "frame=" + frame + ", " +
                "wheel=" + wheel + ", " +
                "engine=" + engine + ", " +
                "requiredFeatures=" + requiredFeatures + ']';
    }

}
