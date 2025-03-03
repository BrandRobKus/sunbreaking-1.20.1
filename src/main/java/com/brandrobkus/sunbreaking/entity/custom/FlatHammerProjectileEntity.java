package com.brandrobkus.sunbreaking.entity.custom;

import com.brandrobkus.sunbreaking.enchantment.ModEnchantmentHelper;
import com.brandrobkus.sunbreaking.util.ModEnchantments;
import com.brandrobkus.sunbreaking.util.ModEntities;
import com.brandrobkus.sunbreaking.util.ModItems;
import com.brandrobkus.sunbreaking.sound.ModSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FlatHammerProjectileEntity extends PersistentProjectileEntity {
    private static final TrackedData<Byte> LOYALTY = DataTracker.registerData(FlatHammerProjectileEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(FlatHammerProjectileEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private ItemStack hammerStack = new ItemStack(ModItems.FLAT_HAMMER);
    private boolean dealtDamage;
    public int returnTimer;

    private float rotationZ = 0.0f;
    private boolean hasLanded = false;

    public FlatHammerProjectileEntity(EntityType<? extends FlatHammerProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.hammerStack = new ItemStack(ModItems.FLAT_HAMMER);
    }

    public FlatHammerProjectileEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ModEntities.FLAT_HAMMER_PROJECTILE, owner, world);
        this.hammerStack = stack.copy();
        this.dataTracker.set(LOYALTY, (byte) ModEnchantmentHelper.getLoyalty(stack));
        this.dataTracker.set(ENCHANTED, stack.hasGlint());

        this.hasChar = hasChar(stack);
        this.hasEruption = hasEruption(stack);
    }

    private boolean hasEruption = false;
    private boolean hasChar = false;

    private boolean hasAshen(ItemStack stack) {
        return EnchantmentHelper.get(stack).containsKey(ModEnchantments.ASHEN);
    }

    private boolean hasChar(ItemStack stack) {
        return EnchantmentHelper.get(stack).containsKey(ModEnchantments.CHAR);
    }

    private boolean hasEruption(ItemStack stack) {
        return EnchantmentHelper.get(stack).containsKey(ModEnchantments.ERUPTION);
    }

    public ItemStack getHammerStack() {
        return hammerStack != null ? hammerStack : ItemStack.EMPTY;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(LOYALTY, (byte) 0);
        this.dataTracker.startTracking(ENCHANTED, false);
    }

    @Override
    public void tick() {
        super.tick();


        if (!hasLanded) {
            this.rotationZ += 20.0f;
            if (this.rotationZ > 360.0f) {
                this.rotationZ -= 360.0f;
            }
        }

        Entity entity = this.getOwner();
        int i = this.dataTracker.get(LOYALTY);
        if (i > 0 && (this.dealtDamage || this.isNoClip()) && entity != null) {
            if (!this.isOwnerAlive()) {
                if (!this.getWorld().isClient && this.pickupType == PickupPermission.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1F);
                }

                this.discard();
            } else {
                this.setNoClip(true);
                Vec3d vec3d = entity.getEyePos().subtract(this.getPos());
                this.setPos(this.getX(), this.getY() + vec3d.y * 0.015 * (double) i, this.getZ());
                if (this.getWorld().isClient) {
                    this.lastRenderY = this.getY();
                }

                double d = 0.05 * (double) i;
                this.setVelocity(this.getVelocity().multiply(0.95).add(vec3d.normalize().multiply(d)));
                if (this.returnTimer == 0) {
                    this.playSound(ModSounds.HAMMER_RETURN, 10.0F, 1.0F);
                }

                this.returnTimer++;
            }
        }
            if (this.inGroundTime > 4 && !hasLanded) {
                this.triggerExplosion();

                if (this.dataTracker.get(LOYALTY) == 0) {
                    this.rotationZ = 30.0f; // Set rotation to 150 degrees
                }
                this.setVelocity(0, 0, 0); // Stop movement
                this.hasLanded = true;
            }
            if (this.inGroundTime > 4 && !this.dealtDamage) {
                this.dealtDamage = true;
            }

        if (this.getWorld().isClient && !hasLanded) {
            double speedX = 0.0;
            double speedY = 0.0;
            double speedZ = 0.0;
            double sizeFactor = 2.0;

            this.getWorld().addParticle(ParticleTypes.WHITE_ASH,
                    this.getX(), this.getY(), this.getZ(),
                    speedX * sizeFactor, speedY * sizeFactor, speedZ * sizeFactor);
            this.getWorld().addParticle(ParticleTypes.SMALL_FLAME,
                    this.getX(), this.getY(), this.getZ(),
                    speedX * sizeFactor, speedY * sizeFactor, speedZ * sizeFactor);
            this.getWorld().addParticle(ParticleTypes.SMOKE,
                    this.getX(), this.getY(), this.getZ(),
                    speedX * sizeFactor, speedY * sizeFactor, speedZ * sizeFactor);
            this.getWorld().addParticle(ParticleTypes.LARGE_SMOKE,
                    this.getX(), this.getY(), this.getZ(),
                    speedX * sizeFactor, speedY * sizeFactor, speedZ * sizeFactor);
            this.getWorld().addParticle(ParticleTypes.LAVA,
                    this.getX(), this.getY(), this.getZ(),
                    speedX * sizeFactor, speedY * sizeFactor, speedZ * sizeFactor);
        } else {
            double speedX = 0.0;
            double speedY = 0.0;
            double speedZ = 0.0;
            double sizeFactor = 2.0;

            this.getWorld().addParticle(ParticleTypes.SMOKE,
                    this.getX(), this.getY(), this.getZ(),
                    speedX * sizeFactor, speedY * sizeFactor, speedZ * sizeFactor);
            this.getWorld().addParticle(ParticleTypes.FLAME,
                    this.getX(), this.getY(), this.getZ(),
                    speedX * sizeFactor, speedY * sizeFactor, speedZ * sizeFactor);
        }

            super.tick();
        }

    private boolean isOwnerAlive() {
        Entity entity = this.getOwner();
        return entity != null && entity.isAlive() && (!(entity instanceof ServerPlayerEntity) || !entity.isSpectator());
    }

    @Override
    protected ItemStack asItemStack() {
        return this.hammerStack.copy();
    }

    public boolean isEnchanted() {
        return this.dataTracker.get(ENCHANTED);
    }

    @Nullable
    @Override
    protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        float f = 8.0F;

        // Additional damage logic if needed
        if (entity instanceof LivingEntity livingEntity) {
            f += EnchantmentHelper.getAttackDamage(this.hammerStack, livingEntity.getGroup());
        }

        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.getDamageSources().trident(this, entity2 == null ? this : entity2);
        this.dealtDamage = true;
        SoundEvent soundEvent = ModSounds.HAMMER_HIT;
        if (entity.damage(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity livingEntity2) {
                if (entity2 instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(livingEntity2, entity2);
                    EnchantmentHelper.onTargetDamaged((LivingEntity) entity2, livingEntity2);
                }

                this.onHit(livingEntity2);
            }
        }
        this.triggerExplosion();
        this.setVelocity(this.getVelocity().multiply(-0.01, -0.1, -0.01));
        this.playSound(soundEvent, 0.7F, 1.0F);
    }

    private void triggerExplosion() {
        System.out.println("Triggering explosion!");

        if (!this.getWorld().isClient) {
            BlockPos impactPos = this.getBlockPos();
            BlockPos adjustedPos = impactPos.up(1);
            
            if (this.getOwner() instanceof PlayerEntity player) {
                ItemStack stack = this.hammerStack;

                System.out.println("Player's main hand item: " + stack.getItem().getName().getString());

                boolean hasAshen = hasAshen(stack);

                System.out.println("Hammer has ASHEN enchantment: " + hasAshen);

                float blastRadius = 2.0F;

                if (hasAshen) {
                    blastRadius = 4.0F;
                }

                this.hasEruption = hasEruption(stack);

                World.ExplosionSourceType explosionSourceType = this.hasEruption ?
                        World.ExplosionSourceType.BLOCK : World.ExplosionSourceType.NONE;

                boolean allowBlockDestruction = this.hasChar;

                System.out.println("Hammer has Char effect: " + (this.hasChar ? "Allowing block destruction" : "Not allowing block destruction"));

                this.getWorld().createExplosion(
                        this,
                        adjustedPos.getX(),
                        adjustedPos.getY(),
                        adjustedPos.getZ(),
                        blastRadius,
                        allowBlockDestruction,
                        explosionSourceType
                );

                // Debug: Explosion triggered log
                System.out.println("Explosion triggered with radius: " + blastRadius + ", block destruction: " + allowBlockDestruction);
            }
        }
    }


    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || this.isNoClip() && this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }

    @Override
    protected SoundEvent getHitSound() {
        return ModSounds.HAMMER_HIT;
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (this.isOwner(player) || this.getOwner() == null) {
            super.onPlayerCollision(player);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("HammerOfSol", NbtCompound.COMPOUND_TYPE)) {
            this.hammerStack = ItemStack.fromNbt(nbt.getCompound("HammerOfSol"));
        }

        this.dealtDamage = nbt.getBoolean("DealtDamage");
        this.rotationZ = nbt.getFloat("RotationZ");
        this.hasLanded = nbt.getBoolean("HasLanded");

        if (nbt.contains("LOYALTY", NbtCompound.BYTE_TYPE)) {
            this.dataTracker.set(LOYALTY, nbt.getByte("LOYALTY"));
        } else {
            this.dataTracker.set(LOYALTY, (byte) 0);
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("HammerOfSol", this.hammerStack.writeNbt(new NbtCompound()));
        nbt.putBoolean("DealtDamage", this.dealtDamage);

        nbt.putFloat("RotationZ", this.rotationZ);
        nbt.putBoolean("HasLanded", this.hasLanded);

        nbt.putByte("LOYALTY", this.dataTracker.get(LOYALTY));
    }


    @Override
    public void age() {
        int i = this.dataTracker.get(LOYALTY);
        if (this.pickupType != PickupPermission.ALLOWED || i <= 0) {
            super.age();
        }
    }

    @Override
    protected float getDragInWater() {
        return 0.75F;
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    // Add getter for rotationZ
    public float getRotationZ() {
        return this.rotationZ;
    }
}
