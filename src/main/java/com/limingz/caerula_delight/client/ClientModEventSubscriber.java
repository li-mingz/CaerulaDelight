package com.limingz.caerula_delight.client;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.item.SanityTooltip;
import com.limingz.caerula_delight.item.LightTooltip;
import com.limingz.caerula_delight.client.renderer.SimpleFeastRenderer;
import com.limingz.caerula_delight.registry.ModBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import com.limingz.caerula_delight.registry.RegisterItems;

@Mod.EventBusSubscriber(modid = CaerulaDelightMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(RegisterItems.TRAILRITE_FISHING_ROD.get(), new ResourceLocation("cast"), (stack, level, entity, seed) -> {
                if (entity == null) {
                    return 0.0F;
                } else {
                    boolean flag = entity.getMainHandItem() == stack;
                    boolean flag1 = entity.getOffhandItem() == stack;
                    // 优先处理主手持钓鱼竿的情况
                    if (entity.getMainHandItem().getItem() instanceof FishingRodItem) {
                        flag1 = false;
                    }
                    return (flag || flag1) && entity instanceof Player && ((Player)entity).fishing != null ? 1.0F : 0.0F;
                }
            });
        });
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.GLOW_SEA_PUDDING_ENTITY.get(), context -> new SimpleFeastRenderer());
    }

    @SubscribeEvent
    public static void registerTooltipFactory(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(SanityTooltip.class, ClientSanityTooltip::new);
        event.register(LightTooltip.class, ClientLightTooltip::new);
    }
}
