package com.limingz.caerula_delight;

import com.limingz.caerula_delight.registry.ModBlock;
import com.limingz.caerula_delight.registry.ModBlockEntities;
import com.limingz.caerula_delight.registry.ModCreativeTabs;
import com.limingz.caerula_delight.registry.ModLootModifiers;
import com.limingz.caerula_delight.registry.ModRecipeSerializers;
import com.limingz.caerula_delight.registry.RegisterItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CaerulaDelightMod.MODID)
public class CaerulaDelightMod
{
    public static final String MODID = "caerula_delight";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CaerulaDelightMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        RegisterItems.ITEMS.register(modEventBus);
        ModBlock.BLOCKS.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModCreativeTabs.CREATIVE_TABS.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
    }
}
