package com.brandrobkus.sunbreaking.util;

import com.brandrobkus.sunbreaking.Sunbreaking;
import com.brandrobkus.sunbreaking.entity.custom.*;
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
                    .dimensions(EntityDimensions.fixed(0.75f, 0.75f))
                    .trackRangeBlocks(80)
                    .trackedUpdateRate(3)
                    .build()
    );

    public static final EntityType<BlazingHammerProjectileEntity> BLAZING_HAMMER_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("modid", "blazing_hammer_projectile"),
            FabricEntityTypeBuilder.<BlazingHammerProjectileEntity>create(SpawnGroup.MISC, BlazingHammerProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 0.75f))
                    .trackRangeBlocks(80)
                    .trackedUpdateRate(3)
                    .build()
    );
    public static final EntityType<HellbentHammerProjectileEntity> HELLBENT_HAMMER_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("modid", "hellbent_hammer_projectile"),
            FabricEntityTypeBuilder.<HellbentHammerProjectileEntity>create(SpawnGroup.MISC, HellbentHammerProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 0.75f))
                    .trackRangeBlocks(80)
                    .trackedUpdateRate(3)
                    .build()
    );
    public static final EntityType<FlatHammerProjectileEntity> FLAT_HAMMER_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("modid", "2d_hammer_projectile"),
            FabricEntityTypeBuilder.<FlatHammerProjectileEntity>create(SpawnGroup.MISC, FlatHammerProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 0.75f))
                    .trackRangeBlocks(80)
                    .trackedUpdateRate(3)
                    .build()
    );

    public static final EntityType<IronHammerProjectileEntity> IRON_HAMMER_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Sunbreaking.MOD_ID, "iron_hammer_projectile"),
            FabricEntityTypeBuilder.<IronHammerProjectileEntity>create(SpawnGroup.MISC, IronHammerProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f))
                    .trackRangeBlocks(80)
                    .trackedUpdateRate(3)
                    .build()
    );

    public static final EntityType<ShadowshotArrowEntity> SHADOWSHOT_ARROW = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Sunbreaking.MOD_ID, "shadowshot_arrow"),
            FabricEntityTypeBuilder.<ShadowshotArrowEntity>create(SpawnGroup.MISC, ShadowshotArrowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                    .trackRangeBlocks(80)
                    .trackedUpdateRate(3)
                    .build()
    );

    public static final EntityType<ShadowshotNodeEntity> SHADOWSHOT_NODE = Registry.register
            (Registries.ENTITY_TYPE,
            new Identifier(Sunbreaking.MOD_ID, "shadowshot_node"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, ShadowshotNodeEntity::new)
                    .dimensions(EntityDimensions.fixed(2f, 2f)).build());

    public static final EntityType<StormCloudEntity> STORM_CLOUD = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Sunbreaking.MOD_ID, "storm_cloud"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, StormCloudEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f, 1.0f)).trackRangeBlocks(10).build());

    public static void registerModEntities() {
        System.out.println("Registering entities for Sunbreaking Mod!");
    }
}
