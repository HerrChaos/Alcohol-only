package charten.shield;

import charten.shield.Item.ModItems;
import charten.shield.Item.custom.BrockenBottleTester;
import charten.shield.block.ModBlocks;
import charten.shield.block.custom.blockentity.ModBlockEntityTypes;
import charten.shield.entity.ModEntities;
import charten.shield.statuseffect.ModStatusEffects;
import charten.shield.util.ModCustomTrades;
import charten.shield.villager.ModVillagers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

	public static final String MOD_ID = "shield";
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.MOD_ID);

    @Override
	public void onInitialize() {

		BrockenBottleTester.InitializeEventListiner();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntityTypes.registerModBlockEntities();
		ModEntities.registerEntities();
		ModStatusEffects.registerStatusEffect(MOD_ID);
		ModVillagers.registerVillagers();
		ModVillagers.registerTrades();

		LOGGER.info("Hello Fabric world!");
	}
}