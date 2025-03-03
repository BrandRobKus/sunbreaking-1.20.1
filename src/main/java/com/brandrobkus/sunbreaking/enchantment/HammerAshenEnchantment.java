package com.brandrobkus.sunbreaking.enchantment;

import com.brandrobkus.sunbreaking.util.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class HammerAshenEnchantment extends Enchantment {
    public HammerAshenEnchantment(Rarity weight, EquipmentSlot... slots) {
        super(weight, EnchantmentTarget.TRIDENT, slots);
    }

    //Hammer Enchantment, increases radius of Hammer Explosion
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isOf(ModItems.HAMMER_OF_SOL);
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return true;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return true;
    }

    @Override
    public int getMinPower(int level) {
        return 15;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

}
