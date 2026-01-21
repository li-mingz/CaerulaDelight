package com.limingz.caerula_delight.registry;

import com.google.common.collect.Sets;
import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.item.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.LinkedHashSet;
import java.util.List;
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
    public static final RegistryObject<Item> STARFIELD_SHAVED_ICE = registerWithTab("starfield_shaved_ice",
            () -> new StarfieldShavedIceItem(drinkItem().food(FoodValues.STARFIELD_SHAVED_ICE)));



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
    public static final RegistryObject<Item> SEA_TERROR_SASHIMI = registerWithTab("sea_terror_sashimi",
            () -> new ConsumableItem(bowlFoodItem(FoodValues.SEA_TERROR_SASHIMI), false, false));
    public static final RegistryObject<Item> COLLECTOR_SUSHI = registerWithTab("collector_sushi",
            () -> new Item(foodItem(FoodValues.COLLECTOR_SUSHI)));
    public static final RegistryObject<Item> SEA_TERROR_SUSHI = registerWithTab("sea_terror_sushi",
            () -> new Item(foodItem(FoodValues.SEA_TERROR_SUSHI)));
    public static final RegistryObject<Item> JELLYFISH_SKIN = registerWithTab("jellyfish_skin",
            () -> new ConsumableItem(foodItem(FoodValues.JELLYFISH_SKIN), true, false));


    public static final RegistryObject<Item> OCEANIZED_WITHER_BONE_BROTH = registerWithTab("oceanized_wither_bone_broth",
            () -> new OceanizedWitherBoneBrothItem(bowlFoodItem(FoodValues.OCEANIZED_WITHER_BONE_BROTH)));

    // 刀
    public static final RegistryObject<Item> OCEAN_CHITIN_KNIFE = registerWithTab("ocean_chitin_knife",
            () -> new AquaticKnifeItem(CaerulaMaterials.OCEAN_CHITIN, 0.5F, -2.0F, basicItem()));
    public static final RegistryObject<Item> COMPLEX_CHITIN_KNIFE = registerWithTab("complex_chitin_knife",
            () -> new AquaticKnifeItem(CaerulaMaterials.COMPLEX_CHITIN, 0.5F, -2.0F, basicItem()));
    public static final RegistryObject<Item> TRAILRITE_KNIFE = registerWithTab("trailrite_knife",
            () -> new TrailriteKnifeItem(CaerulaMaterials.TRAILRITE, 0.5F, -2.0F, basicItem()));

    // 特殊掉落物
    public static final RegistryObject<Item> OCEANIZED_WITHER_BONE = registerWithTab("oceanized_wither_bone",
            () -> new Item(basicItem()) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
                    tooltip.add(Component.translatable("item.caerula_delight.oceanized_wither_bone.tooltip").withStyle(ChatFormatting.GRAY));
                }
            });

}
