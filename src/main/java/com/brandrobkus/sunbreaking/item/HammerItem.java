package com.brandrobkus.sunbreaking.item;

import com.brandrobkus.sunbreaking.Sunbreaking;
import com.brandrobkus.sunbreaking.entity.custom.HammerProjectileEntity;
import com.brandrobkus.sunbreaking.sound.ModSounds;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HammerItem extends Item {
    public static final int USE_THRESHOLD = 10;
    public static final float ATTACK_DAMAGE = 10.0F;
    public static final float PROJECTILE_SPEED = 2.5F;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public HammerItem(Item.Settings settings) {
        super(settings.maxDamage(250)); // Set max durability here
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

                    // Debug: Log entity creation
                    System.out.println("Creating HammerProjectileEntity...");

                    HammerProjectileEntity hammerEntity = new HammerProjectileEntity(world, playerEntity, stack);
                    hammerEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, PROJECTILE_SPEED, 1.0F);

                    // Debug: Log entity details
                    System.out.println("HammerProjectileEntity created with velocity: " + hammerEntity.getVelocity());

                    if (playerEntity.getAbilities().creativeMode) {
                        hammerEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    }

                    // Spawn the entity
                    world.spawnEntity(hammerEntity);

                    // Debug: Log entity spawning
                    System.out.println("HammerProjectileEntity spawned.");


                    world.playSoundFromEntity(null, hammerEntity, ModSounds.HAMMER_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    Sunbreaking.LOGGER.info("Playing sound: " + ModSounds.HAMMER_THROW.getId());




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
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}
