package com.limingz.caerula_delight.mixins;

import com.limingz.caerula_delight.registry.ModMobEffects;
import net.mcreator.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DeductPlayerSanityProcedure.class)
public class DeductPlayerSanityProcedureMixin {

    @ModifyVariable(method = "execute", at = @At("HEAD"), argsOnly = true, ordinal = 0, remap = false)
    private static double caerula_delight_modifySanityReduction(double num, Entity player) {
        if (player instanceof LivingEntity livingEntity) {
            MobEffectInstance effectInstance = livingEntity.getEffect(ModMobEffects.SANITY_VULNERABILITY.get());
            if (effectInstance != null) {
                int level = effectInstance.getAmplifier() + 1;
                // 每级额外提升10%
                num *= (1.0 + level * 0.10);
            }
        }
        return num;
    }
}

