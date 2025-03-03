package com.brandrobkus.sunbreaking.util;

import com.brandrobkus.sunbreaking.enchantment.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final Enchantment ASHEN = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("sunbreaking", "ashen"),
            new HammerAshenEnchantment(Enchantment.Rarity.UNCOMMON,
                    EquipmentSlot.MAINHAND));

    public static final Enchantment CHAR = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("sunbreaking", "char"),
            new HammerCharEnchantment(Enchantment.Rarity.UNCOMMON,
                    EquipmentSlot.MAINHAND));

    public static final Enchantment ERUPTION = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("sunbreaking", "eruption"),
            new HammerEruptionEnchantment(Enchantment.Rarity.UNCOMMON,
                    EquipmentSlot.MAINHAND));

    public static final Enchantment BULK = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("sunbreaking", "bulk"),
            new HammerBulkEnchantment(
                    Enchantment.Rarity.UNCOMMON,
                    EquipmentSlot.MAINHAND));

    public static final Enchantment DILATION = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("sunbreaking", "dilation"),
            new BowDilationEnchantment(
                    Enchantment.Rarity.UNCOMMON,
                    EquipmentSlot.MAINHAND));


    public static void registerModEnchantments() {
        System.out.println("Registering Mod Enchantments for Sunbreaking");
    }

}
