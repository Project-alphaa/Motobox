package motobox.vehicle.render.wheel;

import motobox.vehicle.WheelBase;

public interface WheelContextReceiver {
    void provideContext(WheelBase.WheelPos pos);
}
