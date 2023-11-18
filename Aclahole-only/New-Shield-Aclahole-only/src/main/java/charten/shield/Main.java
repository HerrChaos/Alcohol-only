package charten.shield;

import charten.shield.Item.ModItems;
import charten.shield.block.ModBlocks;
import charten.shield.block.custom.blockentity.ModBlockEntityTypes;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("shield");
    public static final String MOD_ID = "shield";

    @Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntityTypes.registerModBlockEntities();

		LOGGER.info("Hello Fabric world!");
	}
}