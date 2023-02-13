package motobox.item;

import motobox.vehicle.VehicleEngine;

public class VehicleEngineItem extends VehicleComponentItem<VehicleEngine> {
    public VehicleEngineItem(Settings settings) {
        super(settings, "engine", "engine", VehicleEngine.REGISTRY);
    }
}
