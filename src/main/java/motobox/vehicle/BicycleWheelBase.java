package motobox.vehicle;

public class BicycleWheelBase extends WheelBase {
    public BicycleWheelBase(float sepLong) {
        super(
                new WheelPos(sepLong / 2, -0.1f, 1f, 0f, WheelEnd.FRONT, WheelSide.LEFT),
                new WheelPos(sepLong / -2, -0.1f, 1f, 0f, WheelEnd.BACK, WheelSide.LEFT),
                new WheelPos(sepLong / 2, 0.1f, 1f, 0f, WheelEnd.FRONT, WheelSide.RIGHT),
                new WheelPos(sepLong / -2, 0.1f, 1f, 0f, WheelEnd.BACK, WheelSide.RIGHT)
        );
    }
}
