package com.limingz.caerula_delight.client;

import com.limingz.caerula_delight.CaerulaDelightMod;

import com.limingz.caerula_delight.item.*;
import com.limingz.caerula_delight.registry.RegisterItems;
import com.mojang.datafixers.util.Either;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CaerulaDelightMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientForgeEventSubscriber {

    @SubscribeEvent
    public static void onRenderTooltip(RenderTooltipEvent.GatherComponents event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof SeaTerrorBloodItem) {
            event.getTooltipElements().add(Either.right(new SanityTooltip("-1001")));
        }
        if (stack.getItem() == RegisterItems.OCEANIZED_WITHER_BONE_BROTH.get()) {
            event.getTooltipElements().add(Either.right(new SanityTooltip("-1001")));
        }

        if (stack.getItem() instanceof GlowSeaPuddingItem) {
            if (event.getTooltipElements().stream().anyMatch(e -> e.right().isPresent() && e.right().get() instanceof LightTooltip)) {
                return;
            }
            event.getTooltipElements().add(Either.right(new LightTooltip("+20")));
        }
        if (stack.getItem() instanceof StarfieldShavedIceItem) {
            if (event.getTooltipElements().stream().anyMatch(e -> e.right().isPresent() && e.right().get() instanceof LightTooltip)) {
                return;
            }
            event.getTooltipElements().add(Either.right(new LightTooltip("+15")));
        }
    }
}
