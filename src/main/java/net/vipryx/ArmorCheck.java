package net.vipryx;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class ArmorCheck {
    public static boolean isHorseArmor(Item stack) {
        return stack == Items.DIAMOND_HORSE_ARMOR ||
                stack == Items.GOLDEN_HORSE_ARMOR ||
                stack == Items.IRON_HORSE_ARMOR ||
                stack == Items.LEATHER_HORSE_ARMOR;
    }
}
