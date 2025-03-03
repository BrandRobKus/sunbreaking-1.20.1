package com.brandrobkus.sunbreaking.datagen;

import com.brandrobkus.sunbreaking.util.ModItems;
import com.brandrobkus.sunbreaking.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.HAMMER)
                .add(ModItems.HAMMER_OF_SOL)
                .add(ModItems.BLAZING_HAMMER)
                .add(ModItems.HELLBENT_HAMMER)
                .add(ModItems.FLAT_HAMMER)
        ;
        getOrCreateTagBuilder(ModTags.Items.ARROWS)
                .add(ModItems.SHADOWSHOT_ARROW)
                .add(Items.ARROW)
                .add(Items.SPECTRAL_ARROW)
                .add(Items.TIPPED_ARROW)
        ;
        getOrCreateTagBuilder(ModTags.Items.GLAIVES)
                .add(ModItems.WOODEN_GLAIVE)
                .add(ModItems.STONE_GLAIVE)
                .add(ModItems.IRON_GLAIVE)
                .add(ModItems.GOLD_GLAIVE)
                .add(ModItems.DIAMOND_GLAIVE)
                .add(ModItems.NETHERITE_GLAIVE)
        ;
    }
}
