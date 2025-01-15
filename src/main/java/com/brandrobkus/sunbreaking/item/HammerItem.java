package com.brandrobkus.sunbreaking.item;

import com.brandrobkus.sunbreaking.Sunbreaking;
import com.brandrobkus.sunbreaking.enchantment.ModEnchantments;
import com.brandrobkus.sunbreaking.entity.custom.HammerProjectileEntity;
import com.brandrobkus.sunbreaking.sound.ModSounds;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class HammerItem extends TridentItem {
    public static final int USE_THRESHOLD = 10;
    public static final float PROJECTILE_SPEED = 2.5F;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public HammerItem(Item.Settings settings) {
        super(settings.maxDamage(350)); // Set max durability here
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", 8.0, EntityAttributeModifier.Operation.ADDITION)
        );
        builder.put(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Tool modifier", -2.9F, EntityAttributeModifier.Operation.ADDITION)
        );
        this.attributeModifiers = builder.build();


    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            if (i >= USE_THRESHOLD) {
                if (!world.isClient) {
                    stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(user.getActiveHand()));

                    HammerProjectileEntity hammerEntity = new HammerProjectileEntity(world, playerEntity, stack);

                    // Determine projectile speed based on Bulk enchantment
                    float velocity = hasBulk(stack) ? PROJECTILE_SPEED / 1.33F : PROJECTILE_SPEED;

                    // Debug: Log velocity adjustment
                    System.out.println("HammerProjectileEntity velocity: " + velocity);

                    hammerEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, velocity, 1.0F);

                    if (playerEntity.getAbilities().creativeMode) {
                        hammerEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    }

                    // Spawn the entity
                    world.spawnEntity(hammerEntity);

                    world.playSoundFromEntity(null, hammerEntity, ModSounds.HAMMER_THROW, SoundCategory.PLAYERS,
                            2.0F, 1.0F);

                    if (!playerEntity.getAbilities().creativeMode) {
                        playerEntity.getInventory().removeOne(stack);
                    }
                }

                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        System.out.println("Hammer durability: " + (itemStack.getMaxDamage() - itemStack.getDamage()));
        if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
            System.out.println("Hammer is broken!");
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if ((double)state.getHardness(world, pos) != 0.0) {
            stack.damage(2, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        return true;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();

            if (hasBulk(stack)) {
                // Bulk-specific attributes
                builder.put(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                ATTACK_DAMAGE_MODIFIER_ID, // Same UUID ensures replacement
                                "Bulk Tool modifier",
                                12.0, // Replace with 12.0 (8 base + 4 Bulk)
                                EntityAttributeModifier.Operation.ADDITION
                        )
                );
                builder.put(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                ATTACK_SPEED_MODIFIER_ID, // Same UUID ensures replacement
                                "Bulk Tool modifier",
                                -3.4F, // Replace with -3.4 (-2.9 base - 0.5 Bulk)
                                EntityAttributeModifier.Operation.ADDITION
                        )
                );
            } else {
                // Default attributes
                builder.put(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                ATTACK_DAMAGE_MODIFIER_ID,
                                "Tool modifier",
                                8.0,
                                EntityAttributeModifier.Operation.ADDITION
                        )
                );
                builder.put(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                ATTACK_SPEED_MODIFIER_ID,
                                "Tool modifier",
                                -2.9F,
                                EntityAttributeModifier.Operation.ADDITION
                        )
                );
            }

            return builder.build();
        }

        return super.getAttributeModifiers(stack, slot);
    }

    private boolean hasBulk(ItemStack stack) {
        return EnchantmentHelper.getLevel(ModEnchantments.BULK, stack) > 0;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }
}
