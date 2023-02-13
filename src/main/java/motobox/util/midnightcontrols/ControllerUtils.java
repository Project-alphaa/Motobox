package motobox.util.midnightcontrols;

import net.fabricmc.loader.api.FabricLoader;

public enum ControllerUtils {;
    public static boolean accelerating() {
        return isMidnightControlsLoaded() && MotoboxMidnightControls.ACCELERATE.isButtonDown();
    }

    public static boolean braking() {
        return isMidnightControlsLoaded() && MotoboxMidnightControls.BRAKE.isButtonDown();
    }

    public static boolean drifting() {
        return isMidnightControlsLoaded() && MotoboxMidnightControls.DRIFT.isButtonDown();
    }

    public static boolean inControllerMode() {
        return isMidnightControlsLoaded() && MotoboxMidnightControls.IN_CONTROLLER_MODE.get();
    }

    public static void initMidnightControlsHandler() {
        if (isMidnightControlsLoaded()) MotoboxMidnightControls.init();
    }

    public static boolean isMidnightControlsLoaded() {
        return FabricLoader.getInstance().isModLoaded("midnightcontrols");
    }
}
