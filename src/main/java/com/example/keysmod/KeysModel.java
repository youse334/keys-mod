package com.example.keysmod;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

/** An upright door key: round bow (ring) on top, shaft in the middle, two teeth on the side. */
public class KeysModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(KeysMod.MOD_ID, "keys"), "main");

    private final ModelPart key;

    public KeysModel(ModelPart root) {
        super(root);
        this.key = root.getChild("key");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("key",
                CubeListBuilder.create()
                        // shaft (has the face on its front)
                        .texOffs(0, 0).addBox(-2.0F, -12.0F, -1.5F, 4.0F, 12.0F, 3.0F)
                        // bow / ring: top, bottom, left, right (4x4 hole in the middle)
                        .texOffs(32, 0).addBox(-5.0F, -22.0F, -1.5F, 10.0F, 3.0F, 3.0F)
                        .texOffs(32, 0).addBox(-5.0F, -15.0F, -1.5F, 10.0F, 3.0F, 3.0F)
                        .texOffs(32, 0).addBox(-5.0F, -19.0F, -1.5F, 3.0F, 4.0F, 3.0F)
                        .texOffs(32, 0).addBox(2.0F, -19.0F, -1.5F, 3.0F, 4.0F, 3.0F)
                        // teeth
                        .texOffs(32, 0).addBox(2.0F, -10.0F, -1.5F, 2.0F, 2.0F, 3.0F)
                        .texOffs(32, 0).addBox(2.0F, -6.0F, -1.5F, 3.0F, 2.0F, 3.0F),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        // little waddle while walking
        this.key.zRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 0.25F * state.walkAnimationSpeed;
    }
}
