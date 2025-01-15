package com.brandrobkus.sunbreaking.enchantment;

import com.brandrobkus.sunbreaking.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class HammerAshenEnchantment extends Enchantment {
    public HammerAshenEnchantment(Rarity weight, EquipmentSlot... slots) {
        super(weight, EnchantmentTarget.TRIDENT, slots);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        // This will make sure the enchantment can only be applied to the HAMMER_OF_SOL with an anvil
        return stack.isOf(ModItems.HAMMER_OF_SOL);
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        // This allows the enchantment to appear on books in the enchanting table
        return true; // Only apply to books in the enchantment table
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        // This allows the enchantment to appear randomly on books
        return true; // Available for random selection on books
    }

    @Override
    public int getMinPower(int level) {
        return 15; // Minimum enchantment power needed for this enchantment to appear
    }

    @Override
    public int getMaxPower(int level) {
        return 50; // Maximum enchantment power needed for this enchantment to appear
    }

    @Override
    public int getMaxLevel() {
        return 1; // Only one level for this enchantment
    }
}
