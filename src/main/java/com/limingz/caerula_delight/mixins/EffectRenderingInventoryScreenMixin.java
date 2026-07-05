package com.limingz.caerula_delight.mixins;

import com.limingz.caerula_delight.registry.ModAttributes;
import com.limingz.caerula_delight.registry.ModMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(EffectRenderingInventoryScreen.class)
public class EffectRenderingInventoryScreenMixin {
    private static MobEffectInstance CAERULA_DELIGHT_HOVERED_EFFECT;

    @ModifyVariable(
            method = "renderEffects",
            at = @At(value = "STORE"),
            ordinal = 0
    )
    private MobEffectInstance caerula_delight$captureHoveredEffect(MobEffectInstance instance) {
        if (instance != null) {
            CAERULA_DELIGHT_HOVERED_EFFECT = instance;
        }
        return instance;
    }

    @ModifyVariable(
            method = "renderEffects",
            at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;of(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;"),
            ordinal = 0
    )
    private List<Component> caerula_delight$addEffectDescription(List<Component> list) {
        MobEffectInstance instance = CAERULA_DELIGHT_HOVERED_EFFECT;
        if (instance != null && instance.getEffect() == ModMobEffects.SANITY_VULNERABILITY.get()) {
            List<Component> newList = new ArrayList<>(list.size() + 1);
            newList.addAll(list);

            double vulnerability = 0.0;
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null && player.getAttributes().hasAttribute(ModAttributes.SANITY_VULNERABILITY.get())) {
                vulnerability = player.getAttributeValue(ModAttributes.SANITY_VULNERABILITY.get());
            }
            int percentage = (int) Math.round(vulnerability * 100.0);
            newList.add(Component.translatable("effect.caerula_delight.sanity_vulnerability.description", percentage)
                    .withStyle(ChatFormatting.RED));
            return newList;
        }
        return list;
    }
}
