package com.brandrobkus.sunbreaking.item;

import com.brandrobkus.sunbreaking.util.ModEntities;
import com.brandrobkus.sunbreaking.entity.custom.StormCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BondItem extends Item {
    public BondItem(Settings settings) {
        super(settings);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!world.isClient) {
            // Get the velocity vector based on the player's rotation and apply it.
            Vec3d velocity = user.getRotationVec(1.0F).multiply(1.5); // The multiplier controls the speed.

            StormCloudEntity stormCloudEntity = new StormCloudEntity(ModEntities.STORM_CLOUD, world);
            stormCloudEntity.setOwner(user); // Set the owner of the cloud
            stormCloudEntity.setPosition(user.getX(), user.getEyeY(), user.getZ());
            stormCloudEntity.setVelocity(velocity); // Apply the velocity right away

            world.spawnEntity(stormCloudEntity); // Spawn the cloud in the world


        if (!user.getAbilities().creativeMode) {
                user.getItemCooldownManager().set(this, 100);
            }
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

}
