package com.limingz.caerula_delight.mixins;

import com.limingz.caerula_delight.registry.ModAttributes;
import net.mcreator.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DeductPlayerSanityProcedure.class)
public class DeductPlayerSanityProcedureMixin {

    @ModifyVariable(method = "execute", at = @At("HEAD"), argsOnly = true, ordinal = 0, remap = false)
    private static double caerula_delight_modifySanityReduction(double num, Entity player) {
        if (player instanceof LivingEntity livingEntity
                && livingEntity.getAttributes().hasAttribute(ModAttributes.SANITY_VULNERABILITY.get())) {
            double vulnerability = livingEntity.getAttributeValue(ModAttributes.SANITY_VULNERABILITY.get());
            if (vulnerability > 0.0) {
                num *= (1.0 + vulnerability);
            }
        }
        return num;
    }
}
