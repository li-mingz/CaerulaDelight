package com.limingz.caerula_delight.registry;

import com.limingz.caerula_delight.CaerulaDelightMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, CaerulaDelightMod.MODID);

    public static final RegistryObject<SimpleParticleType> SANITY_VULNERABILITY_PARTICLE =
            PARTICLE_TYPES.register("sanity_vulnerability_particle",
                    () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}

