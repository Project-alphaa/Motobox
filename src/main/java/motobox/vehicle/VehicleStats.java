package motobox.vehicle;

import motobox.Motobox;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class VehicleStats implements StatContainer<VehicleStats> {
    public static final Identifier ID = Motobox.id("vehicle");
    public static final DisplayStat<VehicleStats> STAT_ACCELERATION = new DisplayStat<>("acceleration", VehicleStats::getAcceleration);
    public static final DisplayStat<VehicleStats> STAT_COMFORTABLE_SPEED = new DisplayStat<>("comfortable_speed", stats -> stats.getComfortableSpeed() * 20);
    public static final DisplayStat<VehicleStats> STAT_HANDLING = new DisplayStat<>("handling", VehicleStats::getHandling);
    public static final DisplayStat<VehicleStats> STAT_GRIP = new DisplayStat<>("grip", VehicleStats::getGrip);

    private float acceleration = 0;     // 0-1
    private float comfortableSpeed = 0; // Blocks per Tick
    private float handling = 0;         // 0-1
    private float grip = 0;             // 0-1

    public VehicleStats() {
    }

    public void from(VehicleFrame frame, VehicleWheel wheel, VehicleEngine engine) {
        this.acceleration = ((1 - ((frame.weight() + wheel.size()) / 2)) + (2 * engine.torque()) / 3);
        this.comfortableSpeed = ((engine.speed() * 3) + ((engine.speed() * frame.weight()) * 2) + (engine.speed() * wheel.size())) / 5.7f;
        this.handling = ((1 - wheel.size()) + (1 - frame.weight()) + 2) / 4;
        this.grip = (wheel.grip() + frame.weight()) / 2;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public float getComfortableSpeed() {
        return comfortableSpeed;
    }

    public float getHandling() {
        return handling;
    }

    public float getGrip() {
        return grip;
    }

    @Override
    public Identifier containerId() {
        return ID;
    }

    @Override
    public void forEachStat(Consumer<DisplayStat<VehicleStats>> action) {
        action.accept(STAT_ACCELERATION);
        action.accept(STAT_COMFORTABLE_SPEED);
        action.accept(STAT_HANDLING);
        action.accept(STAT_GRIP);
    }
}
