package com.limingz.caerula_delight.common.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.limingz.caerula_delight.registry.ModRecipeSerializers;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraftforge.common.crafting.CraftingHelper;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * 一种消耗特定原材料的容器（瓶子）的无序配方，
 * 而不是将其返还到玩家物品栏。
 * <p>
 * 用于瓶子/容器在概念上是最终菜肴一部分的配方。
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ConsumedBottleShapelessRecipe extends ShapelessRecipe {
    public ConsumedBottleShapelessRecipe(ResourceLocation pId, String pGroup, CraftingBookCategory pCategory, ItemStack pResult, NonNullList<Ingredient> pIngredients) {
        super(pId, pGroup, pCategory, pResult, pIngredients);
    }

    /**
     * 确定合成后合成网格中剩余的物品。
     * 此实现防止特定原材料的容器物品（瓶子）被返还。
     *
     * @param pContainer 合成容器
     * @return 剩余物品列表
     */
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer pContainer) {
        NonNullList<ItemStack> result = NonNullList.withSize(pContainer.getContainerSize(), ItemStack.EMPTY);
        boolean consumedOneBottle = false;

        for (int i = 0; i < result.size(); ++i) {
            ItemStack item = pContainer.getItem(i);
            if (item.hasCraftingRemainingItem()) {
                // 检查物品是否返还玻璃瓶（例如药水、果汁、海恐之血）
                // 如果是，并且我们还没有消耗瓶子，则消耗这一个（返回 EMPTY 而不是玻璃瓶）
                if (item.getCraftingRemainingItem().getItem() == Items.GLASS_BOTTLE) {
                    if (!consumedOneBottle) {
                        consumedOneBottle = true;
                        result.set(i, ItemStack.EMPTY); // 消耗瓶子（不返还）
                        continue;
                    }
                }
                result.set(i, item.getCraftingRemainingItem());
            }
        }
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.CONSUMED_BOTTLE_SHAPELESS.get();
    }

    public static class Serializer implements RecipeSerializer<ConsumedBottleShapelessRecipe> {
        @Override
        public ConsumedBottleShapelessRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            String s = GsonHelper.getAsString(pJson, "group", "");
            CraftingBookCategory category = CraftingBookCategory.CODEC.byName(GsonHelper.getAsString(pJson, "category", null), CraftingBookCategory.MISC);

            NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(pJson, "ingredients"));

            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nonnulllist.size() > 9) {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            } else {
                ItemStack itemstack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(pJson, "result"), true);
                return new ConsumedBottleShapelessRecipe(pRecipeId, s, category, itemstack, nonnulllist);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray pIngredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();
            for (int i = 0; i < pIngredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(pIngredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }
            return nonnulllist;
        }

        @Override
        public ConsumedBottleShapelessRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            CraftingBookCategory category = pBuffer.readEnum(CraftingBookCategory.class);
            int i = pBuffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack itemstack = pBuffer.readItem();
            return new ConsumedBottleShapelessRecipe(pRecipeId, s, category, itemstack, nonnulllist);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, ConsumedBottleShapelessRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.getGroup());
            pBuffer.writeEnum(pRecipe.category());
            pBuffer.writeVarInt(pRecipe.getIngredients().size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItem(pRecipe.getResultItem(RegistryAccess.EMPTY));
        }
    }
}
