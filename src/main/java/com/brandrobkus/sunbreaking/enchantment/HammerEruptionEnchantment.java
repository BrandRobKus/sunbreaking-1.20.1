package com.brandrobkus.sunbreaking.enchantment;

import com.brandrobkus.sunbreaking.util.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class HammerEruptionEnchantment extends Enchantment {
    public HammerEruptionEnchantment(Rarity weight, EquipmentSlot... slot) {
        super(weight, EnchantmentTarget.TRIDENT, slot);
    }

    //Hammer enchantment, blows up blocks in explosion radius
    @Override
    public int getMinPower(int level) {
        return 20;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isOf(ModItems.HAMMER_OF_SOL);
    }
}
