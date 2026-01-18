package com.limingz.caerula_delight.block;

import com.limingz.caerula_delight.block.entity.SimpleFeastBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SimpleGeckoItemFeastBlock extends SimpleItemFeastBlock implements EntityBlock {

    private final Supplier<BlockEntityType<? extends SimpleFeastBlockEntity>> blockEntityType;

    public SimpleGeckoItemFeastBlock(Properties properties, Supplier<Item> foodItem, VoxelShape shape, Supplier<BlockEntityType<? extends SimpleFeastBlockEntity>> blockEntityType) {
        super(properties, foodItem, shape);
        this.blockEntityType = blockEntityType;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SimpleFeastBlockEntity(this.blockEntityType.get(), pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
