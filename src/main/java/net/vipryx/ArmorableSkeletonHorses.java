package net.vipryx;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.EntityType;
import net.vipryx.renderers.SkeletonHorseArmorRenderer;

public class ArmorableSkeletonHorses implements ClientModInitializer, DataGeneratorEntrypoint {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(EntityType.SKELETON_HORSE, (EntityRendererFactory.Context ctx) ->
				new SkeletonHorseArmorRenderer(ctx, ctx.getEntityModels()));
	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(SkeletonHorseEntityTagProvider::new);
	}

}