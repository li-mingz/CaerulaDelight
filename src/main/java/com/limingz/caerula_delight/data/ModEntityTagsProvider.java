package com.limingz.caerula_delight.data;

import com.limingz.caerula_delight.CaerulaDelightMod;
import net.mcreator.caerulaarbor.init.CaerulaArborModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagsProvider extends EntityTypeTagsProvider {
    public ModEntityTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CaerulaDelightMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // 定义标签
        TagKey<EntityType<?>> STRAW_DROPPERS = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(CaerulaDelightMod.MODID, "sea_terror_cutlet_droppers"));

        // 添加生物
        tag(STRAW_DROPPERS)
                .add(CaerulaArborModEntities.PREDATOR_ABYSSAL.get())
                .add(CaerulaArborModEntities.SPLASHER_ABYSSAL.get())
                .add(CaerulaArborModEntities.RUN_FISH.get())
                .add(CaerulaArborModEntities.SLIDER_FISH.get())
                .add(CaerulaArborModEntities.SHOOTER_FISH.get())
                .add(CaerulaArborModEntities.FLY_FISH.get())
                .add(CaerulaArborModEntities.FLOATER_PROKARYOTE.get())
                .add(CaerulaArborModEntities.ACCUMULATOR_PROKARYOTE.get())
                .add(CaerulaArborModEntities.FEEDER_PROKARYOTE.get())
                .add(CaerulaArborModEntities.DEPOSITER_PROKARYOTE.get());
    }
}