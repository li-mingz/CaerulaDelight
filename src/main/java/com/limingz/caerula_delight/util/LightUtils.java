package com.limingz.caerula_delight.util;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.world.entity.Entity;

public class LightUtils {
    public static void addLight(Entity entity, double amount) {
        if (entity == null) return;

        entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent((capability) -> {
            capability.player_light = Math.min(100, Math.max(0, capability.player_light + amount));
            capability.syncPlayerVariables(entity);
        });
    }

    public static void decreaseLight(Entity entity, double amount) {
        addLight(entity, -amount);
    }
}
