package motobox.featuretoggle;

import net.minecraft.resource.featuretoggle.FeatureManager;

public interface FeatureFlagRegistration {
    void registerFeatureFlags(FeatureManager.Builder builder);
}
