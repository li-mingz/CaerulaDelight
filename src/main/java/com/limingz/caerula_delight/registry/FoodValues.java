package com.limingz.caerula_delight.registry;

import net.mcreator.caerulaarbor.init.CaerulaArborModMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.registries.RegistryObject;

public class FoodValues {
    public static final int DURATION_5S = 100;
    public static final int DURATION_30S = 600;
    public static final int DURATION_1M30S = 1800;
    public static final int DURATION_3M = 3600;
    public static final int DURATION_5M = 6000;

    private static final RegistryObject<MobEffect> FD_NOURISHMENT =
            vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT;
    public static final FoodProperties ROTTEN_FOAM = (new FoodProperties.Builder())
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, DURATION_30S, 0), 1.0F)
            .effect(() -> new MobEffectInstance(CaerulaArborModMobEffects.SANITY_IMMUE.get(), DURATION_5M, 0), 1.0F)
            .build();
    public static final FoodProperties STARFIELD_SHAVED_ICE = (new FoodProperties.Builder())
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, DURATION_5M, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, DURATION_5M, 0), 1.0F)
            .nutrition(8)
            .saturationMod(0.5f)
            .build();

    public static final FoodProperties SEA_TERROR_BLOOD = (new FoodProperties.Builder())
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, DURATION_30S, 0), 1.0F)
            .effect(() -> new MobEffectInstance(CaerulaArborModMobEffects.SANITY_IMMUE.get(), DURATION_5M, 0), 1.0F)
            .nutrition(1)
            .saturationMod(0.3f)
            .build();


    public static final FoodProperties GLOW_SEA_PUDDING = (new FoodProperties.Builder())
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, DURATION_1M30S, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, DURATION_1M30S, 0), 1.0F)
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
    public static final FoodProperties SEA_TERROR_SASHIMI = (new FoodProperties.Builder())
            .nutrition(12).saturationMod(0.6f).meat().build();
    public static final FoodProperties COLLECTOR_SUSHI = (new FoodProperties.Builder())
            .nutrition(7).saturationMod(0.6f).build();
    public static final FoodProperties SEA_TERROR_SUSHI = (new FoodProperties.Builder())
            .nutrition(7).saturationMod(0.6f).build();
    public static final FoodProperties JELLYFISH_SKIN = (new FoodProperties.Builder())
            .effect(() -> new MobEffectInstance(CaerulaArborModMobEffects.INVULNERABLE.get(), DURATION_5S, 0), 1.0F)
            .nutrition(2)
            .saturationMod(1f)
            .fast()
            .build();

    public static final FoodProperties OCEANIZED_WITHER_BONE_BROTH = (new FoodProperties.Builder())
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, DURATION_30S, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, DURATION_3M, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, DURATION_3M, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, DURATION_3M, 1), 1.0F)
            .effect(() -> new MobEffectInstance(CaerulaArborModMobEffects.ADD_ATTACK_PERCLY.get(), DURATION_3M, 3), 1.0F)
            .nutrition(10)
            .saturationMod(1.2f)
            .build();

    public static final FoodProperties SEAFOOD_FRIED_RICE = (new FoodProperties.Builder())
            .effect(() -> new MobEffectInstance(FD_NOURISHMENT.get(), DURATION_3M, 0), 1.0F)
            .nutrition(16)
            .saturationMod(0.75f)
            .build();
}
