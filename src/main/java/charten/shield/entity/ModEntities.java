package charten.shield.entity;

import charten.shield.Main;
import charten.shield.entity.custom.BottleEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<BottleEntity> BOTTLE_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "bottle_projectile"),
            FabricEntityTypeBuilder.<BottleEntity>create(SpawnGroup.MISC, BottleEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static void registerEntities() {
        System.out.println("Registering Entities for " + Main.MOD_ID);
    }
}