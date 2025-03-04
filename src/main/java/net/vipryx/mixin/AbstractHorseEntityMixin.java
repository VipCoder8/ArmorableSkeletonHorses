package net.vipryx.mixin;

import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.vipryx.ArmorCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractHorseEntity.class)
public class AbstractHorseEntityMixin {
    @Inject(at = @At("RETURN"), method = "isHorseArmor", cancellable = true)
    void isHorseArmor(ItemStack item, CallbackInfoReturnable<Boolean> cir) {
        if(ArmorCheck.isHorseArmor(item)) {
            cir.setReturnValue(true);
        }
    }
    @Inject(at = @At("RETURN"), method = "equipHorseArmor")
    public void equip(PlayerEntity player, ItemStack stack, CallbackInfo ci) {

    }
}