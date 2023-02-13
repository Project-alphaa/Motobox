package motobox;

import motobox.featuretoggle.FeatureFlagRegistration;
import motobox.featuretoggle.MotoboxFeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureManager;

public class MotoboxFeatureFlagsRegister implements FeatureFlagRegistration {
    @Override
    public void registerFeatureFlags(FeatureManager.Builder builder) {
        MotoboxFeatureFlags.init(builder);
    }
}
