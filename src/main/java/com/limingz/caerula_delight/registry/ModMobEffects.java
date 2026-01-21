package com.limingz.caerula_delight.registry;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.effect.SanityVulnerabilityEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CaerulaDelightMod.MODID);

    public static final RegistryObject<MobEffect> SANITY_VULNERABILITY = MOB_EFFECTS.register("sanity_vulnerability",
            () -> new SanityVulnerabilityEffect(MobEffectCategory.HARMFUL, 0xFFFFFF));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}

