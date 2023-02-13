package motobox;

import java.awt.*;

public class ColorUtils {
    public static int awtToMinecraft(Color color) {
        return (color.getAlpha() << 24)
                | (color.getRed() << 16)
                | (color.getGreen() << 8)
                | (color.getBlue());
    }
}
