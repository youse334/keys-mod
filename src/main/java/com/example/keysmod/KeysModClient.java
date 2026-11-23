package com.example.keysmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

public class KeysModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(KeysModel.LAYER, KeysModel::createBodyLayer);
        EntityRendererRegistry.register(KeysMod.KEYS, KeysRenderer::new);
    }
}
