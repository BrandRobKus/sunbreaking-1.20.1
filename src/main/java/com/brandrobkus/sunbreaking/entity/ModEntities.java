package com.brandrobkus.sunbreaking.entity;

import com.brandrobkus.sunbreaking.Sunbreaking;
import com.brandrobkus.sunbreaking.entity.custom.HammerProjectileEntity;
import com.brandrobkus.sunbreaking.entity.custom.IronHammerProjectileEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<HammerProjectileEntity> HAMMER_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("modid", "hammer_projectile"),
            FabricEntityTypeBuilder.<HammerProjectileEntity>create(SpawnGroup.MISC, HammerProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)) // Set appropriate dimensions
                    .trackRangeBlocks(80)
                    .trackedUpdateRate(3)
                    .build()
    );


    public static final EntityType<IronHammerProjectileEntity> IRON_HAMMER_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Sunbreaking.MOD_ID, "iron_hammer_projectile"),
            FabricEntityTypeBuilder.<IronHammerProjectileEntity>create(SpawnGroup.MISC, IronHammerProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.125f, 0.125f))
                    .trackRangeBlocks(80)
                    .trackedUpdateRate(3)
                    .build()
    );

    public static void registerModEntities() {
        System.out.println("Registering entities for Sunbreaking Mod!");
        // Additional registrations can go here
    }
}
