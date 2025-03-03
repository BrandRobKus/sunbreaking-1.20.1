package com.brandrobkus.sunbreaking.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.UUID;

public class StormCloudEntity extends ProjectileEntity {
    private int timeAlive = 0;
    private int lastLightningStrike = 0;
    private UUID ownerUUID;

    public StormCloudEntity(EntityType<? extends StormCloudEntity> entityType, World world) {
        super(entityType, world);
    }

    public void setOwner(PlayerEntity owner) {
        this.ownerUUID = owner.getUuid(); // Store the player's unique ID
    }

    @Override
    protected void initDataTracker() {}

    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        timeAlive++;

        if (timeAlive == 1) {
            Vec3d velocity = new Vec3d(0, 2, 0);
            setVelocity(velocity);
        } else {
            Vec3d currentVelocity = getVelocity();
            Vec3d newVelocity = currentVelocity.multiply(0.95);
            setVelocity(newVelocity);
            if (newVelocity.length() < 0.02) {
                setVelocity(Vec3d.ZERO);
            }
        }

        if (!getWorld().isClient) {
            this.syncVelocity();
        }

        if (!getWorld().isClient && timeAlive - lastLightningStrike >= 30) {
            lastLightningStrike = timeAlive;

            Box detectionBox = new Box(getX() - 5, getY(), getZ() - 5, getX() + 5, getY() - 20, getZ() + 5);
            List<LivingEntity> entities = getWorld().getEntitiesByClass(LivingEntity.class, detectionBox, entity ->
                    ownerUUID == null || !entity.getUuid().equals(ownerUUID)
            );

            for (LivingEntity entity : entities) {
                if (getWorld() instanceof ServerWorld serverWorld) {
                    Entity lightning = EntityType.LIGHTNING_BOLT.create(serverWorld);
                    if (lightning != null) {
                        lightning.refreshPositionAfterTeleport(entity.getX(), entity.getY(), entity.getZ());
                        serverWorld.spawnEntity(lightning);
                        entity.emitGameEvent(GameEvent.LIGHTNING_STRIKE);
                    }
                }
            }
        }

        if (getWorld().isClient) {
            for (int i = 0; i < 10; i++) {
                double offsetX = random.nextGaussian();
                double offsetZ = random.nextGaussian();
                double offsetY = random.nextGaussian() * 0.5;
                getWorld().addParticle(ParticleTypes.SMOKE, getX() + offsetX, getY() + offsetY, getZ() + offsetZ, 0, 0, 0);
                getWorld().addParticle(ParticleTypes.CLOUD, getX() + offsetX, getY() + offsetY, getZ() + offsetZ, 0, 0, 0);
            }
        }

        if (timeAlive >= 500) {
            this.remove(Entity.RemovalReason.DISCARDED);
        }
    }

    private void syncVelocity() {
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            for (ServerPlayerEntity player : serverWorld.getPlayers()) {
                player.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(this)); // Sync velocity
            }
        }
    }

    // Ensure velocity persists after saving/loading
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putDouble("VelX", getVelocity().x);
        nbt.putDouble("VelY", getVelocity().y);
        nbt.putDouble("VelZ", getVelocity().z);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        setVelocity(new Vec3d(nbt.getDouble("VelX"), nbt.getDouble("VelY"), nbt.getDouble("VelZ")));
    }
}
