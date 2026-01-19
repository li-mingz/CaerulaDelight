package com.limingz.caerula_delight.data;

import com.limingz.caerula_delight.registry.RegisterItems;
import net.mcreator.caerulaarbor.init.CaerulaArborModBlocks;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import java.util.function.Consumer;

/**
 * 烹饪食谱生成类
 * 负责定义所有在烹饪锅中制作的食谱数据。
 */
public class CookingRecipes
{
    // 定义烹饪时间常量 (单位: tick, 20 ticks = 1秒)
    public static final int FAST_COOKING = 100;      // 快速烹饪: 5 秒 (如番茄酱)
    public static final int NORMAL_COOKING = 200;    // 正常烹饪: 10 秒 (大多数炖菜)
    public static final int SLOW_COOKING = 400;      // 慢速烹饪: 20 秒 (如酿南瓜)

    // 定义烹饪获得的经验值常量
    public static final float SMALL_EXP = 0.35F;     // 少量经验
    public static final float MEDIUM_EXP = 1.0F;     // 中等经验
    public static final float LARGE_EXP = 2.0F;      // 大量经验

    /**
     * 主注册方法
     * 类似于 Minecraft 数据生成器中的 registerRecipes 方法。
     * @param consumer 接受生成好的食谱对象的消费者
     */
    public static void register(Consumer<FinishedRecipe> consumer) {
        cookMiscellaneous(consumer);   // 注册杂项食谱 (饮料、酱料、狗粮等)
        cookMeals(consumer);           // 注册模组新增的主食 (炖菜、面条、米饭等)
    }

    /**
     * 注册杂项食谱
     * 包括饮料、调味品和特殊物品。
     */
    private static void cookMiscellaneous(Consumer<FinishedRecipe> consumer) {
//        // 热可可 (Hot Cocoa)
//        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.HOT_COCOA.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
//                .addIngredient(ForgeTags.MILK)       // 原料: 牛奶 (Tag)
//                .addIngredient(Items.SUGAR)          // 原料: 糖
//                .addIngredient(Items.COCOA_BEANS)    // 原料: 可可豆
//                .addIngredient(Items.COCOA_BEANS)    // 原料: 可可豆 (需要两份)
//                // 解锁条件: 拥有可可豆、奶桶或牛奶瓶时解锁
//                .unlockedByAnyIngredient(Items.COCOA_BEANS, Items.MILK_BUCKET, ModItems.MILK_BOTTLE.get())
//                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS) // 设置在食谱书中的分类: 饮料
//                .build(consumer);

        CookingPotRecipeBuilder.cookingPotRecipe(RegisterItems.ROTTEN_FOAM.get(), 1, NORMAL_COOKING, MEDIUM_EXP, RegisterItems.SEA_TERROR_BLOOD.get())
//                .addIngredient(RegisterItems.SEA_TERROR_BLOOD.get())
                .addIngredient(CaerulaArborModBlocks.DEEP_SEAGRASS.get())
                .addIngredient(Items.GLOW_INK_SAC)
                .addIngredient(Items.SEA_PICKLE)
                // 解锁条件
                .unlockedByAnyIngredient(CaerulaArborModBlocks.DEEP_SEAGRASS.get(), RegisterItems.SEA_TERROR_BLOOD.get(), Items.GLOW_INK_SAC, Items.SEA_PICKLE)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS) // 设置在食谱书中的分类: 饮料
                .build(consumer);

    }

    /**
     * 注册模组主食
     */
    private static void cookMeals(Consumer<FinishedRecipe> consumer) {
        // 寂海返魂汤
        CookingPotRecipeBuilder.cookingPotRecipe(RegisterItems.OCEANIZED_WITHER_BONE_BROTH.get(), 1, NORMAL_COOKING, LARGE_EXP, Items.BOWL)
                .addIngredient(RegisterItems.OCEANIZED_WITHER_BONE.get())
                .addIngredient(CaerulaArborModBlocks.NETHERSEA_SOUL_SAND.get())
                .addIngredient(CaerulaArborModBlocks.NETHERSEA_SOUL_SAND.get())
                .addIngredient(CaerulaArborModBlocks.NETHERSEA_SOUL_SAND.get())
                .addIngredient(CaerulaArborModBlocks.TRAIL_MUSHROOM.get())
                .addIngredient(Items.WITHER_SKELETON_SKULL)
                .unlockedByAnyIngredient(RegisterItems.OCEANIZED_WITHER_BONE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(consumer);
    }
}