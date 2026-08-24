package net.vipryx.renderers;

import fuzs.eternalnether.world.entity.animal.horse.WitherSkeletonHorse;
import net.minecraft.client.render.entity.AbstractHorseEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.util.Identifier;

public class EternalWitherSkeletonHorseRenderer extends AbstractHorseEntityRenderer<WitherSkeletonHorse, HorseEntityModel<WitherSkeletonHorse>> {

    public EternalWitherSkeletonHorseRenderer(EntityRendererFactory.Context ctx, EntityModelLoader loader, float scale) {
        super(ctx, new HorseEntityModel<>(loader.getModelPart(EntityModelLayers.SKELETON_HORSE)), scale);
        this.addFeature(new WitherSkeletonHorseArmorFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(WitherSkeletonHorse entity) {
        return Identifier.of("eternalnether", "textures/entity/horse/wither_skeleton_horse.png");
    }
}
