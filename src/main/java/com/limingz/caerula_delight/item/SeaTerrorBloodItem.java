package com.limingz.caerula_delight.item;

import net.mcreator.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import vectorwing.farmersdelight.common.item.DrinkableItem;



public class SeaTerrorBloodItem extends DrinkableItem
{
    public SeaTerrorBloodItem(Properties properties) {
        super(properties, false, false);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        DeductPlayerSanityProcedure.execute(consumer, 1001.0);
    }


}