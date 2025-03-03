package com.brandrobkus.sunbreaking.client.renderer;

import com.brandrobkus.sunbreaking.entity.custom.ShadowshotTetherEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class ShadowshotTetherRenderer extends EntityRenderer<ShadowshotTetherEntity> {
    private static final Identifier BLANK_TEXTURE = new Identifier("textures/misc/blank.png");

    public ShadowshotTetherRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(ShadowshotTetherEntity entity, float yaw, float tickDelta, MatrixStack matrices, net.minecraft.client.render.VertexConsumerProvider vertexConsumers, int light) {
        if (entity.getTarget() == null) {
            return;
        }

        Vec3d startPos = entity.getPos();
        Vec3d endPos = entity.getTarget().getPos().add(0, entity.getTarget().getHeight() / 2.0, 0);
        spawnParticles(entity.getWorld(), startPos, endPos);
    }

    private void spawnParticles(World world, Vec3d start, Vec3d end) {
        if (!world.isClient) return; // Ensure this is client-side

        Vec3d difference = end.subtract(start);
        int particleCount = 20;
        DustParticleEffect particleEffect = new DustParticleEffect(new Vector3f(0.2f, 0.2f, 0.5f), 1.0f);

        System.out.println("Spawning particles from " + start + " to " + end);

        for (int i = 0; i <= particleCount; i++) {
            double progress = i / (double) particleCount;
            Vec3d pos = start.add(difference.multiply(progress));

            // Test with END_ROD to verify particles are rendering
            world.addParticle(ParticleTypes.END_ROD, pos.x, pos.y, pos.z, 0, 0, 0);

            // Uncomment this once confirmed working
            // world.addParticle(particleEffect, pos.x, pos.y, pos.z, 0, 0, 0);
        }
    }


    @Override
    public Identifier getTexture(ShadowshotTetherEntity entity) {
        return BLANK_TEXTURE;
    }
}
