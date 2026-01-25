package com.limingz.caerula_delight.item;

import com.limingz.caerula_delight.registry.ModMobEffects;
import net.mcreator.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import java.util.UUID;

import java.util.List;

public class TrailriteFishingRodItem extends FishingRodItem {
    private final Tier tier;
    private static final UUID MAINHAND_LUCK_MODIFIER_UUID = UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca3");
    private static final UUID OFFHAND_LUCK_MODIFIER_UUID = UUID.fromString("d6db11e0-8271-4658-b610-86337a672323");

    public TrailriteFishingRodItem(Item.Properties builder, Tier tier) {
        super(builder.durability(tier.getUses()));
        this.tier = tier;
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        tooltip.add(Component.translatable("item.caerula_delight.trailrite_fishing_rod.tooltip").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return this.tier.getRepairIngredient().test(repair) || super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public int getEnchantmentValue() {
        return this.tier.getEnchantmentValue();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slot, stack));
        if (slot == EquipmentSlot.MAINHAND) {
            builder.put(Attributes.LUCK, new AttributeModifier(MAINHAND_LUCK_MODIFIER_UUID, "trailrite_fishing_rod_luck_modifier", 3.25, AttributeModifier.Operation.ADDITION));
        } else if (slot == EquipmentSlot.OFFHAND) {
            builder.put(Attributes.LUCK, new AttributeModifier(OFFHAND_LUCK_MODIFIER_UUID, "trailrite_fishing_rod_luck_modifier", 3.25, AttributeModifier.Operation.ADDITION));
        }
        return builder.build();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof Player player) {
            boolean isHeld = player.getMainHandItem() == stack || player.getOffhandItem() == stack;
            if (isHeld && player.fishing != null) {
                FishingHook hook = player.fishing;
                Entity hooked = hook.getHookedIn();
                if (hooked instanceof LivingEntity livingHooked) {
                    // 给被钩中的生物施加 5s 损伤脆弱效果
                    livingHooked.addEffect(new MobEffectInstance(ModMobEffects.SANITY_VULNERABILITY.get(), 100, 2, false, false));

                }

                // 浮标周围2米内实体造成精神损伤
                // 基础伤害每 tick 5，饵钓每级增加10%，海之眷顾每级增加5%
                int lureLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FISHING_SPEED, stack);
                int luckLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FISHING_LUCK, stack);
                double baseDamage = 5.0;
                double damage = baseDamage * (1.0 + (lureLevel * 0.1) + (luckLevel * 0.05));

                net.minecraft.world.phys.AABB aabb = hook.getBoundingBox().inflate(2.0D);
                java.util.List<LivingEntity> nearbyEntities = level.getEntitiesOfClass(LivingEntity.class, aabb);
                for (LivingEntity target : nearbyEntities) {
                    if (target != player) {
                        DeductPlayerSanityProcedure.execute(target, damage);
                    }
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (player.fishing != null) {
            if (!level.isClientSide) {
                Entity hooked = player.fishing.getHookedIn();
                FishingHook hook = player.fishing;

                int i = player.fishing.retrieve(itemstack);
                itemstack.hurtAndBreak(i, player, (p_41288_) -> {
                    p_41288_.broadcastBreakEvent(hand);
                });

                if (hooked != null) {
                    double d0 = player.getX() - hook.getX();
                    double d1 = player.getY() - hook.getY();
                    double d2 = player.getZ() - hook.getZ();
                    Vec3 vec3 = new Vec3(d0, d1, d2);
                    // 增加额外的拉力，使其约为1.5倍（原版为0.1，我们增加0.05）
                    Vec3 extraPull = vec3.normalize().scale(0.05);
                    hooked.setDeltaMovement(hooked.getDeltaMovement().add(extraPull));
                }
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {
                int k = EnchantmentHelper.getFishingSpeedBonus(itemstack);
                int j = EnchantmentHelper.getFishingLuckBonus(itemstack);
                level.addFreshEntity(new FishingHook(player, level, j, k));
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
