package com.limingz.caerula_delight.item;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.util.LightUtils;
import com.mojang.datafixers.util.Either;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class GlowSeaPuddingItem extends ConsumableItem
{
    public GlowSeaPuddingItem(Properties properties) {
        super(properties, true, false);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        LightUtils.addLight(consumer, 20d);
    }

    @Mod.EventBusSubscriber(modid = CaerulaDelightMod.MODID, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onRenderTooltip(RenderTooltipEvent.GatherComponents event) {
            if (event.getItemStack().getItem() instanceof GlowSeaPuddingItem) {
                if (event.getTooltipElements().stream().anyMatch(e -> e.right().isPresent() && e.right().get() instanceof LightTooltip)) {
                    return;
                }
                event.getTooltipElements().add(Either.right(new LightTooltip("+20")));
            }
        }
    }
}