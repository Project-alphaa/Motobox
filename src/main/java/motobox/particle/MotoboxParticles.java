package motobox.particle;

import motobox.Motobox;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class MotoboxParticles {
    public static final DefaultParticleType DRIFT_SMOKE = Registry.register(Registries.PARTICLE_TYPE, Motobox.id("drift_smoke"), FabricParticleTypes.simple(true));

    public static void init() {
    }

    public static void initClient() {
        ParticleFactoryRegistry.getInstance().register(DRIFT_SMOKE, DriftSmokeParticle.Factory::new);
    }
}
