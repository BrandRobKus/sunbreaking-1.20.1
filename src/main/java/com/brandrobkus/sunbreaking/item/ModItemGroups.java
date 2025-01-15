package com.brandrobkus.sunbreaking.item;

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

                        entries.add(ModItems.HAMMER_OF_SOL);
                        entries.add(ModItems.IRON_HAMMER);
                        entries.add(ModItems.SOLAR_LIGHT);
                        entries.add(ModItems.SOLAR_UPGRADE_SMITHING_TEMPLATE);

                    }).build());

    public static void registerItemGroups(){
        Sunbreaking.LOGGER.info("Registering Item Groups for " + Sunbreaking.MOD_ID);
    }
}
