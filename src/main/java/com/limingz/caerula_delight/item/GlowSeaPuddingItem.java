package com.limingz.caerula_delight.item;

import com.limingz.caerula_delight.util.LightUtils;
import com.limingz.caerula_delight.registry.ModBlock;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import vectorwing.farmersdelight.common.item.ConsumableItem;

public class GlowSeaPuddingItem extends ConsumableItem
{
    public GlowSeaPuddingItem(Properties properties) {
        super(properties, true, false);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        // shift+右键 尝试放置方块
        if (context.getPlayer() != null && context.getPlayer().isSecondaryUseActive()) {
            InteractionResult result = this.place(new BlockPlaceContext(context));
            if (result.consumesAction()) {
                return result;
            }
        }
        return super.useOn(context);
    }

    // 模仿 BlockItem 的 place 方法逻辑
    private InteractionResult place(BlockPlaceContext context) {
        // 检查是否可以放置方块
        if (!context.canPlace()) {
            return InteractionResult.FAIL;
        }

        // 获取要放置的方块和方块状态
        Block block = ModBlock.GLOW_SEA_PUDDING_BLOCK.get();
        BlockState blockState = block.getStateForPlacement(context);

        if (blockState == null) {
            return InteractionResult.FAIL;
        }

        // 尝试在世界中放置方块
        if (!this.placeBlock(context, blockState)) {
            return InteractionResult.FAIL;
        }

        Level level = context.getLevel();
        ItemStack itemStack = context.getItemInHand();
        BlockState placedState = level.getBlockState(context.getClickedPos());

        // 如果放置成功，调用 setPlacedBy 进行后续处理（如放置后的逻辑）
        if (placedState.is(blockState.getBlock())) {
            placedState.getBlock().setPlacedBy(level, context.getClickedPos(), placedState, context.getPlayer(), itemStack);
        }

        // 播放方块放置音效
        SoundType soundtype = placedState.getSoundType(level, context.getClickedPos(), context.getPlayer());
        level.playSound(context.getPlayer(), context.getClickedPos(), soundtype.getPlaceSound(), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

        // 触发游戏事件
        level.gameEvent(GameEvent.BLOCK_PLACE, context.getClickedPos(), GameEvent.Context.of(context.getPlayer(), placedState));

        // 如果不是创造模式，消耗一个物品
        if (context.getPlayer() == null || !context.getPlayer().getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private boolean placeBlock(BlockPlaceContext context, BlockState state) {
        return context.getLevel().setBlock(context.getClickedPos(), state, 11);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        LightUtils.addLight(consumer, 20d);
    }


}
