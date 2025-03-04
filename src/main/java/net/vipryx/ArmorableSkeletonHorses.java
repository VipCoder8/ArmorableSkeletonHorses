package net.vipryx;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.EntityType;
import net.vipryx.renderers.SkeletonHorseArmorRenderer;

public class ArmorableSkeletonHorses implements ModInitializer {
	public static final String MOD_ID = "template-mod";

	@Override
	public void onInitialize() {
		EntityRendererRegistry.register(EntityType.SKELETON_HORSE, (EntityRendererFactory.Context ctx) ->
				new SkeletonHorseArmorRenderer(ctx, ctx.getModelLoader(), 1.1f));
	}
}