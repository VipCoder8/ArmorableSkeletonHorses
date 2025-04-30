package net.vipryx.renderers;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.state.HorseEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;

public class SkeletonHorseArmorFeatureRenderer extends FeatureRenderer<HorseEntityRenderState, HorseEntityModel> {
    private final HorseEntityModel armorModel;
    private final EquipmentRenderer equipmentRenderer;

    public SkeletonHorseArmorFeatureRenderer(FeatureRendererContext<HorseEntityRenderState, HorseEntityModel> context, LoadedEntityModels loader, EquipmentRenderer equipmentRenderer) {
        super(context);
        this.equipmentRenderer = equipmentRenderer;
        this.armorModel = new HorseEntityModel(loader.getModelPart(EntityModelLayers.HORSE_ARMOR));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, HorseEntityRenderState state, float limbAngle, float limbDistance) {
        ItemStack itemStack = state.armor;
        EquippableComponent equippableComponent = itemStack.get(DataComponentTypes.EQUIPPABLE);
        if (equippableComponent != null && equippableComponent.assetId().isPresent()) {
            HorseEntityModel horseEntityModel = this.armorModel;
            horseEntityModel.setAngles(state);
            this.equipmentRenderer.render(EquipmentModel.LayerType.HORSE_BODY, equippableComponent.assetId().get(), horseEntityModel, itemStack, matrices, vertexConsumers, light);
        }
    }
}
