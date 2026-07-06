package com.limingz.caerula_delight.registry;

import com.limingz.caerula_delight.registry.RegisterItems;
import net.mcreator.caerulaarbor.init.CaerulaArborModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 集中记录“食用后会回复 Caerula Arbor 灯火（player_light）”的食物。
 *
 * <p>正值为回复灯火，负值为降低灯火。数值来自 Caerula Arbor 本体或
 * Caerula Delight 的设定。</p>
 */
public final class LightFoodRegistry {

    private static final Map<Item, Double> LIGHTS = new IdentityHashMap<>();

    private LightFoodRegistry() {
    }

    /**
     * 在注册表事件完成后调用，构建查找表。
     */
    public static void initialize() {
        LIGHTS.clear();
        registerDefaults();
    }

    private static void registerDefaults() {
        // Caerula Arbor — 回复灯火的食物
        put(CaerulaArborModItems.FLUORE_BERRIES, 8.0);
        put(CaerulaArborModItems.RADIANT_BERRIES, 24.0);
        put(CaerulaArborModItems.FLUORE_BERRY_JUICE, 20.0);
        put(CaerulaArborModItems.COLOURFUL_APPLE_JUICE, 12.0);
        put(CaerulaArborModItems.CHOCOLATE_ICECREAM, 9.0);
        put(CaerulaArborModItems.FLUORE_ICECREAM, 12.0);
        put(CaerulaArborModItems.FRUIT_JELLY, 16.0);
        put(CaerulaArborModItems.MIZUKI_BISCUIT, 10.0);
        put(CaerulaArborModItems.NETHERSEA_ICECREAM, 3.0);

        // Caerula Delight — 回复灯火的食物
        put(RegisterItems.GLOW_SEA_PUDDING, 20.0);
        put(RegisterItems.STARFIELD_SHAVED_ICE, 15.0);
    }

    private static void put(RegistryObject<Item> item, double lightDelta) {
        LIGHTS.put(item.get(), lightDelta);
    }

    /**
     * 获取物品对灯火的变化量。
     *
     * @param stack 物品栈
     * @return 变化量（正数为回复，负数为降低），null 表示无影响
     */
    @Nullable
    public static Double getLightDelta(ItemStack stack) {
        return LIGHTS.get(stack.getItem());
    }
}
