package net.vipryx.mixin;

import com.izofar.bygonenether.client.renderer.WitherSkeletonHorseRenderer;
import com.izofar.bygonenether.entity.WitherSkeletonHorse;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieHorseEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.util.Identifier;
import net.vipryx.renderers.WitherSkeletonHorseArmorFeatureRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherSkeletonHorseRenderer.class)
public class WitherSkeletonHorseEntityRendererMixin extends ZombieHorseEntityRenderer {

    @Shadow @Final private static Identifier WITHER_SKELETON_HORSE_LOCATION;

    public WitherSkeletonHorseEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx, EntityModelLayers.SKELETON_HORSE);
    }

    @Inject(at = @At("RETURN"), method = "<init>")
    public void init(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.addFeature((FeatureRenderer<AbstractHorseEntity, HorseEntityModel<AbstractHorseEntity>>)(Object) new WitherSkeletonHorseArmorFeatureRenderer(new FeatureRendererContext<>() {
            @Override
            public HorseEntityModel<WitherSkeletonHorse> getModel() {
                HorseEntityModel<WitherSkeletonHorse> horseModel = new HorseEntityModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(EntityModelLayers.SKELETON_HORSE));
                horseModel.child = false;
                return horseModel;
            }

            @Override
            public Identifier getTexture(WitherSkeletonHorse entity) {
                return WITHER_SKELETON_HORSE_LOCATION;
            }
        }));
    }
}
