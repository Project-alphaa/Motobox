package motobox.common;

import java.util.function.Supplier;

/**
 * Supplier for float primitive type.
 */
@FunctionalInterface
public interface FloatSupplier extends Supplier<Float> {
    static FloatSupplier direct(float value) {
        return () -> value;
    }

    /**
     * @return the float value.
     * @deprecated use {@link #getFloat()} instead.
     */
    @Deprecated
    @Override
    default Float get() {
        return getFloat();
    }

    float getFloat();
}
