package com.limingz.caerula_delight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.entity.Entity;
import java.util.Collections;
import java.util.List;

import java.util.function.Supplier;


public class SimpleItemFeastBlock extends HorizontalDirectionalBlock {

    private final Supplier<Item> foodItemSupplier;
    private final VoxelShape shape;

    /**
     * @param properties 方块属性
     * @param foodItem   要模拟吃掉的物品
     * @param shape      方块的碰撞箱形状 (传入 null 则默认为满格方块)
     */
    public SimpleItemFeastBlock(Properties properties, Supplier<Item> foodItem, VoxelShape shape) {
        super(properties);
        this.foodItemSupplier = foodItem;
        // 如果没传形状，就默认用类似蛋糕/盘子的大小
        this.shape = shape != null ? shape : Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);

        // 设置默认朝向
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shape;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        Item foodItem = foodItemSupplier.get();
        ItemStack foodStack = new ItemStack(foodItem);

        // 检查玩家是否能吃
        if (player.canEat(foodItem.getFoodProperties() != null && foodItem.getFoodProperties().canAlwaysEat())) {
            if (!level.isClientSide) {
                // 触发物品的所有效果
                foodItem.finishUsingItem(foodStack, level, player);

                // 补充音效
//                level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0f, 1.0f);

                // 吃完销毁
                level.removeBlock(pos, false);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    // 让方块面向玩家
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    // 注册属性
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        Entity entity = builder.getOptionalParameter(LootContextParams.THIS_ENTITY);
        if (entity instanceof Player player && player.getAbilities().instabuild) {
            return Collections.emptyList();
        }
        return Collections.singletonList(new ItemStack(this.foodItemSupplier.get()));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(this.foodItemSupplier.get());
    }
}