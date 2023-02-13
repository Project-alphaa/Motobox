package motobox.vehicle;

public class OffsetBicycleWheelBase extends WheelBase {
    public OffsetBicycleWheelBase(float sepLong, float offset) {
        super(
                new WheelPos(offset + sepLong / 2, -0.1f, 1f, 0f, WheelEnd.FRONT, WheelSide.LEFT),
                new WheelPos(offset + sepLong / -2, -0.1f, 1f, 0f, WheelEnd.BACK, WheelSide.LEFT),
                new WheelPos(offset + sepLong / 2, 0.1f, 1f, -180f, WheelEnd.FRONT, WheelSide.RIGHT),
                new WheelPos(offset + sepLong / -2, 0.1f, 1f, -180f, WheelEnd.BACK, WheelSide.RIGHT)
        );
    }
}
