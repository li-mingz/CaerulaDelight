package com.limingz.caerula_delight.item;

import net.mcreator.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.mcreator.caerulaarbor.procedures.TrailSwordHitProcedure;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TrailriteKnifeItem  extends AquaticKnifeItem {
    public TrailriteKnifeItem(Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltip, isAdvanced);
        tooltip.add(Component.translatable("item.caerula_delight.trailrite_knife.tooltip").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        DeductPlayerSanityProcedure.execute(entity, 330.0);
        return retval;
    }

}
