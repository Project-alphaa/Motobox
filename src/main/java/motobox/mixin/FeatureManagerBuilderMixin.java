package motobox.mixin;

import net.minecraft.resource.featuretoggle.FeatureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FeatureManager.Builder.class)
public class FeatureManagerBuilderMixin {
    @ModifyConstant(method = "addFlag", constant = @Constant(intValue = 64))
    private static int motobox$modifyLimit(int constant) {
        return Integer.MAX_VALUE;
    }
}
