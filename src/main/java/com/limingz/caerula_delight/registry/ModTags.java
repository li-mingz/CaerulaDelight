package com.limingz.caerula_delight.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class ModTags {
    public static final TagKey<EntityType<?>> OCEAN_OFFSPRING = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor", "oceanoffspring"));
}

