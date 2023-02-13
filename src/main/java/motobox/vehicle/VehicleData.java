package motobox.vehicle;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class VehicleData {
    private VehicleFrame frame;
    private VehicleWheel wheel;
    private VehicleEngine engine;
    private boolean prefab;

    public VehicleData() {
    }

    public void read(NbtCompound nbt) {
        frame = VehicleFrame.REGISTRY.getOrDefault(Identifier.tryParse(nbt.getString("frame")));
        wheel = VehicleWheel.REGISTRY.getOrDefault(Identifier.tryParse(nbt.getString("wheels")));
        engine = VehicleEngine.REGISTRY.getOrDefault(Identifier.tryParse(nbt.getString("engine")));
        prefab = nbt.contains("isPrefab") && nbt.getBoolean("isPrefab");
    }

    public VehicleFrame getFrame() {
        return frame;
    }

    public VehicleWheel getWheel() {
        return wheel;
    }

    public VehicleEngine getEngine() {
        return engine;
    }

    public boolean isPrefab() {
        return prefab;
    }
}
