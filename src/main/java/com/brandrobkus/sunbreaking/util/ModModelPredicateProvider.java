package com.brandrobkus.sunbreaking.util;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModModelPredicateProvider {

    public static void registerShadowShotBow (Item bow){

        FabricModelPredicateProviderRegistry.register(ModItems.SHADOWSHOT_BOW, new Identifier("pull"), (stack, world, entity, seed) -> {
        if (entity == null) {
            return 0.0F;
        } else {
            return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
        }
    });
        FabricModelPredicateProviderRegistry.register(
                ModItems.SHADOWSHOT_BOW,
                new Identifier("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
    }
    public static void registerModModel(){
        registerShadowShotBow(ModItems.SHADOWSHOT_BOW);
    }
}
