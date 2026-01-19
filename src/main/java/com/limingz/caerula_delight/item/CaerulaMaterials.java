package com.limingz.caerula_delight.item;

import net.mcreator.caerulaarbor.init.CaerulaArborModItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class CaerulaMaterials
{
    // 深海几丁质
    public static final Tier OCEAN_CHITIN = new Tier() {
        public int getUses() {
            return 1220;
        }

        public float getSpeed() {
            return 6.0F;
        }

        public float getAttackDamageBonus() {
            return 2.0F;
        }

        public int getLevel() {
            return 3;
        }

        public int getEnchantmentValue() {
            return 11;
        }

        public Ingredient getRepairIngredient() {
            return Ingredient.of(CaerulaArborModItems.OCEAN_CHITIN.get());
        }
    };
    // 复合几丁质
    public static final Tier COMPLEX_CHITIN = new Tier() {
        public int getUses() {
            return 3374;
        }

        public float getSpeed() {
            return 15.0F;
        }

        public float getAttackDamageBonus() {
            return 5.0F;
        }

        public int getLevel() {
            return 3;
        }

        public int getEnchantmentValue() {
            return 18;
        }

        public Ingredient getRepairIngredient() {
            return Ingredient.of(CaerulaArborModItems.COMPLEX_CHITIN.get());
        }
    };
    // 狱海合金
    public static final Tier TRAILRITE = new Tier() {
        public int getUses() {
            return 7999;
        }

        public float getSpeed() {
            return 9.0F;
        }

        public float getAttackDamageBonus() {
            return 10.0F;
        }

        public int getLevel() {
            return 4;
        }

        public int getEnchantmentValue() {
            return 23;
        }

        public Ingredient getRepairIngredient() {
            return Ingredient.of(CaerulaArborModItems.TRAILRITE.get());
        }
    };
}