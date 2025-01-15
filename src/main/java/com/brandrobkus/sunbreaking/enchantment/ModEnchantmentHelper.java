package com.brandrobkus.sunbreaking.enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class ModEnchantmentHelper extends EnchantmentHelper {

    public static boolean hasAshen(ItemStack stack) {
        return EnchantmentHelper.getLevel(ModEnchantments.ASHEN, stack) > 0;
    }
    public static boolean hasSunspot(ItemStack stack) {
        return EnchantmentHelper.getLevel(ModEnchantments.CHAR, stack) > 0;
    }

    public static boolean hasIgnition(ItemStack stack) {
        return EnchantmentHelper.getLevel(ModEnchantments.ERUPTION, stack) > 0;
    }

}
