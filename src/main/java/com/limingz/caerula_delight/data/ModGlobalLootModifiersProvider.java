package com.limingz.caerula_delight.data;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.loot.AddItemModifier;
import com.limingz.caerula_delight.registry.RegisterItems;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, CaerulaDelightMod.MODID);
    }

    @Override
    protected void start() {
        TagKey<EntityType<?>> SEA_TERROR_CUTLET_DROPPERS = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(CaerulaDelightMod.MODID, "sea_terror_cutlet_droppers"));

        var seaTerrorCutletItem = RegisterItems.SEA_TERROR_CUTLET.get();

        add("sea_terror_cutlet_from_sea_terror", new AddItemModifier(
                new LootItemCondition[]{
                        LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(SEA_TERROR_CUTLET_DROPPERS)
                        ).build(),
                        LootItemRandomChanceCondition.randomChance(0.6f).build()
                },
                seaTerrorCutletItem
        ));
    }
}