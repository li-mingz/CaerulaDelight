package com.limingz.caerula_delight.registry;

import com.limingz.caerula_delight.CaerulaDelightMod;
import com.limingz.caerula_delight.block.entity.SimpleFeastBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CaerulaDelightMod.MODID);

    public static final RegistryObject<BlockEntityType<SimpleFeastBlockEntity>> GLOW_SEA_PUDDING_ENTITY =
            BLOCK_ENTITIES.register("glow_sea_pudding_block", () ->
                    BlockEntityType.Builder.of(
                            (pos, state) -> new SimpleFeastBlockEntity(ModBlockEntities.GLOW_SEA_PUDDING_ENTITY.get(), pos, state),
                            ModBlock.GLOW_SEA_PUDDING_BLOCK.get()
                    ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

