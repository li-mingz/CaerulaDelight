package com.limingz.caerula_delight.client;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.item.*;
import com.limingz.caerula_delight.registry.LightFoodRegistry;
import com.limingz.caerula_delight.registry.SanityFoodRegistry;
import com.limingz.caerula_delight.util.SanityEffect;
import com.mojang.datafixers.util.Either;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = CaerulaDelightMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientForgeEventSubscriber {

    @SubscribeEvent
    public static void onRenderTooltip(RenderTooltipEvent.GatherComponents event) {
        ItemStack stack = event.getItemStack();
        List<SanityEffect> effects = SanityFoodRegistry.getEffects(stack);
        if (!effects.isEmpty()) {
            if (event.getTooltipElements().stream().noneMatch(e -> e.right().isPresent() && e.right().get() instanceof SanityTooltip)) {
                for (SanityEffect effect : effects) {
                    String suffix = buildSuffix(effect);
                    event.getTooltipElements().add(Either.right(new SanityTooltip(effect.delta(), suffix)));
                }
            }
        }

        Double lightDelta = LightFoodRegistry.getLightDelta(stack);
        if (lightDelta != null) {
            if (event.getTooltipElements().stream().noneMatch(e -> e.right().isPresent() && e.right().get() instanceof LightTooltip)) {
                String sign = lightDelta > 0.0 ? "+" : "";
                event.getTooltipElements().add(Either.right(new LightTooltip(sign + Math.round(lightDelta))));
            }
        }
    }

    private static String buildSuffix(SanityEffect effect) {
        StringBuilder suffix = new StringBuilder();
        if (effect.isDelayed()) {
            suffix.append("延迟");
        }
        if (effect.isSustained()) {
            if (!suffix.isEmpty()) {
                suffix.append(" ");
            }
            suffix.append("持续");
        }
        return suffix.toString();
    }
}
