package motobox.entity.ufo;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class UfoInput {

    private static final String UFO_CATEGORY = "category.ufos.ufocontrols";

    private static KeyBinding upKey = new KeyBinding("ufo.key.up", GLFW.GLFW_KEY_SPACE, UFO_CATEGORY);
    private static KeyBinding downKey = new KeyBinding("ufo.key.down", GLFW.GLFW_KEY_LEFT_CONTROL, UFO_CATEGORY);

    public static boolean pressingForward() {
        return MinecraftClient.getInstance().options.forwardKey.isPressed();
    }
    public static boolean pressingBackward() {
        return MinecraftClient.getInstance().options.backKey.isPressed();
    }
    public static boolean pressingLeft() {
        return MinecraftClient.getInstance().options.leftKey.isPressed();
    }
    public static boolean pressingRight() {
        return MinecraftClient.getInstance().options.rightKey.isPressed();
    }
    public static boolean pressingUp() {
        return MinecraftClient.getInstance().options.jumpKey.isPressed();
    }
    public static boolean pressingDown() {
        return downKey.isPressed();
    }

    public static boolean receivingInput() {
        return
                pressingForward() ||
                        pressingBackward() ||
                        pressingLeft() ||
                        pressingRight() ||
                        pressingUp() ||
                        pressingDown();
    }

    public static void registerKeybinds() {
        upKey = KeyBindingHelper.registerKeyBinding(upKey);
        downKey = KeyBindingHelper.registerKeyBinding(downKey);
    }
}