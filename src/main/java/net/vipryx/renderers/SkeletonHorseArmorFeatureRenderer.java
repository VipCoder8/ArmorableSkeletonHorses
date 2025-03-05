package net.vipryx.renderers;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.ColorHelper;

public class SkeletonHorseArmorFeatureRenderer extends FeatureRenderer<SkeletonHorseEntity, HorseEntityModel<SkeletonHorseEntity>> {
    private final HorseEntityModel<SkeletonHorseEntity> armorModel;

    public SkeletonHorseArmorFeatureRenderer(FeatureRendererContext<SkeletonHorseEntity, HorseEntityModel<SkeletonHorseEntity>> context) {
        super(context);
        this.armorModel = new HorseEntityModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(EntityModelLayers.HORSE_ARMOR));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int i, SkeletonHorseEntity entity, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = entity.getBodyArmor();
        if(itemStack == null) {
            return;
        }
        if (itemStack.getItem() instanceof AnimalArmorItem horseArmorItem) {
            this.getContextModel().copyStateTo(this.armorModel);
            this.armorModel.animateModel(entity, f, g, h);
            this.armorModel.setAngles(entity, f, g, j, k, l);
            float n;
            float o;
            float p;
            if (itemStack.isIn(ItemTags.DYEABLE)) {
                int m = DyedColorComponent.getColor(itemStack, -6265536);
                n = (float) ColorHelper.Argb.getRed(m) / 255.0F;
                o = (float) ColorHelper.Argb.getGreen(m) / 255.0F;
                p = (float) ColorHelper.Argb.getBlue(m) / 255.0F;
            } else {
                n = 1.0F;
                o = 1.0F;
                p = 1.0F;
            }

            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(horseArmorItem.getEntityTexture()));
            this.armorModel.render(matrices, vertexConsumer, i, OverlayTexture.DEFAULT_UV, n, o, p, 1.0F);
        }
    }
}
