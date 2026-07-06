package com.limingz.caerula_delight.util;

import com.limingz.caerula_delight.registry.RegisterItems;
import net.mcreator.caerulaarbor.init.CaerulaArborModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * 集中记录“食用后会改变 Caerula Arbor 神经损伤（sanity）”的食物。
 *
 * <p>每个食物可以对应一个或多个 {@link SanityEffect}，从而表达
 * 先扣后回、即时 + 延迟/持续等多阶段效果。负值为扣除，正值为恢复。</p>
 *
 * <p>数值来自 Caerula Arbor 本体。</p>
 */
public final class SanityFoodRegistry {

    private static final Map<Item, List<SanityEffect>> EFFECTS = new IdentityHashMap<>();

    private SanityFoodRegistry() {
    }

    /**
     * 在注册表事件完成后调用，构建查找表。
     */
    public static void initialize() {
        EFFECTS.clear();
        registerDefaults();
    }

    private static void registerDefaults() {
        // Caerula Delight
        put(RegisterItems.SEA_TERROR_BLOOD, SanityEffect.immediate(-1001.0));
        put(RegisterItems.OCEANIZED_WITHER_BONE_BROTH, SanityEffect.immediate(-1001.0));

        // Caerula Arbor — 降低 sanity 的食物
        put(CaerulaArborModItems.CELL_CLUSTER, SanityEffect.immediate(-225.0));
        put(CaerulaArborModItems.BROKEN_CELL_CLUSTER, SanityEffect.immediate(-75.0));
        put(CaerulaArborModItems.OCEAN_CAVAIR, SanityEffect.immediate(-325.0));
        put(CaerulaArborModItems.OCEAN_EYE, SanityEffect.immediate(-325.0));
        put(CaerulaArborModItems.ELITE_CAVAIR, SanityEffect.immediate(-45.0));
        put(CaerulaArborModItems.CANNED_PEDUNCLE, SanityEffect.immediate(-120.0));
        // 罐装精制腕足：立即 -40，随后 200 tick 内持续再扣 200，共 -240。
        put(CaerulaArborModItems.CANNED_PEDUNCLE_ELITE,
                SanityEffect.immediate(-40.0),
                SanityEffect.sustained(-200.0, 200));
        put(CaerulaArborModItems.OCEAN_PEDUNCLE, SanityEffect.immediate(-60.0));
        put(CaerulaArborModItems.RELIC_CURSED_GLOWBODY, SanityEffect.immediate(-500.0));
        put(CaerulaArborModItems.TRAIL_GOLDEN_APPLE, SanityEffect.immediate(-80.0));
        // 染痕的附魔金苹果：立即扣 120，随后 SANITY_IMMUNE 持续回满 sanity。
        put(CaerulaArborModItems.ENCHANTED_TRAIL_GOLDEN_APPLE,
                SanityEffect.immediate(-120.0),
                SanityEffect.sustained(1000.0, 2400));
        put(CaerulaArborModItems.NETHERSEA_BISCUIT, SanityEffect.immediate(-50.0));
        put(CaerulaArborModItems.NETHERSEA_COFFEE, SanityEffect.immediate(-45.0));
        // 落溟强效咖啡：首次 -75，延迟后若效果仍在再 -125。
        put(CaerulaArborModItems.NETHERSEA_STIMUTANT,
                SanityEffect.immediate(-75.0),
                SanityEffect.delayed(-125.0, 100));
        put(CaerulaArborModItems.TRAIL_APPLE, SanityEffect.immediate(-75.0));
        put(CaerulaArborModItems.TRAIL_CAKE_PIECE, SanityEffect.immediate(-150.0));
        put(CaerulaArborModItems.FAKE_EGG, SanityEffect.immediate(-160.0));
        put(CaerulaArborModItems.SEA_TRAIL_MOR, SanityEffect.immediate(-160.0));

        // Caerula Arbor — 恢复 sanity 的食物
        put(CaerulaArborModItems.FRUIT_JELLY, SanityEffect.immediate(150.0));
        put(CaerulaArborModItems.COMPLEX_CHITIN_APPLE, SanityEffect.immediate(100.0));
        put(CaerulaArborModItems.COLOURFUL_APPLE_JUICE, SanityEffect.immediate(80.0));
        put(CaerulaArborModItems.CARAMEL_CAKE_PIECE, SanityEffect.immediate(125.0));
        put(CaerulaArborModItems.CARAMEL_RICE_DUMPLING, SanityEffect.immediate(50.0));
        put(CaerulaArborModItems.CARAMEL_BISCUIT, SanityEffect.immediate(25.0));
    }

    private static void put(RegistryObject<Item> item, SanityEffect... effects) {
        EFFECTS.put(item.get(), List.of(effects));
    }

    /**
     * 获取物品对 sanity 的所有阶段效果。
     *
     * @param stack 物品栈
     * @return 效果列表，不会为 null；无影响时返回空列表
     */
    public static List<SanityEffect> getEffects(ItemStack stack) {
        return EFFECTS.getOrDefault(stack.getItem(), List.of());
    }

    /**
     * 获取物品对 sanity 的净变化量（向后兼容）。
     *
     * @param stack 物品栈
     * @return 净变化量（负数为扣，正数为回），null 表示无影响
     */
    @Nullable
    public static Double getDelta(ItemStack stack) {
        List<SanityEffect> effects = EFFECTS.get(stack.getItem());
        if (effects == null || effects.isEmpty()) {
            return null;
        }
        return effects.stream().mapToDouble(SanityEffect::delta).sum();
    }
}
