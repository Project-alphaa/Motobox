package motobox.vehicle;

public class OffsetWheelBase extends WheelBase {
    public OffsetWheelBase(float sepLong, float sepWide, float offset) {
        super(
                new WheelPos(offset + sepLong / 2, sepWide / -2, 1, 0, WheelEnd.FRONT, WheelSide.LEFT),
                new WheelPos(offset + sepLong / -2, sepWide / -2, 1, 0, WheelEnd.BACK, WheelSide.LEFT),
                new WheelPos(offset + sepLong / 2, sepWide / 2, 1, 180, WheelEnd.FRONT, WheelSide.RIGHT),
                new WheelPos(offset + sepLong / -2, sepWide / 2, 1, 180, WheelEnd.BACK, WheelSide.RIGHT)
        );
    }
}
