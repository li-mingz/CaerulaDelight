package com.limingz.caerula_delight.api;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * 用于标记会改变玩家 caerula_arbor 神经损伤（sanity）属性的物品。
 * 返回值为 base sanity 变化量，负数表示减少，正数表示增加。
 */
public interface ISanityAffecting {
    /**
     * 获取该物品被食用/使用时对玩家 sanity 基础值的影响量。
     *
     * @param stack  手持的物品栈
     * @param player 目标玩家
     * @return sanity 变化量，负数为减少
     */
    double getSanityDelta(ItemStack stack, Player player);
}
