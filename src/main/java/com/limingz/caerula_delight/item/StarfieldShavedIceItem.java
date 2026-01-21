package com.limingz.caerula_delight.item;

import com.limingz.caerula_delight.registry.ModBlock;
import com.limingz.caerula_delight.util.LightUtils;
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

public class StarfieldShavedIceItem extends ConsumableItem
{
    public StarfieldShavedIceItem(Properties properties) {
        super(properties, true, false);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        LightUtils.addLight(consumer, 15d);
    }


}
