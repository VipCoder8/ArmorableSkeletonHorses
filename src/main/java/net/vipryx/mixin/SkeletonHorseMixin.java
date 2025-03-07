package net.vipryx.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.vipryx.ArmorCheck;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkeletonHorseEntity.class)
public abstract class SkeletonHorseMixin extends AbstractHorseEntity {
    @Shadow protected abstract void playJumpSound();

    protected SkeletonHorseMixin(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }
    @Override
    public void onEquipStack(EquipmentSlot slot, ItemStack oldStack, ItemStack newStack) {
        super.onEquipStack(slot, oldStack, newStack);
        if(!this.isBaby()) {
            ItemStack armorStack = this.getBodyArmor();
            if (armorStack == null || !ArmorCheck.isHorseArmor(armorStack)) {
                return;
            }
            this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(((AnimalArmorItem) armorStack.getItem()).getProtection());
        }
    }

    @Unique
    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
    public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if(!isBaby()) {
            if(this.getFirstPassenger() == null) {
                if(player.getStackInHand(hand) != ItemStack.EMPTY && player.getStackInHand(hand).getItem() instanceof AnimalArmorItem) {
                    ItemStack handItem = player.getStackInHand(hand);
                    ItemStack currentHorseArmor = this.getBodyArmor();

                    this.equipBodyArmor(handItem);
                    this.onInventoryChanged(player.getInventory());
                    player.setStackInHand(hand, currentHorseArmor);

                    cir.setReturnValue(ActionResult.SUCCESS);
                }
            }
        }
    }

    @Unique
    @Inject(at = @At("RETURN"), method = "writeCustomDataToNbt")
    public void saveArmor(NbtCompound nbt, CallbackInfo ci) {
        if(this.items.getStack(1) != ItemStack.EMPTY) {
            nbt.put("Armor", items.getStack(1).encode(this.getRegistryManager()));
        }
    }
    @Unique
    @Inject(at = @At("RETURN"), method = "readCustomDataFromNbt")
    public void readArmor(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("Armor", 10)) {
            ItemStack itemStack = (ItemStack)ItemStack.fromNbt(this.getRegistryManager(), nbt.getCompound("Armor")).orElse(ItemStack.EMPTY);
            if (!itemStack.isEmpty() && this.isHorseArmor(itemStack)) {
                this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(((AnimalArmorItem)itemStack.getItem()).getProtection());
                this.equipStack(EquipmentSlot.LEGS, itemStack);
                this.items.setStack(1, itemStack);
            }
        }
    }

    @Override
    public boolean canUseSlot(EquipmentSlot slot) {
        return slot == EquipmentSlot.BODY;
    }

    //Unused implemented methods.
    @Override
    public boolean canBeSaddled() {
        return !isBaby();
    }

    @Override
    public boolean isTame() {
        return true;
    }

    @Override
    public boolean cannotBeSilenced() {
        return super.cannotBeSilenced();
    }

    @Override
    public @Nullable LivingEntity getOwner() {
        return super.getOwner();
    }

    @Override
    public int getJumpCooldown() {
        return super.getJumpCooldown();
    }

    @Override
    public SoundEvent getSaddleSound() {
        return super.getSaddleSound();
    }
}
