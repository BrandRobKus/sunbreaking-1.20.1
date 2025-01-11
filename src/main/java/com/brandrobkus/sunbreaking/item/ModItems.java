package com.brandrobkus.sunbreaking.item;

import com.brandrobkus.sunbreaking.Sunbreaking;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item HAMMER_OF_SOL = registerItem("hammer_of_sol", new HammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item SOLAR_LIGHT = registerItem("solar_light", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Sunbreaking.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Sunbreaking.LOGGER.info("Registering Mod Items for " + Sunbreaking.MOD_ID);
    }
}
