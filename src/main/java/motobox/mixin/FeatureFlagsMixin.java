package motobox.mixin;

import motobox.event.FeatureFlagRegisterEvent;
import motobox.featuretoggle.FeatureFlagRegistration;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FeatureFlags.class)
public class FeatureFlagsMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/featuretoggle/FeatureManager$Builder;build()Lnet/minecraft/resource/featuretoggle/FeatureManager;"), method = "<clinit>", locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void motobox$injectExtraFeatures(CallbackInfo ci, FeatureManager.Builder builder) {
        var containers = FabricLoader.getInstance().getEntrypointContainers("motobox:feature_flags", FeatureFlagRegistration.class);
        for (EntrypointContainer<FeatureFlagRegistration> container : containers) {
            container.getEntrypoint().registerFeatureFlags(builder);
        }
        FeatureFlagRegisterEvent.EVENT.invoker().onRegisterFeatureFlags(builder);
    }
}
