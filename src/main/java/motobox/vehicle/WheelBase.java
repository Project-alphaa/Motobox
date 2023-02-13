package motobox.vehicle;

public class WheelBase {
    public final WheelPos[] wheels;
    public final int wheelCount;

    public WheelBase(WheelPos ... wheels) {
        this.wheels = wheels;
        this.wheelCount = wheels.length;
    }

    public enum WheelSide {
        LEFT,
        RIGHT
    }

    public enum WheelEnd {
        FRONT,
        BACK
    }

    public record WheelPos(float forward, float right, float scale, float yaw, WheelEnd end, WheelSide side) {}

    public static WheelBase basic(float separationLong, float separationWide) {
        return new BasicWheelBase(separationLong, separationWide);
    }

    public static WheelBase offset(float separationLong, float separationWide, float offset) {
        return new OffsetWheelBase(separationLong, separationWide, offset);
    }

    public static WheelBase bicycle(float separationLong) {
        return new BicycleWheelBase(separationLong);
    }

    public static WheelBase bicycleOffset(float separationLong, float offset) {
        return new OffsetBicycleWheelBase(separationLong, offset);
    }
}
