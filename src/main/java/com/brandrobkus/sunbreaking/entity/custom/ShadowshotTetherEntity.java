package com.brandrobkus.sunbreaking.entity.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ShadowshotTetherEntity extends Entity {
    private LivingEntity targetEntity;
    private Vec3d startPosition;

    public ShadowshotTetherEntity(EntityType<? extends ShadowshotTetherEntity> type, World world) {
        super(type, world);
    }

    public ShadowshotTetherEntity(EntityType<? extends ShadowshotTetherEntity> type, World world, Vec3d startPos, LivingEntity target) {
        super(type, world);
        this.startPosition = startPos;
        this.targetEntity = target;
        this.setPosition(startPos);
    }

    public LivingEntity getTarget() {
        return this.targetEntity;
    }

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            System.out.println("ShadowshotTetherEntity exists at " + this.getPos());
        }

        if (targetEntity == null || !targetEntity.isAlive()) {
            this.remove(RemovalReason.DISCARDED);
            return;
        }

        Vec3d targetPos = targetEntity.getPos().add(0, 1, 0);
        Vec3d currentPos = this.getPos();
        Vec3d direction = targetPos.subtract(currentPos).normalize().multiply(0.3);

        this.setPosition(currentPos.add(direction));
        startPosition = this.getPos();

        if (currentPos.distanceTo(targetPos) < 0.5) {
            this.remove(Entity.RemovalReason.DISCARDED);
        }

        if (this.getWorld().isClient) {
            spawnParticleTrail(currentPos, targetPos); // Ensure it runs only on the client
        }

    }


    private void spawnParticle(Vec3d position) {
        this.getWorld().addParticle(ParticleTypes.END_ROD, position.x, position.y, position.z, 0, 0, 0);
    }


    private void spawnParticleTrail(Vec3d start, Vec3d end) {
        Vec3d direction = end.subtract(start);
        double distance = direction.length();
        direction = direction.normalize();

        double stepSize = 0.1; // Smaller step for more particles
        for (double i = 0; i < distance; i += stepSize) {
            Vec3d particlePos = start.add(direction.multiply(i));
            spawnParticle(particlePos);
        }
    }

}
