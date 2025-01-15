package com.brandrobkus.sunbreaking;

import com.brandrobkus.sunbreaking.enchantment.ModEnchantments;
import com.brandrobkus.sunbreaking.item.ModItemGroups;
import com.brandrobkus.sunbreaking.item.ModItems;
import com.brandrobkus.sunbreaking.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.minecraft.loot.LootTable;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sunbreaking implements ModInitializer {
	public static final String MOD_ID = "sunbreaking";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModSounds.registerSounds();
		ModEnchantments.registerModEnchantments();
	}
}