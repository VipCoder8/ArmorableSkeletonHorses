package net.vipryx;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;

public class ArmorDefense {
    public static int getArmorItemDefense(ItemStack item) {
        if(item.isOf(Items.DIAMOND_HORSE_ARMOR)) {
            return ArmorMaterials.DIAMOND.defense().get(EquipmentType.BODY);
        } else if(item.isOf(Items.GOLDEN_HORSE_ARMOR)) {
            return ArmorMaterials.GOLD.defense().get(EquipmentType.BODY);
        } else if(item.isOf(Items.IRON_HORSE_ARMOR)) {
            return ArmorMaterials.IRON.defense().get(EquipmentType.BODY);
        } else if(item.isOf(Items.LEATHER_HORSE_ARMOR)) {
            return ArmorMaterials.LEATHER.defense().get(EquipmentType.BODY);
        }
        return 0;
    }
}
