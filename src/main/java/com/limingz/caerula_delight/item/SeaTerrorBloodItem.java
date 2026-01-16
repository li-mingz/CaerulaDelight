package com.limingz.caerula_delight.item;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.mojang.datafixers.util.Either;
import net.mcreator.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class SeaTerrorBloodItem extends DrinkableItem
{
    public SeaTerrorBloodItem(Properties properties) {
        super(properties, false, false);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        DeductPlayerSanityProcedure.execute(consumer, 1001);
    }

    @Mod.EventBusSubscriber(modid = CaerulaDelightMod.MODID, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onRenderTooltip(RenderTooltipEvent.GatherComponents event) {
            if (event.getItemStack().getItem() instanceof SeaTerrorBloodItem) {
                event.getTooltipElements().add(Either.right(new SanityTooltip("-1001")));
            }
        }
    }
}