package net.vipryx;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.EntityType;
import net.vipryx.renderers.SkeletonHorseArmorRenderer;

public class ArmorableSkeletonHorses implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(EntityType.SKELETON_HORSE, (EntityRendererFactory.Context ctx) ->
				new SkeletonHorseArmorRenderer(ctx, ctx.getEntityModels()));
	}
}