package motobox.item;

import motobox.vehicle.VehicleWheel;

public class VehicleWheelItem extends VehicleComponentItem<VehicleWheel> {
    public VehicleWheelItem(Settings settings) {
        super(settings, "wheel", "wheel", VehicleWheel.REGISTRY);
    }

    @Override
    protected boolean addToCreative(VehicleWheel component) {
        return super.addToCreative(component);
    }
}
