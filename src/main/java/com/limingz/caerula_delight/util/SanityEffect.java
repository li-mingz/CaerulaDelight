package com.limingz.caerula_delight.util;

/**
 * 表示食用某物品后对玩家 sanity 的一个阶段影响。
 *
 * <p>多个 {@code SanityEffect} 可以组合起来描述复杂食物
 * （例如先扣后回、即时扣 + 延迟扣/持续扣等）。</p>
 *
 * @param delta         基础变化量（负数为扣，正数为回）
 * @param delayTicks    延迟生效的 tick 数，0 表示立即生效
 * @param durationTicks 持续生效的 tick 数，0 表示一次性生效
 */
public record SanityEffect(double delta, int delayTicks, int durationTicks) {

    public SanityEffect(double delta) {
        this(delta, 0, 0);
    }

    public SanityEffect(double delta, int delayTicks) {
        this(delta, delayTicks, 0);
    }

    public static SanityEffect immediate(double delta) {
        return new SanityEffect(delta, 0, 0);
    }

    public static SanityEffect delayed(double delta, int delayTicks) {
        return new SanityEffect(delta, delayTicks, 0);
    }

    public static SanityEffect sustained(double delta, int durationTicks) {
        return new SanityEffect(delta, 0, durationTicks);
    }

    public boolean isDelayed() {
        return delayTicks > 0;
    }

    public boolean isSustained() {
        return durationTicks > 0;
    }
}
