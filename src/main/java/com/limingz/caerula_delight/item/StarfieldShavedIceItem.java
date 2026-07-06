package com.limingz.caerula_delight.item;

import net.mcreator.caerulaarbor.procedures.RevivesPlayerLightsProcedure;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class StarfieldShavedIceItem extends ConsumableItem
{
    public StarfieldShavedIceItem(Properties properties) {
        super(properties, true, false);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        RevivesPlayerLightsProcedure.execute(consumer, 15.0);
    }


}
