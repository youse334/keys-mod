package com.example.keysmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class KeysMod implements ModInitializer {
    public static final String MOD_ID = "keys_mod";

    public static EntityType<KeysEntity> KEYS;
    public static Item KEYS_SPAWN_EGG;

    @Override
    public void onInitialize() {
        // --- Entity ---------------------------------------------------------
        // Deliberately NO spawn placement and NO biome spawn entries,
        // so the mob never spawns naturally. Only the spawn egg can create it.
        ResourceKey<EntityType<?>> entityKey =
                ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "keys"));

        KEYS = Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                entityKey,
                EntityType.Builder.of(KeysEntity::new, MobCategory.MONSTER)
                        .sized(0.6f, 1.4f)
                        .clientTrackingRange(10)
                        .build(entityKey));

        FabricDefaultAttributeRegistry.register(KEYS, KeysEntity.createAttributes());

        // --- Spawn egg --------------------------------------------------------
        ResourceKey<Item> eggKey =
                ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "keys_spawn_egg"));

        KEYS_SPAWN_EGG = Registry.register(
                BuiltInRegistries.ITEM,
                eggKey,
                new SpawnEggItem(new Item.Properties().setId(eggKey).spawnEgg(KEYS)));

        // Show it in the creative menu's Spawn Eggs tab
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS)
                .register(output -> output.accept(KEYS_SPAWN_EGG));
    }
}
