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

    public static final FoodProperties SEA_TERROR_BLOOD = (new FoodProperties.Builder())
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 600, 0), 1.0F)
            .effect(() -> new MobEffectInstance(CaerulaArborModMobEffects.SANITY_IMMUE.get(), 6000, 0), 1.0F)
            .nutrition(1)
            .saturationMod(0.3f)
            .build();


    public static final FoodProperties GLOW_SEA_PUDDING = (new FoodProperties.Builder())
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 1800, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1800, 0), 1.0F)
            .nutrition(3).saturationMod(0.8f).build();
    public static final FoodProperties SEA_TERROR_JELLY = (new FoodProperties.Builder())
            .nutrition(3).saturationMod(0.8f).build();

    public static final FoodProperties SEA_TERROR_CUTLET = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.3f).meat().build();
    public static final FoodProperties COOKED_SEA_TERROR_CUTLET = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.8f).meat().build();
    public static final FoodProperties SEA_TERROR_CUTLET_STRIPS = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.3f).meat().fast().build();
    public static final FoodProperties COOKED_SEA_TERROR_CUTLET_STRIPS = (new FoodProperties.Builder())
			.nutrition(3).saturationMod(0.8f).meat().fast().build();

    public static final FoodProperties OCEANIZED_WITHER_BONE_BROTH = (new FoodProperties.Builder())
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 600, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 1), 1.0F)
            .effect(() -> new MobEffectInstance(CaerulaArborModMobEffects.ADD_ATTACK_PERCLY.get(), 3600, 3), 1.0F)
            .nutrition(10)
            .saturationMod(1.2f)
            .build();
}
