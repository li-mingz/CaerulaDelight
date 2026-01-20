package com.limingz.caerula_delight.data;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.loot.AddItemModifier;
import com.limingz.caerula_delight.registry.RegisterItems;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.mcreator.caerulaarbor.init.CaerulaArborModEntities;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.tag.ForgeTags;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, CaerulaDelightMod.MODID);
    }

    @Override
    protected void start() {
        // 恐鱼排
        var seaTerrorCutletItem = RegisterItems.SEA_TERROR_CUTLET.get();

        RegistryObject<EntityType<?>>[] entities = new RegistryObject[]{
                CaerulaArborModEntities.PREDATOR_ABYSSAL,
                CaerulaArborModEntities.SPLASHER_ABYSSAL,
                CaerulaArborModEntities.RUN_FISH,
                CaerulaArborModEntities.SLIDER_FISH,
                CaerulaArborModEntities.SHOOTER_FISH,
                CaerulaArborModEntities.FLY_FISH,
                CaerulaArborModEntities.FLOATER_PROKARYOTE,
                CaerulaArborModEntities.ACCUMULATOR_PROKARYOTE,
                CaerulaArborModEntities.FEEDER_PROKARYOTE,
                CaerulaArborModEntities.DEPOSITER_PROKARYOTE,
                CaerulaArborModEntities.BASELAYER_ABYSSAL,
                CaerulaArborModEntities.GUIDE_ABYSSAL,
                CaerulaArborModEntities.UMBRELLA_ABYSSAL,
                CaerulaArborModEntities.CRACKER_ABYSSAL,
                CaerulaArborModEntities.REAPER_FISH,
                CaerulaArborModEntities.PUNCTURE_FISH,
                CaerulaArborModEntities.PREGNANT_FISH,
                CaerulaArborModEntities.FLEE_FISH,
                CaerulaArborModEntities.APOSTLE_PROKARYOTE,
                CaerulaArborModEntities.NUCLEIC_MALEFICENT
        };

        for (RegistryObject<EntityType<?>> entity : entities) {
            add("sea_terror_cutlet_from_" + entity.getId().getPath(), new AddItemModifier(
                    new LootItemCondition[]{
                            LootItemEntityPropertyCondition.hasProperties(
                                    LootContext.EntityTarget.THIS,
                                    EntityPredicate.Builder.entity().of(entity.get())
                            ).build(),
                            LootItemRandomChanceCondition.randomChance(0.6f).build()
                    },
                    seaTerrorCutletItem
            ));
        }
        // 海嗣胶冻
        RegistryObject<EntityType<?>>[] prokaryotes = new RegistryObject[]{
                CaerulaArborModEntities.FLOATER_PROKARYOTE,
                CaerulaArborModEntities.ACCUMULATOR_PROKARYOTE,
                CaerulaArborModEntities.FEEDER_PROKARYOTE,
                CaerulaArborModEntities.DEPOSITER_PROKARYOTE,
                CaerulaArborModEntities.APOSTLE_PROKARYOTE,
                CaerulaArborModEntities.NUCLEIC_MALEFICENT
        };

        for (RegistryObject<EntityType<?>> entity : prokaryotes) {
            add("sea_terror_jelly_from_" + entity.getId().getPath(), new AddItemModifier(
                    new LootItemCondition[]{
                            LootItemEntityPropertyCondition.hasProperties(
                                    LootContext.EntityTarget.KILLER,
                                    EntityPredicate.Builder.entity().equipment(
                                            EntityEquipmentPredicate.Builder.equipment().mainhand(
                                                    ItemPredicate.Builder.item().of(ForgeTags.TOOLS_KNIVES).build()
                                            ).build()
                                    )
                            ).build(),
                            LootItemEntityPropertyCondition.hasProperties(
                                    LootContext.EntityTarget.THIS,
                                    EntityPredicate.Builder.entity().of(entity.get())
                            ).build()
                    },
                    RegisterItems.SEA_TERROR_JELLY.get(),
                    1,
                    2
            ));
        }

        // 寂海凋零残骨
        add("oceanized_wither_bone_from_oceanized_wither", new AddItemModifier(
                new LootItemCondition[]{
                        LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(CaerulaArborModEntities.OCEANIZED_WITHERIA.get())
                        ).build()
                },
                RegisterItems.OCEANIZED_WITHER_BONE.get()
        ));

        // 水母伞部
        add("jellyfish_skin_from_izumik_offspring", new AddItemModifier(
                new LootItemCondition[]{
                        LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(CaerulaArborModEntities.IZUMIK_OFFSPRING.get())
                        ).build(),
                        LootItemRandomChanceCondition.randomChance(0.2f).build()
                },
                RegisterItems.JELLYFISH_SKIN.get()
        ));
        add("jellyfish_skin_from_izumik", new AddItemModifier(
                new LootItemCondition[]{
                        LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.KILLER,
                                EntityPredicate.Builder.entity().equipment(
                                        EntityEquipmentPredicate.Builder.equipment().mainhand(
                                                ItemPredicate.Builder.item().of(ForgeTags.TOOLS_KNIVES).build()
                                        ).build()
                                )
                        ).build(),
                        LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(CaerulaArborModEntities.IZUMIK.get())
                        ).build()
                },
                RegisterItems.JELLYFISH_SKIN.get()
        ));

    }
}