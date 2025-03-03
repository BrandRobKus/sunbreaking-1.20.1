package com.brandrobkus.sunbreaking.entity.custom;

import com.brandrobkus.sunbreaking.util.ModEnchantments;
import com.brandrobkus.sunbreaking.sound.ModSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.List;
import java.util.UUID;

public class ShadowshotNodeEntity extends Entity {
    private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(ShadowshotNodeEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int timeAlive = 0;
    public int timeToDie = 260;

    private float getNodeRadius() {
        return hasDilation() ? 9.0f : 6.0f;
    }

    private UUID ownerUUID;

    public ShadowshotNodeEntity(EntityType<? extends ShadowshotNodeEntity> type, World world) {
        super(type, world);
    }

    private ItemStack bowStack = ItemStack.EMPTY;

    public void setBowStack(ItemStack stack) {
        this.bowStack = stack.copy();
    }

    private boolean hasDilation() {
        return EnchantmentHelper.get(bowStack).containsKey(ModEnchantments.DILATION);
    }


    private void playSpawnSound() {
        getWorld().playSound(
                null,
                getBlockPos(), ModSounds.SHADOWSHOT_NODE, SoundCategory.HOSTILE, 15.0F, 1.0F
        );
    }

    public void setOwner(PlayerEntity owner) {
        this.ownerUUID = owner.getUuid();
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }

    private void applyEffectsToNearbyEntities() {
        List<LivingEntity> nearbyEntities = getWorld().getEntitiesByClass(
                LivingEntity.class,
                new Box(getX() - getNodeRadius(), getY() - getNodeRadius(), getZ() - getNodeRadius(),
                        getX() + getNodeRadius(), getY() + getNodeRadius(), getZ() + getNodeRadius()),
                entity -> true
        );

        for (LivingEntity entity : nearbyEntities) {
            if (ownerUUID != null && entity.getUuid().equals(ownerUUID)) {
                continue;
            }

            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 3, 2, true, true, true));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 3, 0, true, true, true));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 3, 0, true, true, true));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            spawnParticles();
            rotateNode();
        } else {
            if (timeAlive >= 20) {
                applyEffectsToNearbyEntities();
            }
        }

        timeAlive++;
        if (timeAlive == 1) {
            playSpawnSound();
        }
        if (timeAlive >= timeToDie) {
            this.remove(Entity.RemovalReason.DISCARDED);
        }
    }

    public float getScale() {
        double scale = 0.0;

        if (timeAlive >= 0 && timeAlive <= 10) {
            scale = (timeAlive) / 10.0;

        } else if (timeAlive >= 10 && timeAlive <= 15) {
            scale = 1.0 - ((timeAlive - 10) / 5.0) * 0.25;

        } else if (timeAlive >= 15 && timeAlive <= 20) {
            scale = 0.75 + ((timeAlive - 15) / 5.0) * 0.75;

        } else if (timeAlive >= 20 && timeAlive <= 40) {
            scale = 1.5 - ((timeAlive - 20) / 20.0) * 0.5;

        } else if (timeAlive >= 40 && timeAlive <= 200) {
            scale = 1.0 + 0.25 * Math.sin(((timeAlive - 40) / 20.0) * Math.PI);

        } else if (timeAlive >= 200 && timeAlive <= 255) {
            int x = timeAlive - 200;
            scale = 1.0 + 0.000625 * x * x;

        } else if (timeAlive >= 255 && timeAlive <= 260) {
            int x = timeAlive - 255;
            double maxScaleAt240 = 1.0 + 0.0025 * 40 * 40; // Scale at 240 ticks
            scale = maxScaleAt240 - (maxScaleAt240 * x / 5.0); // Shrink linearly to 0
        }

        return (float) scale;
    }

    private void spawnParticles() {
        World world = this.getWorld();
        if (world.isClient) {
            float maxRadius = getNodeRadius();

            double maxSmallRadius = 0.5;
            int particleCount = 100;
            int smallParticleCount = 20;

            double expansionProgress;

            if (timeAlive < 15) {
                expansionProgress = 0.0;
            } else if (timeAlive < 22) {
                expansionProgress = Math.sqrt(timeAlive - 15) / 2.62;
            } else if (timeAlive > 250) {
                expansionProgress = Math.sqrt(258 - timeAlive) / 2.82843;
            } else {
                expansionProgress = 1.0;
            }

            double currentRadius = maxRadius * expansionProgress;
            double smallSphereRadius = maxSmallRadius * expansionProgress;

            for (int i = 0; i < particleCount; i++) {
                double theta = Math.random() * 2 * Math.PI;
                double u = Math.random() * 2 - 1;
                double phi = Math.acos(u);

                double x = getX() + currentRadius * Math.sin(phi) * Math.cos(theta);
                double y = getY() + currentRadius * Math.cos(phi);
                double z = getZ() + currentRadius * Math.sin(phi) * Math.sin(theta);

                world.addParticle(new DustParticleEffect(new Vector3f(0.7f, 0.2f, 0.7f), 1.2f), x, y, z, 0, 0, 0);
            }

            for (int i = 0; i < smallParticleCount / 5; i++) {
                double theta = Math.random() * 2 * Math.PI;
                double u = Math.random() * 2 - 1;
                double phi = Math.acos(u);

                double x = getX() + smallSphereRadius * Math.sin(phi) * Math.cos(theta);
                double y = getY() + smallSphereRadius * Math.cos(phi);
                double z = getZ() + smallSphereRadius * Math.sin(phi) * Math.sin(theta);

                world.addParticle(new DustParticleEffect(new Vector3f(0.4f, 0.1f, 0.6f), 0.7f), x, y, z, 0, 0, 0);
            }
        }
    }

    private void rotateNode() {
        float scale = getScale();
        float rotationSpeed = 10.0F / (scale * scale + 0.000001F);

        float newYaw = this.getYaw() + rotationSpeed;
        this.setYaw(newYaw);
        this.setRotation(newYaw, this.getPitch());

        float newPitch = this.getPitch() + rotationSpeed;
        this.setPitch(newPitch);
        this.setRotation(newPitch, this.getPitch());
    }

}