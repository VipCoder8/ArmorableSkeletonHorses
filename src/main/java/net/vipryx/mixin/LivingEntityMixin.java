package net.vipryx.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.vipryx.ArmorCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at = @At("RETURN"), method = "canEquip", cancellable = true)
    public void canEquip(ItemStack stack, EquipmentSlot slot, CallbackInfoReturnable<Boolean> cir) {
        if(slot == EquipmentSlot.BODY) {
            cir.setReturnValue(ArmorCheck.isHorseArmor(stack.getItem()));
        } else if(slot == EquipmentSlot.SADDLE) {
            cir.setReturnValue(true);
        }
    }
}
