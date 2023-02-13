package motobox.common;

import net.minecraft.util.function.ToFloatFunction;

public interface ToFloatFunctionImpl<C> extends ToFloatFunction<C> {
    @Override
    default float min() {
        return Float.MIN_VALUE;
    }

    @Override
    default float max() {
        return Float.MAX_VALUE;
    }
}
