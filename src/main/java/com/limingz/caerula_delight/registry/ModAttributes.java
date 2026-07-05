package com.limingz.caerula_delight.registry;

import com.limingz.caerula_delight.CaerulaDelightMod;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(ForgeRegistries.ATTRIBUTES, CaerulaDelightMod.MODID);

    public static final RegistryObject<Attribute> SANITY_VULNERABILITY = ATTRIBUTES.register("sanity_vulnerability",
            () -> new RangedAttribute("attribute.caerula_delight.sanity_vulnerability", 0.0, 0.0, 1024.0).setSyncable(true));

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
        eventBus.register(ModAttributes.class);
    }

    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
        event.getTypes().forEach(entityType -> event.add(entityType, SANITY_VULNERABILITY.get()));
    }
}
