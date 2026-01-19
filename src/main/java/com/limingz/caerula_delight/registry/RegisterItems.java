package com.limingz.caerula_delight.registry;

import com.google.common.collect.Sets;
import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.item.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

import static vectorwing.farmersdelight.common.registry.ModItems.*;

public class RegisterItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CaerulaDelightMod.MODID);
    public static LinkedHashSet<RegistryObject<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    public static RegistryObject<Item> registerWithTab(final String name, final Supplier<Item> supplier) {
        RegistryObject<Item> block = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(block);
        return block;
    }

    public static final RegistryObject<Item> GLOW_SEA_PUDDING = registerWithTab("glow_sea_pudding",
            () -> new GlowSeaPuddingItem(foodItem(FoodValues.GLOW_SEA_PUDDING)));
    public static final RegistryObject<Item> SEA_TERROR_JELLY = registerWithTab("sea_terror_jelly",
            () -> new Item(foodItem(FoodValues.SEA_TERROR_JELLY)));

    public static final RegistryObject<Item> ROTTEN_FOAM = registerWithTab("rotten_foam",
            () -> new DrinkableItem(drinkItem().food(FoodValues.ROTTEN_FOAM), true, false));



    public static final RegistryObject<Item> SEA_TERROR_BLOOD = registerWithTab("sea_terror_blood",
            () -> new SeaTerrorBloodItem(drinkItem().food(FoodValues.SEA_TERROR_BLOOD)));
    public static final RegistryObject<Item> SEA_TERROR_CUTLET = registerWithTab("sea_terror_cutlet",
            () -> new Item(foodItem(FoodValues.SEA_TERROR_CUTLET)));
    public static final RegistryObject<Item> COOKED_SEA_TERROR_CUTLET = registerWithTab("cooked_sea_terror_cutlet",
            () -> new Item(foodItem(FoodValues.COOKED_SEA_TERROR_CUTLET)));
    public static final RegistryObject<Item> SEA_TERROR_CUTLET_STRIPS = registerWithTab("sea_terror_cutlet_strips",
            () -> new Item(foodItem(FoodValues.SEA_TERROR_CUTLET_STRIPS)));
    public static final RegistryObject<Item> COOKED_SEA_TERROR_CUTLET_STRIPS = registerWithTab("cooked_sea_terror_cutlet_strips",
            () -> new Item(foodItem(FoodValues.COOKED_SEA_TERROR_CUTLET_STRIPS)));


    // 刀
    public static final RegistryObject<Item> OCEAN_CHITIN_KNIFE = registerWithTab("ocean_chitin_knife",
            () -> new AquaticKnifeItem(CaerulaMaterials.OCEAN_CHITIN, 0.5F, -2.0F, basicItem()));
    public static final RegistryObject<Item> COMPLEX_CHITIN_KNIFE = registerWithTab("complex_chitin_knife",
            () -> new AquaticKnifeItem(CaerulaMaterials.COMPLEX_CHITIN, 0.5F, -2.0F, basicItem()));
    public static final RegistryObject<Item> TRAILRITE_KNIFE = registerWithTab("trailrite_knife",
            () -> new TrailriteKnifeItem(CaerulaMaterials.TRAILRITE, 0.5F, -2.0F, basicItem()));
}
