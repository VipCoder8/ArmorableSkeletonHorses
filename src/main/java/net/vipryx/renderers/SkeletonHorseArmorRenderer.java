package net.vipryx.renderers;

import net.minecraft.client.render.entity.AbstractHorseEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HorseEntityRenderer;
import net.minecraft.client.render.entity.UndeadHorseEntityRenderer;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.render.entity.state.HorseEntityRenderState;
import net.minecraft.client.render.entity.state.LivingHorseEntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class SkeletonHorseArmorRenderer extends AbstractHorseEntityRenderer<SkeletonHorseEntity, HorseEntityRenderState, HorseEntityModel> {

    public SkeletonHorseArmorRenderer(EntityRendererFactory.Context ctx, LoadedEntityModels loader) {
        super(ctx, new HorseEntityModel(ctx.getPart(EntityModelLayers.HORSE)), new HorseEntityModel(ctx.getPart(EntityModelLayers.HORSE_BABY)));
        Function<HorseEntityRenderState, ItemStack> func = renderState -> renderState.saddleStack;
        this.addFeature(new SkeletonHorseArmorFeatureRenderer(this, loader, ctx.getEquipmentRenderer()));
        this.addFeature(new SaddleFeatureRenderer(this, ctx.getEquipmentRenderer(),
                        EquipmentModel.LayerType.HORSE_SADDLE, func,
                new HorseSaddleEntityModel(ctx.getPart(EntityModelLayers.HORSE_SADDLE)),
                new HorseSaddleEntityModel(ctx.getPart(EntityModelLayers.HORSE_BABY_SADDLE))));
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

