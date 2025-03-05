package net.vipryx.mixin;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.vipryx.ArmorCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Inject(at = @At("RETURN"), method = "isHorseArmor", cancellable = true)
    public void isHorseArmor(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(ArmorCheck.isHorseArmor(stack)) {
            cir.setReturnValue(true);
        }
    }
}
