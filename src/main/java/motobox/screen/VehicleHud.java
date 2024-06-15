package motobox.screen;

import com.google.common.collect.Lists;
import motobox.entity.VehicleEntity;
import motobox.util.AUtils;
import motobox.util.midnightcontrols.ControllerUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.List;
import java.util.function.Function;

public enum VehicleHud {
    ;
    public static final List<ControlHint> CONTROL_HINTS = Lists.newArrayList(
            new ControlHint("accelerate", options -> options.forwardKey),
            new ControlHint("brake", options -> options.backKey),
            new ControlHint("steer_left", options -> options.leftKey),
            new ControlHint("steer_right", options -> options.rightKey),
            new ControlHint("drift", options -> options.jumpKey)
    );

    public static void render(DrawContext context, PlayerEntity player, VehicleEntity vehicle, float tickDelta) {
        renderSpeedometer(context, vehicle);

        if (!ControllerUtils.inControllerMode()) {
            float alpha = Math.max(0, (vehicle.getStandStillTime() * 2) - 1);

            // Check on a 0-255 converted version of alpha, because 0 alpha will render things at 100% alpha for some
            // reason, and small enough numbers (which would result in 0 alpha as an int, but non zero as a float) would
            // result in a brief tick of 100% alpha, messing up the smoothness of the fade in animation
            if ((int) (alpha * 0xFF) > 0) {
                renderControlHints(context, alpha);
            }
        }
    }

    private static void renderSpeedometer(DrawContext context, VehicleEntity vehicle) {
        float speed = (float) vehicle.getEffectiveSpeed() * 20;
        int color = 0xFFFFFF;
        if (vehicle.getBoostTimer() > 0) color = 0xFF6F00;
        if (vehicle.getTurboCharge() > VehicleEntity.SMALL_TURBO_TIME) color = 0xFFEA4A;
        if (vehicle.getTurboCharge() > VehicleEntity.MEDIUM_TURBO_TIME) color = 0x7DE9FF;
        if (vehicle.getTurboCharge() > VehicleEntity.LARGE_TURBO_TIME) color = 0x906EFF;
        context.drawText(MinecraftClient.getInstance().textRenderer, Text.literal(AUtils.DEC_TWO_PLACES.format(convert(speed)) + " km/h"), 20, 20, color, true);
    }

    private static float convert(float blockPerSecond) {
        return (float) (blockPerSecond * 3.6);
    }

    private static void renderControlHints(DrawContext context, float alpha) {
        int x = 20;
        int y = 50;
        var options = MinecraftClient.getInstance().options;
        var font = MinecraftClient.getInstance().textRenderer;

        for (var control : CONTROL_HINTS) {
            var keyTxt = control.getKeybindText(options);
            int keyTxtWid = font.getWidth(keyTxt);

            context.fill(x, y, x + keyTxtWid + 6, y + 14, ((int)(alpha * 0xAB) << 24));

            int textColor = 0x00FFFFFF | ((int)(alpha * 0xFF) << 24);
            context.drawText(font, keyTxt, x + 3, y + 3, textColor, true);
            context.drawText(font, control.getText(), x + keyTxtWid + 9, y + 3, textColor, true);

            y += 17;
        }
    }

    public record ControlHint(String name, Function<GameOptions, KeyBinding> keybind) {
        public Text getText() {
            return Text.translatable("vehicle_control." + name);
        }

        public Text getKeybindText(GameOptions options) {
            return keybind.apply(options).getBoundKeyLocalizedText();
        }
    }
}
