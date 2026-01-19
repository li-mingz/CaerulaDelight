package com.limingz.caerula_delight.item;

import net.mcreator.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;




public class OceanizedWitherBoneBrothItem  extends ConsumableItem {
    public OceanizedWitherBoneBrothItem(Item.Properties properties) {
        super(properties, true, false);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        DeductPlayerSanityProcedure.execute(consumer, 1001);
    }


}
