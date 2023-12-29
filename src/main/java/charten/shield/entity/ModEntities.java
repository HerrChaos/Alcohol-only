package charten.shield.entity;

import charten.shield.Main;
import charten.shield.entity.custom.BottleEntity;
import charten.shield.entity.custom.Molotov_BottleEntity;
import charten.shield.entity.custom.Vodka_bottle;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<Vodka_bottle> VODKA_BOTTLE_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "vodka_bottle"),
            FabricEntityTypeBuilder.<Vodka_bottle>create(SpawnGroup.MISC, Vodka_bottle::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static final EntityType<Molotov_BottleEntity> MOLOTOV_BOTTLE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "molotov_bottleentity"),
            FabricEntityTypeBuilder.<Molotov_BottleEntity>create(SpawnGroup.MISC, Molotov_BottleEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build());
    public static void registerEntities() {
        System.out.println("Registering Entities for " + Main.MOD_ID);
    }
}