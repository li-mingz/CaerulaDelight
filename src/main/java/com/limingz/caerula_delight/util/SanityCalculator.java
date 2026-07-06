package com.limingz.caerula_delight.util;

import com.limingz.caerula_delight.registry.ModAttributes;
import net.mcreator.caerulaarbor.entity.NetherseaBoatEntity;
import net.mcreator.caerulaarbor.init.CaerulaArborModAttributes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;

import java.util.List;

/**
 * 在客户端复刻 {@code DeductPlayerSanityProcedure} 的结算逻辑，用于 HUD 预览。
 * 预览仅考虑属性修饰，不考虑免疫效果/游戏模式。
 */
public final class SanityCalculator {

    private SanityCalculator() {
    }

    public static final double MAX_SANITY = 1000.0;

    /**
     * 获取玩家当前 sanity 基础值（base value），与原 bar 绘制保持一致。
     */
    public static double getCurrentSanity(Player player) {
        Attribute sanity = (Attribute) CaerulaArborModAttributes.SANITY.get();
        if (!player.getAttributes().hasAttribute(sanity)) {
            return MAX_SANITY;
        }
        return player.getAttributeBaseValue(sanity);
    }

    /**
     * 计算原始减少量经过属性修饰后的实际减少量。
     *
     * @param player       目标玩家
     * @param rawDeduction 原始减少量（正值）
     * @return 实际扣除量
     */
    public static double computeActualDeduction(Player player, double rawDeduction) {
        if (rawDeduction <= 0.0) {
            return 0.0;
        }

        double vulnerability = 0.0;
        if (player.getAttributes().hasAttribute(ModAttributes.SANITY_VULNERABILITY.get())) {
            vulnerability = player.getAttributeValue(ModAttributes.SANITY_VULNERABILITY.get());
        }
        double modifiedNum = rawDeduction * (1.0 + vulnerability);

        double resistance = 0.0;
        if (player.getAttributes().hasAttribute((Attribute) CaerulaArborModAttributes.SANITY_RESISTANCE.get())) {
            resistance = player.getAttributeValue((Attribute) CaerulaArborModAttributes.SANITY_RESISTANCE.get());
        }
        double resistanceFactor = 0.01 * (100.0 - resistance);

        double modifier = 1.0;
        if (player.getAttributes().hasAttribute((Attribute) CaerulaArborModAttributes.SANITY_MODIFIER.get())) {
            modifier = player.getAttributeValue((Attribute) CaerulaArborModAttributes.SANITY_MODIFIER.get());
        }

        double deduction = modifiedNum * modifier * resistanceFactor;

        if (player.getVehicle() instanceof NetherseaBoatEntity) {
            deduction *= 0.5;
        }

        return deduction;
    }

    /**
     * 按顺序应用多个 sanity 效果，预测最终 sanity 基础值。
     *
     * @param player 目标玩家
     * @param effects 阶段效果列表
     * @return 结算后的 sanity 值，范围 [-1, 1000]
     */
    public static double predictSanityAfterEffects(Player player, List<SanityEffect> effects) {
        double sanity = getCurrentSanity(player);
        for (SanityEffect effect : effects) {
            sanity = applyRawDelta(player, sanity, effect.delta());
        }
        return Mth.clamp(sanity, -1.0, MAX_SANITY);
    }

    /**
     * 预测使用会改变 sanity 的物品后的最终 sanity 基础值。
     *
     * @param player 目标玩家
     * @param delta  基础变化量（负数为扣，正数为回）
     * @return 结算后的 sanity 值，范围 [-1, 1000]
     */
    public static double predictSanityAfterDelta(Player player, double delta) {
        return applyRawDelta(player, getCurrentSanity(player), delta);
    }

    /**
     * 在指定当前 sanity 下，应用一个基础变化量。
     *
     * @param player       目标玩家
     * @param currentSanity 当前 sanity 值
     * @param delta        基础变化量（负数为扣，正数为回）
     * @return 应用后的 sanity 值，范围 [-1, 1000]
     */
    public static double applyRawDelta(Player player, double currentSanity, double delta) {
        if (delta >= 0.0) {
            return Math.min(currentSanity + delta, MAX_SANITY);
        }
        double actual = computeActualDeduction(player, -delta);
        return Mth.clamp(currentSanity - actual, -1.0, MAX_SANITY);
    }

    /**
     * 预测使用减少 sanity 物品后的最终 sanity 基础值。
     *
     * @param player       目标玩家
     * @param rawDeduction 原始减少量（正值）
     * @return 结算后的 sanity 值，范围 [-1, 1000]
     */
    public static double predictSanityAfterDeduction(Player player, double rawDeduction) {
        double current = getCurrentSanity(player);
        double actual = computeActualDeduction(player, rawDeduction);
        return Mth.clamp(current - actual, -1.0, MAX_SANITY);
    }
}
