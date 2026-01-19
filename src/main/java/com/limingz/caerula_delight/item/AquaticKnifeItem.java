package com.limingz.caerula_delight.item;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.registry.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.List;

public class AquaticKnifeItem extends KnifeItem {
    public AquaticKnifeItem(Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltip, isAdvanced);
        tooltip.add(Component.translatable("item.caerula_delight.aquatic_knife.tooltip").withStyle(ChatFormatting.GRAY));
    }

    @Mod.EventBusSubscriber(modid = CaerulaDelightMod.MODID)
    public static class Events {
        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
            if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                ItemStack heldItem = attacker.getMainHandItem();
                if (heldItem.getItem() instanceof AquaticKnifeItem) {
                    LivingEntity target = event.getEntity();
                    if (target.getMobType() == MobType.WATER || target.getType().is(ModTags.OCEAN_OFFSPRING)) {
                        event.setAmount(event.getAmount() * 1.3F);
                    }
                }
            }
        }
    }
}
