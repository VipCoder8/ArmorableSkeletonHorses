package net.vipryx.renderers;

import fuzs.eternalnether.world.entity.animal.horse.WitherSkeletonHorse;
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
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.ColorHelper;

public class WitherSkeletonHorseArmorFeatureRenderer extends FeatureRenderer<WitherSkeletonHorse, HorseEntityModel<WitherSkeletonHorse>> {

    private final HorseEntityModel<WitherSkeletonHorse> armorModel;

    public WitherSkeletonHorseArmorFeatureRenderer(FeatureRendererContext<WitherSkeletonHorse, HorseEntityModel<WitherSkeletonHorse>> context) {
        super(context);
        this.armorModel = new HorseEntityModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(EntityModelLayers.SKELETON_HORSE));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int i, WitherSkeletonHorse entity, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = entity.getBodyArmor();
        entity.getEquippedItems().forEach(e -> {
            System.out.println("for each item: " + e.getName());
        });
        System.out.println(itemStack.getName());
        if (itemStack.getItem() instanceof AnimalArmorItem animalArmorItem) {
            if (animalArmorItem.getType() == AnimalArmorItem.Type.EQUESTRIAN) {
                this.getContextModel().copyStateTo(this.armorModel);
                this.armorModel.animateModel(entity, f, g, h);
                this.armorModel.setAngles(entity, f, g, j, k, l);
                int m;
                if (itemStack.isIn(ItemTags.DYEABLE)) {
                    m = ColorHelper.Argb.fullAlpha(DyedColorComponent.getColor(itemStack, -6265536));
                } else {
                    m = -1;
                }

                VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(animalArmorItem.getEntityTexture()));
                this.armorModel.render(matrices, vertexConsumer, i, OverlayTexture.DEFAULT_UV, m);
                return;
            }
        }
    }
}