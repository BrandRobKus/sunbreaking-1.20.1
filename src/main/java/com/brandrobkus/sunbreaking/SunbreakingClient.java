package com.brandrobkus.sunbreaking;

import com.brandrobkus.sunbreaking.client.renderer.HammerProjectileRenderer;
import com.brandrobkus.sunbreaking.entity.ModEntities;
import com.brandrobkus.sunbreaking.entity.custom.HammerProjectileEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.TridentEntityRenderer;

public class SunbreakingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.HAMMER_PROJECTILE, HammerProjectileRenderer::new);

    }
}
