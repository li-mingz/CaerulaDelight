package com.limingz.caerula_delight.client;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.client.particle.SanityVulnerabilityParticle;
import com.limingz.caerula_delight.registry.ModParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CaerulaDelightMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModParticleSubscriber {

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.SANITY_VULNERABILITY_PARTICLE.get(), SanityVulnerabilityParticle.SanityVulnerabilityParticleProvider::new);
    }
}

