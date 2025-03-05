package net.vipryx;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.vipryx.renderers.SkeletonHorseArmorRenderer;

public class ArmorableSkeletonHorses implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(EntityType.SKELETON_HORSE, (EntityRendererFactory.Context ctx) ->
				new SkeletonHorseArmorRenderer(ctx, ctx.getModelLoader(), 1.1f));
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, damageSource, amount) -> {
			if (entity instanceof SkeletonHorseEntity) {
				if (damageSource.getType().effects() == DamageEffects.BURNING) {
					return false;
				}
			}
			return true;
		});
	}
}