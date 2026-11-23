package com.example.keysmod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class KeysRenderer extends MobRenderer<KeysEntity, LivingEntityRenderState, KeysModel> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(KeysMod.MOD_ID, "textures/entity/keys.png");

    public KeysRenderer(EntityRendererProvider.Context context) {
        super(context, new KeysModel(context.bakeLayer(KeysModel.LAYER)), 0.3F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
