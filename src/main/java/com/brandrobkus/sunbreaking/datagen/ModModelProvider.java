package com.brandrobkus.sunbreaking.datagen;


import com.brandrobkus.sunbreaking.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SUNBREAKERS_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SUNBREAKERS_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SUNBREAKERS_GREAVES));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SUNBREAKERS_BOOTS));
    }
}
