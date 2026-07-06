package com.limingz.caerula_delight;

import com.limingz.caerula_delight.registry.ModAttributes;
import com.limingz.caerula_delight.registry.ModBlock;
import com.limingz.caerula_delight.registry.ModBlockEntities;
import com.limingz.caerula_delight.registry.ModCreativeTabs;
import com.limingz.caerula_delight.registry.ModLootModifiers;
import com.limingz.caerula_delight.registry.ModMobEffects;
import com.limingz.caerula_delight.registry.ModParticles;
import com.limingz.caerula_delight.registry.ModRecipeSerializers;
import com.limingz.caerula_delight.registry.RegisterItems;
import com.limingz.caerula_delight.util.SanityFoodRegistry;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(CaerulaDelightMod.MODID)
public class CaerulaDelightMod
{
    public static final String MODID = "caerula_delight";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CaerulaDelightMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener((FMLCommonSetupEvent event) ->
                SanityFoodRegistry.initialize());

        RegisterItems.ITEMS.register(modEventBus);
        ModBlock.BLOCKS.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModCreativeTabs.CREATIVE_TABS.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModAttributes.register(modEventBus);
        ModMobEffects.register(modEventBus);
        ModParticles.register(modEventBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
    }
}
