package com.brandrobkus.sunbreaking;

import com.brandrobkus.sunbreaking.client.renderer.HammerProjectileRenderer;
import com.brandrobkus.sunbreaking.client.renderer.IronHammerProjectileRenderer;
import com.brandrobkus.sunbreaking.entity.ModEntities;
import com.brandrobkus.sunbreaking.entity.custom.HammerProjectileEntity;
import com.brandrobkus.sunbreaking.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.TridentEntityRenderer;
import net.minecraft.util.Identifier;

public class SunbreakingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.HAMMER_PROJECTILE, HammerProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.IRON_HAMMER_PROJECTILE, IronHammerProjectileRenderer::new);
        registerItemModels();

    }
    private void registerItemModels() {
        ModelPredicateProviderRegistry.register(ModItems.SOLAR_LIGHT, new Identifier("animation"), (stack, world, entity, seed) -> {
            return stack.getOrCreateNbt().getInt("animation_frame"); // Use this value for dynamic frame switching
        });
    }
}
