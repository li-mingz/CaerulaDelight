package com.limingz.caerula_delight.registry;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.block.SimpleGeckoItemFeastBlock;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CaerulaDelightMod.MODID);

    public static final RegistryObject<Block> GLOW_SEA_PUDDING_BLOCK = BLOCKS.register("glow_sea_pudding_block",
            () -> new SimpleGeckoItemFeastBlock(
                    BlockBehaviour.Properties.copy(Blocks.CAKE).instabreak().noOcclusion(),  // 沿用蛋糕的方块属性并设置为秒破坏
                    RegisterItems.GLOW_SEA_PUDDING,
                    null,
                    ModBlockEntities.GLOW_SEA_PUDDING_ENTITY::get
            ));
}
