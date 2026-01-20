package com.limingz.caerula_delight.data;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.registry.RegisterItems;
import net.mcreator.caerulaarbor.init.CaerulaArborModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;

import java.util.function.Consumer;

public class CraftingRecipes
{
    /**
     * 主注册方法。DataGen 运行时会调用此方法。
     * 它负责调用下面分类好的各个私有方法来生成所有配方。
     */
    public static void register(Consumer<FinishedRecipe> consumer) {
        recipesBlocks(consumer);              // 模组方块（如炉灶、橱柜）的配方
        recipesTools(consumer);               // 工具（刀具）配方
        recipesFoodstuffs(consumer);          // 食材（面团、果汁等中间产物）
        recipesFoodBlocks(consumer);          // 可放置的食物方块（派、蛋糕）
        recipesCraftedMeals(consumer);        // 工作台合成的简易料理（三明治、炖菜）

        // 特殊配方：使用自定义序列化器
        // 这里的 SpecialRecipeBuilder 用于生成那些代码逻辑比较特殊的配方（非标准的有序/无序合成）
//        SpecialRecipeBuilder.special(ModRecipeSerializers.FOOD_SERVING.get()).save(consumer, FarmersDelight.MODID + ":food_serving");
//        SpecialRecipeBuilder.special(ModRecipeSerializers.DOUGH.get()).save(consumer, FarmersDelight.MODID + ":wheat_dough_from_water");
    }

    private static void recipesBlocks(Consumer<FinishedRecipe> consumer) {
    }

    private static void recipesTools(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RegisterItems.OCEAN_CHITIN_KNIFE.get())
                .pattern("m")
                .pattern("s")
                .define('m', CaerulaArborModItems.CHITIN_INGOT.get())
                .define('s', CaerulaArborModItems.CUTIN_STICK.get())
                .unlockedBy("has_cutin_stick", InventoryChangeTrigger.TriggerInstance.hasItems(CaerulaArborModItems.CUTIN_STICK.get()))
                .save(consumer);
        // 锻造台配方
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(CaerulaArborModItems.OCEAN_TRIM_TEMPLATE.get()),
                        Ingredient.of(RegisterItems.OCEAN_CHITIN_KNIFE.get()),
                        Ingredient.of(CaerulaArborModItems.COMPLEX_CHITIN.get()),
                        RecipeCategory.COMBAT,
                        RegisterItems.COMPLEX_CHITIN_KNIFE.get()
                )
                .unlocks("has_complex_chitin", InventoryChangeTrigger.TriggerInstance.hasItems(CaerulaArborModItems.COMPLEX_CHITIN.get()))
                .save(consumer, CaerulaDelightMod.MODID + ":complex_chitin_knife_smithing");
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(CaerulaArborModItems.FLAMARINE_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(ModItems.NETHERITE_KNIFE.get()),
                        Ingredient.of(CaerulaArborModItems.TRAILRITE.get()),
                        RecipeCategory.COMBAT,
                        RegisterItems.TRAILRITE_KNIFE.get()
                )
                .unlocks("has_trailrite", InventoryChangeTrigger.TriggerInstance.hasItems(CaerulaArborModItems.TRAILRITE.get()))
                .save(consumer, CaerulaDelightMod.MODID + ":trailrite_knife_smithing");
    }


    private static void recipesFoodstuffs(Consumer<FinishedRecipe> consumer) {

//        // 奶桶 + 4个玻璃瓶 -> 4个奶瓶
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_BOTTLE.get(), 4)
//                .requires(Items.MILK_BUCKET)
//                .requires(Items.GLASS_BOTTLE)
//                .requires(Items.GLASS_BOTTLE)
//                .requires(Items.GLASS_BOTTLE)
//                .requires(Items.GLASS_BOTTLE)
//                .unlockedBy("has_milk_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MILK_BUCKET))
//                .save(consumer);
    }

    private static void recipesFoodBlocks(Consumer<FinishedRecipe> consumer) {
//        // 苹果派合成 (方块)
//        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.APPLE_PIE.get(), 1)
//                .pattern("###")
//                .pattern("aaa")
//                .pattern("xOx")
//                .define('#', Items.WHEAT)
//                .define('a', Items.APPLE)
//                .define('x', Items.SUGAR)
//                .define('O', ModItems.PIE_CRUST.get()) // 需要派皮
//                .unlockedBy("has_pie_crust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PIE_CRUST.get()))
//                .group("fd_apple_pie")
//                .save(consumer);
    }

    private static void recipesCraftedMeals(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, RegisterItems.SEA_TERROR_BLOOD.get())
                .requires(RegisterItems.SEA_TERROR_CUTLET.get())
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_sea_terror_cutlet", InventoryChangeTrigger.TriggerInstance.hasItems(RegisterItems.SEA_TERROR_CUTLET.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, RegisterItems.GLOW_SEA_PUDDING.get(), 1)
                .pattern(" a ")
                .pattern("bcb")
                .pattern("ddd")
                .define('a', Items.GLOW_BERRIES)
                .define('b', RegisterItems.SEA_TERROR_JELLY.get())
                .define('c', ForgeTags.MILK)
                .define('d', CaerulaArborModItems.FAKE_EGG.get())
                .unlockedBy("has_sea_terror_jelly", InventoryChangeTrigger.TriggerInstance.hasItems(RegisterItems.SEA_TERROR_JELLY.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, RegisterItems.SEA_TERROR_SASHIMI.get())
                .requires(Items.BOWL)
                .requires(Ingredient.of(RegisterItems.SEA_TERROR_CUTLET.get(), RegisterItems.SEA_TERROR_CUTLET_STRIPS.get()))
                .requires(Ingredient.of(RegisterItems.SEA_TERROR_CUTLET.get(), RegisterItems.SEA_TERROR_CUTLET_STRIPS.get()))
                .requires(Ingredient.of(RegisterItems.SEA_TERROR_CUTLET.get(), RegisterItems.SEA_TERROR_CUTLET_STRIPS.get()))
                .requires(CaerulaArborModItems.FAKE_EGG.get())
                .requires(CaerulaArborModItems.COLLECTOR_MEAT.get())
                .requires(CaerulaArborModItems.COLLECTOR_MEAT.get())
                .unlockedBy("has_collector_meat", InventoryChangeTrigger.TriggerInstance.hasItems(CaerulaArborModItems.COLLECTOR_MEAT.get()))
                .save(consumer);
    }
}