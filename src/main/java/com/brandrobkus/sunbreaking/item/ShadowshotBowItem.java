package com.brandrobkus.sunbreaking.item;

import com.brandrobkus.sunbreaking.entity.custom.ShadowshotArrowEntity;
import com.brandrobkus.sunbreaking.util.ModItems;
import com.brandrobkus.sunbreaking.util.ModTags;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class ShadowshotBowItem extends BowItem {
    private static final int COOLDOWN_TIME = 250;

    public ShadowshotBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return stack -> stack.isIn(ModTags.Items.ARROWS);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemStack = playerEntity.getProjectileType(stack);
            boolean isShadowshotArrow = itemStack.getItem() == ModItems.SHADOWSHOT_ARROW;

            if (isShadowshotArrow && playerEntity.getItemCooldownManager().isCoolingDown(this)) {
                if (!world.isClient) {
                    playerEntity.sendMessage(Text.literal("This bow is on cooldown!"), true);
                }
                return;
            }

            if (!itemStack.isEmpty() || bl) {
                if (itemStack.isEmpty()) {
                    itemStack = new ItemStack(Items.ARROW);
                }

                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                float f = getPullProgress(i);
                if (!(f < 0.1)) {
                    boolean bl2 = bl && itemStack.isOf(Items.ARROW);
                    if (!world.isClient) {
                        ArrowItem arrowItem = (ArrowItem) (itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);

                        if (persistentProjectileEntity instanceof ShadowshotArrowEntity shadowshotArrow) {
                            shadowshotArrow.setBowStack(stack);
                        }

                        if (isShadowshotArrow) {
                            persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 4.75F, 0.0F);
                        } else {
                            persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 4.0F, 0.5F);
                        }
                        if (f == 1.0F) {
                            persistentProjectileEntity.setCritical(true);
                        }

                        int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double) j * 0.5 + 0.5);
                        }

                        int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            persistentProjectileEntity.setPunch(k);
                        }

                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                            persistentProjectileEntity.setOnFireFor(100);
                        }

                        stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                        if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW) || itemStack.isOf(ModItems.SHADOWSHOT_ARROW))) {
                            persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        }

                        world.spawnEntity(persistentProjectileEntity);
                    }

                    world.playSound(
                            null,
                            playerEntity.getX(),
                            playerEntity.getY(),
                            playerEntity.getZ(),
                            SoundEvents.ENTITY_ARROW_SHOOT,
                            SoundCategory.PLAYERS,
                            1.0F,
                            1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );

                    if (!bl2 && !playerEntity.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                        if (itemStack.isEmpty()) {
                            playerEntity.getInventory().removeOne(itemStack);
                        }
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));

                    if (isShadowshotArrow) {
                        if (!bl2 && !playerEntity.getAbilities().creativeMode) {
                            playerEntity.getItemCooldownManager().set(this, COOLDOWN_TIME);
                        }
                    }
                }
            }
        }
    }

}
