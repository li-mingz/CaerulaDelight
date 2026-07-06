package com.limingz.caerula_delight.util;

import com.limingz.caerula_delight.registry.RegisterItems;
import net.mcreator.caerulaarbor.init.CaerulaArborModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 集中记录“食用后会改变 Caerula Arbor 神经损伤（sanity）”的食物。
 * 负值为扣除，正值为恢复。
 *
 * <p>数值来自 Caerula Arbor 本体</p>
 */
public final class SanityFoodRegistry {

    private static final Map<Item, Double> DELTAS = new IdentityHashMap<>();

    private SanityFoodRegistry() {
    }

    /**
     * 在注册表事件完成后调用，构建查找表。
     */
    public static void initialize() {
        DELTAS.clear();
        registerDefaults();
    }

    private static void registerDefaults() {
        // Caerula Delight
        put(RegisterItems.SEA_TERROR_BLOOD, -1001.0);
        put(RegisterItems.OCEANIZED_WITHER_BONE_BROTH, -1001.0);

        // Caerula Arbor — 降低 sanity 的食物
        put(CaerulaArborModItems.CELL_CLUSTER, -225.0);
        put(CaerulaArborModItems.BROKEN_CELL_CLUSTER, -75.0);
        put(CaerulaArborModItems.OCEAN_CAVAIR, -325.0);
        put(CaerulaArborModItems.OCEAN_EYE, -325.0);
        put(CaerulaArborModItems.ELITE_CAVAIR, -45.0);
        put(CaerulaArborModItems.CANNED_PEDUNCLE, -120.0);
        // 罐装精制腕足：立即 -40，并附加 200  tick 的 DEDUCT_ONE_SANITY（每秒扣 1），实际总扣 240。
        put(CaerulaArborModItems.CANNED_PEDUNCLE_ELITE, -240.0);
        put(CaerulaArborModItems.OCEAN_PEDUNCLE, -60.0);
        put(CaerulaArborModItems.RELIC_CURSED_GLOWBODY, -500.0);
        put(CaerulaArborModItems.TRAIL_GOLDEN_APPLE, -80.0);
        // 染痕的附魔金苹果：立即扣 120，但会获得 2400 tick 的 SANITY_IMMUNE，
        // 该效果每 tick 回 5 点 sanity（上限 1000），实际效果为回满。
        put(CaerulaArborModItems.ENCHANTED_TRAIL_GOLDEN_APPLE, 1000.0);
        put(CaerulaArborModItems.NETHERSEA_BISCUIT, -50.0);
        put(CaerulaArborModItems.NETHERSEA_COFFEE, -45.0);
        // 落溟强效咖啡：首次 -75，延迟后若效果仍在再 -125，预览直接显示全部潜在损失。
        put(CaerulaArborModItems.NETHERSEA_STIMUTANT, -200.0);
        put(CaerulaArborModItems.TRAIL_APPLE, -75.0);
        put(CaerulaArborModItems.TRAIL_CAKE_PIECE, -150.0);
        put(CaerulaArborModItems.FAKE_EGG, -160.0);
        put(CaerulaArborModItems.SEA_TRAIL_MOR, -160.0);

        // Caerula Arbor — 恢复 sanity 的食物
        put(CaerulaArborModItems.FRUIT_JELLY, 150.0);
        put(CaerulaArborModItems.COMPLEX_CHITIN_APPLE, 100.0);
        put(CaerulaArborModItems.COLOURFUL_APPLE_JUICE, 80.0);
        put(CaerulaArborModItems.CARAMEL_CAKE_PIECE, 125.0);
        put(CaerulaArborModItems.CARAMEL_RICE_DUMPLING, 50.0);
        put(CaerulaArborModItems.CARAMEL_BISCUIT, 25.0);
    }

    private static void put(RegistryObject<Item> item, double delta) {
        DELTAS.put(item.get(), delta);
    }

    /**
     * 获取物品对 sanity 的基础变化量。
     *
     * @param stack 物品栈
     * @return 变化量（负数为扣，正数为回），null 表示无影响
     */
    @Nullable
    public static Double getDelta(ItemStack stack) {
        return DELTAS.get(stack.getItem());
    }
}
