package motobox.vehicle;

import motobox.util.SimpleMapContentRegistry;
import net.minecraft.resource.featuretoggle.ToggleableFeature;

public interface VehicleComponent<T extends VehicleComponent<T>> extends SimpleMapContentRegistry.Identifiable, StatContainer<T>, ToggleableFeature {
    boolean isEmpty();
}
