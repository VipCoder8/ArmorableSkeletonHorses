package net.vipryx.renderers;

import net.minecraft.client.render.entity.AbstractHorseEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.render.entity.state.HorseEntityRenderState;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.util.Identifier;

public class SkeletonHorseArmorRenderer extends AbstractHorseEntityRenderer<SkeletonHorseEntity, HorseEntityRenderState, HorseEntityModel> {

    public SkeletonHorseArmorRenderer(EntityRendererFactory.Context ctx, EntityModelLoader loader, float scale) {
        super(ctx, new HorseEntityModel(ctx.getPart(EntityModelLayers.HORSE)), new HorseEntityModel(ctx.getPart(EntityModelLayers.HORSE_BABY)), scale);
        this.addFeature(new SkeletonHorseArmorFeatureRenderer(this, loader, ctx.getEquipmentRenderer()));
    }

    @Override
    public void updateRenderState(SkeletonHorseEntity abstractHorseEntity, HorseEntityRenderState livingHorseEntityRenderState, float f) {
        super.updateRenderState(abstractHorseEntity, livingHorseEntityRenderState, f);
        livingHorseEntityRenderState.armor = abstractHorseEntity.getBodyArmor().copy();
    }

    @Override
    public HorseEntityRenderState createRenderState() {
        return new HorseEntityRenderState();
    }

    @Override
    public Identifier getTexture(HorseEntityRenderState state) {
        return Identifier.ofVanilla("textures/entity/horse/horse_skeleton.png");
    }
}

