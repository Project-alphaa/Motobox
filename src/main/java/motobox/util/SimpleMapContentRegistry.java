package motobox.util;

import motobox.Motobox;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.impl.launch.knot.Knot;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.ToggleableFeature;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SimpleMapContentRegistry<V extends SimpleMapContentRegistry.Identifiable> {
    private final Map<Identifier, V> entries = new Object2ObjectOpenHashMap<>();
    private final List<Identifier> orderedKeys = new ArrayList<>();

    public SimpleMapContentRegistry() {
    }

    public V register(V entry) {
        entries.put(entry.getId(), entry);
        orderedKeys.add(entry.getId());
        return entry;
    }

    public V get(Identifier name) {
        V v = entries.get(name);
        if (v instanceof ToggleableFeature feature) {
            if (!feature.isEnabled(getEnabledFeatures())) {
                return null;
            }
        }
        return v;
    }

    private FeatureSet getEnabledFeaturesClient() {
        return MinecraftClient.getInstance().player != null ? MinecraftClient.getInstance().player.networkHandler.getEnabledFeatures() : FeatureSet.empty();
    }

    public V getOrDefault(Identifier name) {
        if (orderedKeys.isEmpty()) throw new IllegalStateException("Tried to get from empty registry!");
        return entries.getOrDefault(name, entries.get(orderedKeys.get(0)));
    }

    public void forEach(Consumer<V> action) {
        orderedKeys.forEach(k -> action.accept(entries.get(k)));
    }

    public FeatureSet getEnabledFeatures() {
        if (Knot.getLauncher().getEnvironmentType() == EnvType.CLIENT) {
            return getEnabledFeaturesClient();
        } else {
            return Motobox.server().getSaveProperties().getEnabledFeatures();
        }
    }

    public interface Identifiable {
        Identifier getId();
    }
}
