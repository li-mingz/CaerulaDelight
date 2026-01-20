package com.limingz.caerula_delight.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class ModTags {
    public static final TagKey<EntityType<?>> OCEAN_OFFSPRING = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor", "oceanoffspring"));
    public static final TagKey<Item> FISH_FOOD = TagKey.create(Registries.ITEM, new ResourceLocation("caerula_arbor", "fish_food"));
}
