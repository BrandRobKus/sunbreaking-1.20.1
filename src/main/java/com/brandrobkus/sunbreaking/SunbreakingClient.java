package com.brandrobkus.sunbreaking;

import com.brandrobkus.sunbreaking.client.renderer.*;
import com.brandrobkus.sunbreaking.util.ModEntities;
import com.brandrobkus.sunbreaking.client.renderer.ModModelLayers;
import com.brandrobkus.sunbreaking.client.renderer.ShadowshotNodeModel;
import com.brandrobkus.sunbreaking.client.renderer.ShadowshotNodeRenderer;
import com.brandrobkus.sunbreaking.client.renderer.ShadowshotTetherRenderer;
import com.brandrobkus.sunbreaking.util.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class SunbreakingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.HAMMER_PROJECTILE, HammerProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.BLAZING_HAMMER_PROJECTILE, BlazingHammerProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.HELLBENT_HAMMER_PROJECTILE, HellbentHammerProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.FLAT_HAMMER_PROJECTILE, FlatHammerProjectileRenderer::new);

        EntityRendererRegistry.register(ModEntities.IRON_HAMMER_PROJECTILE, IronHammerProjectileRenderer::new);

        EntityRendererRegistry.register(ModEntities.SHADOWSHOT_ARROW, ShadowshotArrowEntityRenderer::new);

        EntityRendererRegistry.register(ModEntities.SHADOWSHOT_NODE, ShadowshotNodeRenderer::new);

        EntityRendererRegistry.register(ModEntities.SHADOWSHOT_TETHER, ShadowshotTetherRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.NODE, ShadowshotNodeModel::getTexturedModelData);
        registerItemModels();
        Sunbreaking.LOGGER.info("Client initialization complete");

        EntityRendererRegistry.register(ModEntities.STORM_CLOUD, StormCloudRenderer::new);

    }
    private void registerItemModels() {
        ModelPredicateProviderRegistry.register(ModItems.SOLAR_LIGHT, new Identifier("animation"), (stack, world, entity, seed) -> {
            return stack.getOrCreateNbt().getInt("animation_frame");
        });
    }
}
