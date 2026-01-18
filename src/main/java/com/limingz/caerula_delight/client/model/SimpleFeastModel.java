package com.limingz.caerula_delight.client.model;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.block.entity.SimpleFeastBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.model.GeoModel;

public class SimpleFeastModel extends GeoModel<SimpleFeastBlockEntity> {
    @Override
    public RenderType getRenderType(SimpleFeastBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }

    @Override
    public ResourceLocation getModelResource(SimpleFeastBlockEntity animatable) {
        ResourceLocation registryName = ForgeRegistries.BLOCKS.getKey(animatable.getBlockState().getBlock());
        return new ResourceLocation(registryName.getNamespace(), "geo/" + registryName.getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SimpleFeastBlockEntity animatable) {
        ResourceLocation registryName = ForgeRegistries.BLOCKS.getKey(animatable.getBlockState().getBlock());
        return new ResourceLocation(registryName.getNamespace(), "textures/block/" + registryName.getPath() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(SimpleFeastBlockEntity animatable) {
        ResourceLocation registryName = ForgeRegistries.BLOCKS.getKey(animatable.getBlockState().getBlock());
        return new ResourceLocation(registryName.getNamespace(), "animations/" + registryName.getPath() + ".animation.json");
    }
}
