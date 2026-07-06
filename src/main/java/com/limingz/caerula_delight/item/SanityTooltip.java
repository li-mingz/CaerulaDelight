package com.limingz.caerula_delight.item;

import net.minecraft.world.inventory.tooltip.TooltipComponent;

/**
 * 自定义 tooltip 组件：显示一个 sanity 数值（带 +/- 符号），
 * 并可附加一段后缀文本（如“延迟”）。
 *
 * @param delta  sanity 变化量（正数为恢复，负数为扣除）
 * @param suffix 显示在数值后的后缀，空字符串表示无后缀
 */
public record SanityTooltip(double delta, String suffix) implements TooltipComponent {

    public SanityTooltip(double delta) {
        this(delta, "");
    }
}
