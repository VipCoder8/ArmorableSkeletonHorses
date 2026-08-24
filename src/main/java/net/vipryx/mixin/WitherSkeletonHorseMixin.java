package net.vipryx.mixin;

import fuzs.eternalnether.world.entity.animal.horse.WitherSkeletonHorse;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WitherSkeletonHorse.class)
public class WitherSkeletonHorseMixin extends SkeletonHorseEntity {

    public WitherSkeletonHorseMixin(EntityType<? extends SkeletonHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isBaby() {
        return false;
    }
}