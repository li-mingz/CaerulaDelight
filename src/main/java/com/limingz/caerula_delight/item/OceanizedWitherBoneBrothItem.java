package com.limingz.caerula_delight.item;

import com.limingz.caerula_delight.api.ISanityAffecting;
import net.mcreator.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;




public class OceanizedWitherBoneBrothItem extends ConsumableItem implements ISanityAffecting {
    public static final double SANITY_DELTA = -1001.0;

    public OceanizedWitherBoneBrothItem(Item.Properties properties) {
        super(properties, true, false);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        DeductPlayerSanityProcedure.execute(consumer, -SANITY_DELTA);
    }

    @Override
    public double getSanityDelta(ItemStack stack, Player player) {
        return SANITY_DELTA;
    }


}
