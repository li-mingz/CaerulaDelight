package com.limingz.caerula_delight.data;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.registry.RegisterItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagsProvider.TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, CaerulaDelightMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider){


        this.tag(ModTags.KNIVES)
                .add(RegisterItems.OCEAN_CHITIN_KNIFE.get())
                .add(RegisterItems.COMPLEX_CHITIN_KNIFE.get())
                .add(RegisterItems.TRAILRITE_KNIFE.get());

        this.tag(ForgeTags.TOOLS_KNIVES)
                .add(RegisterItems.OCEAN_CHITIN_KNIFE.get())
                .add(RegisterItems.COMPLEX_CHITIN_KNIFE.get())
                .add(RegisterItems.TRAILRITE_KNIFE.get());
    }
}

