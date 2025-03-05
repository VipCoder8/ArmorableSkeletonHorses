package net.vipryx;

import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ItemStack;

public class ArmorCheck {
    public static boolean isHorseArmor(ItemStack stack) {
        return stack.getItem() instanceof AnimalArmorItem;
    }
}
