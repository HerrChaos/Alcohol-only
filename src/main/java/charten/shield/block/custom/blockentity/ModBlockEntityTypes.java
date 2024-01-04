package charten.shield.block.custom.blockentity;

import charten.shield.Main;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static charten.shield.block.ModBlocks.ALCOHOL_BARREL_BLOCK;

public class ModBlockEntityTypes {
    public static final BlockEntityType<Alcohol_Barrel_blockentity> ALCOHOL_BARREL_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(Main.MOD_ID, "alcohol_block_entity"),
            FabricBlockEntityTypeBuilder.create(Alcohol_Barrel_blockentity::new, ALCOHOL_BARREL_BLOCK).build()
    );

    public static void registerModBlockEntities() {
        Main.LOGGER.info("Loading mod EntityTypes for " + Main.MOD_ID);
    }
}
