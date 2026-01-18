package com.limingz.caerula_delight.registry;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.block.SimpleGeckoItemFeastBlock;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CaerulaDelightMod.MODID);

    public static final VoxelShape GLOW_SEA_PUDDING_SHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(4, 1, 4, 12, 10, 12),
            // X-Sides
            Block.box(12, 1, 4, 13, 9, 12),
            Block.box(13, 1, 4, 14, 7, 12),
            Block.box(14, 1, 4, 15, 5, 12),
            Block.box(3, 1, 4, 4, 9, 12),
            Block.box(2, 1, 4, 3, 7, 12),
            Block.box(1, 1, 4, 2, 5, 12),
            // Z-Sides
            Block.box(4, 1, 3, 12, 9, 4),
            Block.box(4, 1, 2, 12, 7, 3),
            Block.box(4, 1, 1, 12, 5, 2),
            Block.box(4, 1, 12, 12, 9, 13),
            Block.box(4, 1, 13, 12, 7, 14),
            Block.box(4, 1, 14, 12, 5, 15),
            // Corners
            Block.box(2, 1, 2, 4, 6, 4),
            Block.box(12, 1, 2, 14, 6, 4),
            Block.box(12, 1, 12, 14, 6, 14),
            Block.box(2, 1, 12, 4, 6, 14),
            // Upper details
            Block.box(12, 5, 3, 13, 8, 4),
            Block.box(12, 5, 12, 13, 8, 13),
            Block.box(3, 5, 12, 4, 8, 13),
            Block.box(3, 5, 3, 4, 8, 4),
            // Top
            Block.box(7, 10, 7, 9, 12, 9)
    );

    public static final RegistryObject<Block> GLOW_SEA_PUDDING_BLOCK = BLOCKS.register("glow_sea_pudding_block",
            () -> new SimpleGeckoItemFeastBlock(
                    BlockBehaviour.Properties.copy(Blocks.CAKE).instabreak().noOcclusion(),  // 沿用蛋糕的方块属性并设置为秒破坏
                    RegisterItems.GLOW_SEA_PUDDING,
                    GLOW_SEA_PUDDING_SHAPE,
                    ModBlockEntities.GLOW_SEA_PUDDING_ENTITY::get
            ));
}
