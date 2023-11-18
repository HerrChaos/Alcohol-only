package charten.shield.block;

import charten.shield.Main;
import charten.shield.block.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block ALCOHOL_BARREL_BLOCK = registerBlock("alcohol_barrel_block",
            new Alcohol_barrel_block(FabricBlockSettings.create().pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.WOOD).strength(2f)));

    public static final Block BEER_GLASS_BLOCK = registerBlock("beer_glass",
            new BeerGlassBlock(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GLASS).strength(0.1f)));

    public static final Block FULL_BEER_GLASS_BLOCK = registerBlock("full_beer_glass",
            new FullBeerGlassBlock(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GLASS).strength(0.1f)));

    public static final Block BEER_BLOCK = registerBlock("beer",
            new BeerBlock(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GLASS).strength(0.1f)));

    public static final Block VODKA_BLOCK = registerBlock("vodka",
            new VodkaBlock(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GLASS).strength(0.1f)));

    public static final Block WINE_BLOCK = registerBlock("wine",
            new WineBlock(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GLASS).strength(0.1f)));

    public static final Block JAEGERMEISTER_BLOCK = registerBlock("jaegermeister",
            new JaegermeisterBlock(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GLASS).strength(0.1f)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Main.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static void addItemsToFunctionalTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(ALCOHOL_BARREL_BLOCK);
    }

    public static void registerModBlocks() {
        Main.LOGGER.info("Registering ModBlocks for " + Main.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ModBlocks::addItemsToFunctionalTabItemGroup);
    }
}
