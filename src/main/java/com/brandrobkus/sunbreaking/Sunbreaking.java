package com.brandrobkus.sunbreaking;

import com.brandrobkus.sunbreaking.util.ModEnchantments;
import com.brandrobkus.sunbreaking.util.ModEntities;
import com.brandrobkus.sunbreaking.util.ModItemGroups;
import com.brandrobkus.sunbreaking.util.ModItems;
import com.brandrobkus.sunbreaking.sound.ModSounds;
import com.brandrobkus.sunbreaking.util.ModModelPredicateProvider;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sunbreaking implements ModInitializer {
	public static final String MOD_ID = "sunbreaking";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEnchantments.registerModEnchantments();
		ModEntities.registerModEntities();
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModModelPredicateProvider.registerModModel();
		ModSounds.registerSounds();
	}
}