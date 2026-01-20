package com.limingz.caerula_delight.integration.jei;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.registry.RegisterItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.mcreator.caerulaarbor.init.CaerulaArborModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.utility.TextUtils;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    // 定义插件的唯一 ID
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(CaerulaDelightMod.MODID, "jei_plugin");
    }

    // 注册功能
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(
                new ItemStack(RegisterItems.SEA_TERROR_JELLY.get()),
                VanillaTypes.ITEM_STACK,
                Component.translatable("jei.info.sea_terror_jelly") // 对应语言文件中的 key
        );
        registration.addIngredientInfo(
                new ItemStack(CaerulaArborModItems.COLLECTOR_MEAT.get()),
                VanillaTypes.ITEM_STACK,
                Component.translatable("jei.info.collector_meat")
        );
        registration.addIngredientInfo(
                new ItemStack(RegisterItems.JELLYFISH_SKIN.get()),
                VanillaTypes.ITEM_STACK,
                Component.translatable("jei.info.jellyfish_skin")
        );

        registration.addIngredientInfo(new ItemStack(RegisterItems.OCEAN_CHITIN_KNIFE.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.knife"));
        registration.addIngredientInfo(new ItemStack(RegisterItems.COMPLEX_CHITIN_KNIFE.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.knife"));
        registration.addIngredientInfo(new ItemStack(RegisterItems.TRAILRITE_KNIFE.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.knife"));

    }
}