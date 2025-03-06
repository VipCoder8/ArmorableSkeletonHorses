package net.vipryx.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.vipryx.ArmorCheck;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkeletonHorseEntity.class)
public abstract class SkeletonHorseMixin extends AbstractHorseEntity {

    protected SkeletonHorseMixin(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void onInventoryChanged(Inventory sender) {
        super.onInventoryChanged(sender);
        if(!this.isBaby()) {
            ItemStack armorStack = this.items.getStack(1);
            if (armorStack == ItemStack.EMPTY) {
                SkeletonHorseMixin.this.equipStack(EquipmentSlot.LEGS, ItemStack.EMPTY);
            }
            if (armorStack == null || !ArmorCheck.isHorseArmor(armorStack)) {
                return;
            }
            if (armorStack.getItem() instanceof HorseArmorItem) {
                SkeletonHorseMixin.this.equipStack(EquipmentSlot.LEGS, armorStack);
            }
            this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(((HorseArmorItem) armorStack.getItem()).getBonus());
        }
    }

    @Unique
    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
    public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if(!isBaby()) {
            if(this.getFirstPassenger() == null) {
                if(player.getStackInHand(hand) != ItemStack.EMPTY && player.getStackInHand(hand).getItem() instanceof HorseArmorItem) {
                    ItemStack handItem = player.getStackInHand(hand);
                    ItemStack currentHorseArmor = this.items.getStack(1);

                    this.items.setStack(1, handItem);
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
            nbt.put("Armor", items.getStack(1).writeNbt(new NbtCompound()));
        }
    }
    @Unique
    @Inject(at = @At("RETURN"), method = "readCustomDataFromNbt")
    public void readArmor(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("Armor", 10)) {
            ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("Armor"));
            if (!itemStack.isEmpty() && this.isHorseArmor(itemStack)) {
                this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(((HorseArmorItem)itemStack.getItem()).getBonus());
                this.equipStack(EquipmentSlot.LEGS, itemStack);
                this.items.setStack(1, itemStack);
            }
        }
    }

    @Override
    public boolean hasArmorSlot() {
        return true;
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
