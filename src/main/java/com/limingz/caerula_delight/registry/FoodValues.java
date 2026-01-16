package com.limingz.caerula_delight.registry;

import net.mcreator.caerulaarbor.init.CaerulaArborModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FoodValues {
    public static final FoodProperties ROTTEN_FOAM = (new FoodProperties.Builder())
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 600, 0), 1.0F)
            .effect(() -> new MobEffectInstance(CaerulaArborModMobEffects.SANITY_IMMUE.get(), 6000, 0), 1.0F)
            .build();
}
