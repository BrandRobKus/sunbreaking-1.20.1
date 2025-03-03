package com.brandrobkus.sunbreaking.util;

import com.brandrobkus.sunbreaking.Sunbreaking;
import com.brandrobkus.sunbreaking.item.*;
import com.brandrobkus.sunbreaking.item.custom.ModSolarArmorItem;
import com.brandrobkus.sunbreaking.item.custom.ModArmorMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {

    public static final Item HAMMER_OF_SOL = registerItem("hammer_of_sol",
            new HammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item BLAZING_HAMMER = registerItem("blazing_hammer_of_sol",
            new BlazingHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item HELLBENT_HAMMER = registerItem("hellbent_hammer_of_sol",
            new HellbentHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item FLAT_HAMMER = registerItem("2d_hammer_of_sol",
            new FlatHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item IRON_HAMMER = registerItem("iron_hammer",
            new IronHammerItem(new FabricItemSettings().maxCount(1)));

    public static final Item SOLAR_LIGHT = registerItem("solar_light",
            new Item(new FabricItemSettings().maxCount(16).fireproof()));
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

    public static final Item HAMMER_APPEARANCE_SMITHING_TEMPLATE = registerItem(
            "hammer_appearance_smithing_template",
            new SmithingTemplateItem(
                    Text.translatable("item.sunbreaking.hammer_appearance_template.title"),
                    Text.translatable("item.sunbreaking.hammer_appearance_template.base_slot"),
                    Text.translatable("item.sunbreaking.hammer_appearance_template.addition_slot"),
                    Text.translatable("item.sunbreaking.hammer_appearance_template.apply"),
                    Text.translatable("item.sunbreaking.hammer_appearance_template.base"),
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

    public static final Item HELLBENT_APPEARANCE_SMITHING_TEMPLATE = registerItem(
            "hellbent_appearance_smithing_template",
            new SmithingTemplateItem(
                    Text.translatable("item.sunbreaking.hellbent_appearance_template.title"),
                    Text.translatable("item.sunbreaking.hellbent_appearance_template.base_slot"),
                    Text.translatable("item.sunbreaking.hellbent_appearance_template.addition_slot"),
                    Text.translatable("item.sunbreaking.hellbent_appearance_template.apply"),
                    Text.translatable("item.sunbreaking.hellbent_appearance_template.base"),
                    List.of(new Identifier("sunbreaking", "item/addition_slot")),
                    List.of(new Identifier("sunbreaking", "item/base_slot"))));

    public static final Item FLAT_APPEARANCE_SMITHING_TEMPLATE = registerItem(
            "flat_appearance_smithing_template",
            new SmithingTemplateItem(
                    Text.translatable("item.sunbreaking.flat_appearance_template.title"),
                    Text.translatable("item.sunbreaking.flat_appearance_template.base_slot"),
                    Text.translatable("item.sunbreaking.flat_appearance_template.addition_slot"),
                    Text.translatable("item.sunbreaking.flat_appearance_template.apply"),
                    Text.translatable("item.sunbreaking.flat_appearance_template.base"),
                    List.of(new Identifier("sunbreaking", "item/addition_slot")),
                    List.of(new Identifier("sunbreaking", "item/base_slot"))));

    public static final Item SUNBREAKERS_HELMET = registerItem("sunbreakers_helmet",
            new ModSolarArmorItem(ModArmorMaterials.SOLAR, ArmorItem.Type.HELMET, new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item SUNBREAKERS_CHESTPLATE = registerItem("sunbreakers_chestplate",
            new ModSolarArmorItem(ModArmorMaterials.SOLAR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item SUNBREAKERS_GREAVES = registerItem("sunbreakers_greaves",
            new ModSolarArmorItem(ModArmorMaterials.SOLAR, ArmorItem.Type.LEGGINGS, new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item SUNBREAKERS_BOOTS = registerItem("sunbreakers_boots",
            new ModSolarArmorItem(ModArmorMaterials.SOLAR, ArmorItem.Type.BOOTS, new FabricItemSettings().maxCount(1).fireproof()));

    public static final Item SHADOWSHOT_BOW = registerItem("shadowshot_bow",
            new ShadowshotBowItem(new FabricItemSettings().maxDamage(500)));
    public static final Item SHADOWSHOT_ARROW = registerItem("shadowshot_arrow",
            new ShadowshotArrowItem(new FabricItemSettings()));

    public static final Item VOID_LIGHT = registerItem("void_light", new Item(new FabricItemSettings().maxCount(16).fireproof()));
    public static final Item VOID_LIGHT_SHARD = registerItem("void_light_shard", new Item(new FabricItemSettings()));
    public static final Item VOID_UPGRADE_SMITHING_TEMPLATE = registerItem(
            "void_upgrade_smithing_template",
            new SmithingTemplateItem(
                    Text.translatable("item.sunbreaking.void_upgrade_template.title"),
                    Text.translatable("item.sunbreaking.void_upgrade_template.base_slot"),
                    Text.translatable("item.sunbreaking.void_upgrade_template.addition_slot"),
                    Text.translatable("item.sunbreaking.void_upgrade_template.apply"),
                    Text.translatable("item.sunbreaking.void_upgrade_template.base"),
                    List.of(new Identifier("sunbreaking", "item/addition_slot_bow")),
                    List.of(new Identifier("sunbreaking", "item/base_slot_void"))));

    public static final Item STORMCALLERS_BOND = registerItem("stormcallers_bond",
            new BondItem(new FabricItemSettings().maxCount(1)));

    public static final Item ARC_LIGHT = registerItem("arc_light",
            new Item(new FabricItemSettings().maxCount(16).fireproof()));

    public static final Item WOODEN_GLAIVE = registerItem("wooden_glaive",
            new GlaiveItem(ToolMaterials.WOOD, 5, -2.8F, new Item.Settings()));
    public static final Item STONE_GLAIVE = registerItem("stone_glaive",
            new GlaiveItem(ToolMaterials.STONE, 5, -2.8F, new Item.Settings()));
    public static final Item IRON_GLAIVE = registerItem("iron_glaive",
            new GlaiveItem(ToolMaterials.IRON, 5, -2.8F, new Item.Settings()));
    public static final Item GOLD_GLAIVE = registerItem("gold_glaive",
            new GlaiveItem(ToolMaterials.GOLD, 5, -2.8F, new Item.Settings()));
    public static final Item DIAMOND_GLAIVE = registerItem("diamond_glaive",
            new GlaiveItem(ToolMaterials.DIAMOND, 5, -2.8F, new Item.Settings()));
    public static final Item NETHERITE_GLAIVE = registerItem("netherite_glaive",
            new GlaiveItem(ToolMaterials.NETHERITE, 5, -2.8F, new Item.Settings().fireproof()));


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Sunbreaking.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Sunbreaking.LOGGER.info("Registering Mod Items for " + Sunbreaking.MOD_ID);
    }
}
