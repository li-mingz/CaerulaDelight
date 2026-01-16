package com.limingz.caerula_delight.client;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.item.SanityTooltip;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CaerulaDelightMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void registerTooltipFactory(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(SanityTooltip.class, tooltip -> new ClientSanityTooltip(tooltip));
    }
}

