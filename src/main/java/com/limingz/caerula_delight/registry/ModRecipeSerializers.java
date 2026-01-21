package com.limingz.caerula_delight.registry;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.common.crafting.ConsumedBottleShapelessRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, CaerulaDelightMod.MODID);

    public static final RegistryObject<RecipeSerializer<ConsumedBottleShapelessRecipe>> CONSUMED_BOTTLE_SHAPELESS = RECIPE_SERIALIZERS.register("consumed_bottle_shapeless", ConsumedBottleShapelessRecipe.Serializer::new);
}
