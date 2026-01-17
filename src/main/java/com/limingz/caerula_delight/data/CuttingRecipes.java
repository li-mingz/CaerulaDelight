package com.limingz.caerula_delight.data;

import com.limingz.caerula_delight.registry.RegisterItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Consumer;

/**
 * 砧板配方生成类
 */
public class CuttingRecipes
{
    /**
     * 注册所有砧板配方的入口方法
     * @param consumer 配方消费者，用于接收生成的配方
     */
    public static void register(Consumer<FinishedRecipe> consumer) {
        // --- 刀具配方 (Knife) ---
        // 用于切割食材、分解动物掉落物、处理花朵等
        cuttingAnimalItems(consumer);  // 肉类/鱼类处理
        cuttingVegetables(consumer);   // 蔬菜/瓜果处理
        cuttingFoods(consumer);        // 熟食/面点处理
    }

    /**
     * 动物类物品切割配方
     * 例如：牛肉 -> 牛肉馅，整鸡 -> 鸡肉切块 + 骨粉
     */
    private static void cuttingAnimalItems(Consumer<FinishedRecipe> consumer) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(RegisterItems.SEA_TERROR_CUTLET.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), RegisterItems.SEA_TERROR_CUTLET_STRIPS.get(), 2)
                .build(consumer);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(RegisterItems.COOKED_SEA_TERROR_CUTLET.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), RegisterItems.COOKED_SEA_TERROR_CUTLET_STRIPS.get(), 2)
                .build(consumer);

//        // 火腿 -> 猪肉 + 骨头
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.HAM.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.PORKCHOP, 2)
//                .addResult(Items.BONE)
//                .build(consumer);
    }

    /**
     * 蔬菜与瓜果切割配方
     */
    private static void cuttingVegetables(Consumer<FinishedRecipe> consumer) {
//        // 卷心菜 -> 卷心菜叶
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CABBAGE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.CABBAGE_LEAF.get(), 2)
//                .build(consumer);
//        // 稻穗 -> 大米 + 稻草
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.RICE_PANICLE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.RICE.get(), 1)
//                .addResult(ModItems.STRAW.get())
//                .build(consumer);
//        // 西瓜 -> 9个西瓜片
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.MELON), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.MELON_SLICE, 9)
//                .build(consumer);
//        // 南瓜 -> 4个南瓜片
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.PUMPKIN), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.PUMPKIN_SLICE.get(), 4)
//                .build(consumer);
//        // 蘑菇菌落 -> 5个蘑菇
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.BROWN_MUSHROOM_COLONY.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.BROWN_MUSHROOM, 5)
//                .build(consumer);
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.RED_MUSHROOM_COLONY.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.RED_MUSHROOM, 5)
//                .build(consumer);
    }

    /**
     * 熟食、糕点和面团处理配方
     */
    private static void cuttingFoods(Consumer<FinishedRecipe> consumer) {
//        // 面团 -> 生面条
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ForgeTags.DOUGH), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.RAW_PASTA.get(), 1)
//                .build(consumer, new ResourceLocation(FarmersDelight.MODID, "cutting/tag_dough"));
//        // 海带卷 -> 海带卷切片
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.KELP_ROLL.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.KELP_ROLL_SLICE.get(), 3)
//                .build(consumer);
//        // 蛋糕 -> 7个蛋糕切片
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CAKE), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.CAKE_SLICE.get(), 7)
//                .build(consumer);
//        // 苹果派 -> 4个苹果派切片
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.APPLE_PIE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.APPLE_PIE_SLICE.get(), 4)
//                .build(consumer);
//        // 甜浆果芝士蛋糕 -> 4个切片
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.SWEET_BERRY_CHEESECAKE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get(), 4)
//                .build(consumer);
//        // 巧克力派 -> 4个切片
//        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.CHOCOLATE_PIE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.CHOCOLATE_PIE_SLICE.get(), 4)
//                .build(consumer);
    }

}