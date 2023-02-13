package motobox.util.midnightcontrols;

import motobox.Motobox;
import motobox.entity.VehicleEntity;
import eu.midnightdust.midnightcontrols.ControlsMode;
import eu.midnightdust.midnightcontrols.client.MidnightControlsClient;
import eu.midnightdust.midnightcontrols.client.MidnightControlsConfig;
import eu.midnightdust.midnightcontrols.client.compat.CompatHandler;
import eu.midnightdust.midnightcontrols.client.compat.MidnightControlsCompat;
import eu.midnightdust.midnightcontrols.client.controller.ButtonBinding;
import eu.midnightdust.midnightcontrols.client.controller.ButtonCategory;
import eu.midnightdust.midnightcontrols.client.controller.InputManager;
import net.minecraft.client.MinecraftClient;
import org.aperlambda.lambdacommon.Identifier;
import org.aperlambda.lambdacommon.utils.function.PairPredicate;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static org.lwjgl.glfw.GLFW.*;

public class MotoboxMidnightControls implements CompatHandler {
    public static final PairPredicate<MinecraftClient, ButtonBinding> ON_VEHICLE = (client, button) -> client.player != null && client.player.getVehicle() instanceof VehicleEntity;

    public static final Set<ButtonBinding> VEHICLE_BINDINGS = new HashSet<>();

    public static final ButtonBinding ACCELERATE = binding(new ButtonBinding.Builder(Motobox.id("accelerate_vehicle"))
            .buttons(GLFW_GAMEPAD_BUTTON_A).filter(ON_VEHICLE).register());

    public static final ButtonBinding BRAKE = binding(new ButtonBinding.Builder(Motobox.id("brake_vehicle"))
            .buttons(GLFW_GAMEPAD_BUTTON_B).filter(ON_VEHICLE).register());

    public static final ButtonBinding DRIFT = binding(new ButtonBinding.Builder(Motobox.id("drift_vehicle"))
            .buttons(ButtonBinding.axisAsButton(GLFW_GAMEPAD_AXIS_RIGHT_TRIGGER, true)).filter(ON_VEHICLE).register());

    //                                                                                       There is 1 impostor among us
    public static final ButtonCategory VEHICLE_CATEGORY = InputManager.registerCategory(new Identifier(Motobox.MOD_ID, "motobox"));

    public static Supplier<Boolean> IN_CONTROLLER_MODE = () -> false;

    public static void init() {
        MidnightControlsCompat.registerCompatHandler(new MotoboxMidnightControls());
    }

    @Override
    public void handle(@NotNull MidnightControlsClient mod) {
        VEHICLE_CATEGORY.registerAllBindings(ACCELERATE, BRAKE, DRIFT);
        IN_CONTROLLER_MODE = () -> MidnightControlsConfig.controlsMode == ControlsMode.CONTROLLER;
    }

    private static ButtonBinding binding(ButtonBinding binding) {
        VEHICLE_BINDINGS.add(binding);
        return binding;
    }
}
