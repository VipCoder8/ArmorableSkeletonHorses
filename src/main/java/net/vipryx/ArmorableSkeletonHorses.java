package net.vipryx;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.EntityType;
import net.vipryx.renderers.EternalWitherSkeletonHorseRenderer;
import net.vipryx.renderers.SkeletonHorseArmorRenderer;

import static fuzs.eternalnether.init.ModEntityTypes.WITHER_SKELETON_HORSE;

public class ArmorableSkeletonHorses implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(EntityType.SKELETON_HORSE, (EntityRendererFactory.Context ctx) ->
				new SkeletonHorseArmorRenderer(ctx, ctx.getModelLoader(), 1.1f));
		if(FabricLoader.getInstance().isModLoaded("eternalnether")) {
			EntityRendererRegistry.register(WITHER_SKELETON_HORSE.value(), (EntityRendererFactory.Context ctx) ->
					new EternalWitherSkeletonHorseRenderer(ctx, ctx.getModelLoader(), 1.1f));
		}
	}
}