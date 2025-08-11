package net.vipryx.renderers;

import net.minecraft.client.render.entity.AbstractHorseEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.util.Identifier;

public class SkeletonHorseArmorRenderer extends AbstractHorseEntityRenderer<SkeletonHorseEntity, HorseEntityModel<SkeletonHorseEntity>> {

    public SkeletonHorseArmorRenderer(EntityRendererFactory.Context ctx, EntityModelLoader loader, float scale) {
        super(ctx, new HorseEntityModel<>(loader.getModelPart(EntityModelLayers.SKELETON_HORSE)), scale);
        this.addFeature(new SkeletonHorseArmorFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(SkeletonHorseEntity entity) {
        return new Identifier("textures/entity/horse/horse_skeleton.png");
    }
}

