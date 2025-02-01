package com.brandrobkus.sunbreaking.item;

import com.brandrobkus.sunbreaking.Sunbreaking;
import com.brandrobkus.sunbreaking.item.custom.ModArmorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {

    public static final Item HAMMER_OF_SOL = registerItem("hammer_of_sol", new HammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item BLAZING_HAMMER = registerItem("blazing_hammer_of_sol", new HammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item IRON_HAMMER = registerItem("iron_hammer", new IronHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item SOLAR_LIGHT = registerItem("solar_light", new Item(new FabricItemSettings().maxCount(16)));
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

    public static final Item BLAZING_APPEARANCE_SMITHING_TEMPLATE = registerItem(
            "blazing_appearance_smithing_template",
            new SmithingTemplateItem(
                    Text.translatable("item.sunbreaking.blazing_appearance_template.title"),
                    Text.translatable("item.sunbreaking.blazing_appearance_template.base_slot"),
                    Text.translatable("item.sunbreaking.blazing_appearance_template.addition_slot"),
                    Text.translatable("item.sunbreaking.blazing_appearance_template.apply"),
                    Text.translatable("item.sunbreaking.blazing_appearance_template.base"),
                    List.of(new Identifier("sunbreaking", "item/addition_slot")),
                    List.of(new Identifier("sunbreaking", "item/base_slot"))));

    public static final Item SUNBREAKERS_HELMET = registerItem("sunbreakers_helmet",
            new ModArmorItem(ModArmorMaterials.SOLAR, ArmorItem.Type.HELMET, new FabricItemSettings().maxCount(1)));
    public static final Item SUNBREAKERS_CHESTPLATE = registerItem("sunbreakers_chestplate",
            new ArmorItem(ModArmorMaterials.SOLAR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().maxCount(1)));
    public static final Item SUNBREAKERS_GREAVES = registerItem("sunbreakers_greaves",
            new ArmorItem(ModArmorMaterials.SOLAR, ArmorItem.Type.LEGGINGS, new FabricItemSettings().maxCount(1)));
    public static final Item SUNBREAKERS_BOOTS = registerItem("sunbreakers_boots",
            new ArmorItem(ModArmorMaterials.SOLAR, ArmorItem.Type.BOOTS, new FabricItemSettings().maxCount(1)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Sunbreaking.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Sunbreaking.LOGGER.info("Registering Mod Items for " + Sunbreaking.MOD_ID);
    }
}
