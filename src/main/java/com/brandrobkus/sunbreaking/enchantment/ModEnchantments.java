package com.brandrobkus.sunbreaking.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final Enchantment ASHEN = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("sunbreaking", "ashen"),
            new HammerAshenEnchantment(
                    Enchantment.Rarity.UNCOMMON,
                    EquipmentSlot.MAINHAND
            )
    );

    public static final Enchantment SUNSPOT = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("sunbreaking", "sunspot"),
            new HammerSunspotEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND)
    );

    public static final Enchantment IGNITION = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("sunbreaking", "ignition"),
            new HammerIgnitionEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND)
    );

    public static final Enchantment BULK = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("sunbreaking", "bulk"),
            new HammerBulkEnchantment(
                    Enchantment.Rarity.UNCOMMON,
                    EquipmentSlot.MAINHAND));

    public static void registerModEnchantments() {
        System.out.println("Registering Mod Enchantments for Sunbreaking");
    }
}
