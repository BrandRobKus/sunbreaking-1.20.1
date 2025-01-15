package com.brandrobkus.sunbreaking.item;

import com.brandrobkus.sunbreaking.Sunbreaking;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {

    public static final Item HAMMER_OF_SOL = registerItem("hammer_of_sol", new HammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item IRON_HAMMER = registerItem("iron_hammer", new IronHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item SOLAR_LIGHT = registerItem("solar_light", new Item(new FabricItemSettings()));
    public static final Item SOLAR_UPGRADE_SMITHING_TEMPLATE = registerItem(
            "solar_upgrade_smithing_template",
            new SmithingTemplateItem(
                    Text.translatable("item.sunbreaking.solar_upgrade_template.title"),
                    Text.translatable("item.sunbreaking.solar_upgrade_template.base_slot"),
                    Text.translatable("item.sunbreaking.solar_upgrade_template.addition_slot"),
                    Text.translatable("item.sunbreaking.solar_upgrade_template.apply"),
                    Text.translatable("item.sunbreaking.solar_upgrade_template.base"),
                    List.of(new Identifier("sunbreaking", "item/addition_slot")),
                    List.of(new Identifier("sunbreaking", "item/base_slot"))));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Sunbreaking.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Sunbreaking.LOGGER.info("Registering Mod Items for " + Sunbreaking.MOD_ID);
    }
}
