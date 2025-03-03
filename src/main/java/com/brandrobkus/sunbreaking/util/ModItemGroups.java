package com.brandrobkus.sunbreaking.util;

import com.brandrobkus.sunbreaking.Sunbreaking;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup HAMMER_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(Sunbreaking.MOD_ID, "hammer"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.hammer"))
                    .icon(() -> new ItemStack(ModItems.SOLAR_LIGHT)).entries((displayContext, entries) -> {

                        entries.add(ModItems.IRON_HAMMER);
                        entries.add(ModItems.HAMMER_OF_SOL);
                        entries.add(ModItems.HAMMER_APPEARANCE_SMITHING_TEMPLATE);
                        entries.add(ModItems.BLAZING_HAMMER);
                        entries.add(ModItems.BLAZING_APPEARANCE_SMITHING_TEMPLATE);
                        entries.add(ModItems.HELLBENT_HAMMER);
                        entries.add(ModItems.HELLBENT_APPEARANCE_SMITHING_TEMPLATE);
                        entries.add(ModItems.FLAT_HAMMER);
                        entries.add(ModItems.FLAT_APPEARANCE_SMITHING_TEMPLATE);

                        entries.add(ModItems.SUNBREAKERS_HELMET);
                        entries.add(ModItems.SUNBREAKERS_CHESTPLATE);
                        entries.add(ModItems.SUNBREAKERS_GREAVES);
                        entries.add(ModItems.SUNBREAKERS_BOOTS);

                        entries.add(ModItems.SOLAR_LIGHT);
                        entries.add(ModItems.SOLAR_UPGRADE_SMITHING_TEMPLATE);

                        entries.add(ModItems.SHADOWSHOT_BOW);
                        entries.add(ModItems.SHADOWSHOT_ARROW);
                        entries.add(ModItems.VOID_LIGHT);
                        entries.add(ModItems.VOID_UPGRADE_SMITHING_TEMPLATE);

                        entries.add(ModItems.STORMCALLERS_BOND);
                        entries.add(ModItems.ARC_LIGHT);

                        entries.add(ModItems.WOODEN_GLAIVE);
                        entries.add(ModItems.STONE_GLAIVE);
                        entries.add(ModItems.IRON_GLAIVE);
                        entries.add(ModItems.GOLD_GLAIVE);
                        entries.add(ModItems.DIAMOND_GLAIVE);
                        entries.add(ModItems.NETHERITE_GLAIVE);

                    }).build());

    public static void registerItemGroups(){
        Sunbreaking.LOGGER.info("Registering Item Groups for " + Sunbreaking.MOD_ID);
    }
}
