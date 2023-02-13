package motobox.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.resource.featuretoggle.FeatureManager;

public interface FeatureFlagRegisterEvent {
    Event<FeatureFlagRegisterEvent> EVENT = EventFactory.createArrayBacked(FeatureFlagRegisterEvent.class, callbacks -> (builder) -> {
        for (FeatureFlagRegisterEvent callback : callbacks) {
            if (!callback.onRegisterFeatureFlags(builder)) {
                return false;
            }
        }

        return true;
    });

    boolean onRegisterFeatureFlags(FeatureManager.Builder builder);
}
