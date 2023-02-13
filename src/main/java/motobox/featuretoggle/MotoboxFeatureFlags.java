package motobox.featuretoggle;

import motobox.Motobox;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.resource.featuretoggle.FeatureManager;

public class MotoboxFeatureFlags {
    public static FeatureFlag CARAVAN;

    public static void init(FeatureManager.Builder builder) {
        CARAVAN = builder.addFlag(Motobox.id("caravan"));
    }
}
