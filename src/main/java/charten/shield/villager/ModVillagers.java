package charten.shield.villager;

import charten.shield.Item.ModItems;
import charten.shield.Main;
import charten.shield.block.ModBlocks;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {
    public static final PointOfInterestType ALCOHOLIC_POI = registerPOI("alcoholic_poi", ModBlocks.ALCOHOL_BARREL_BLOCK);
    public static final VillagerProfession THE_DRUNK_GUY = registerProfession("the_drunk_guy",
            RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), new Identifier(Main.MOD_ID, "alcoholic_poi")));

    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(Main.MOD_ID, name),
                VillagerProfessionBuilder.create().id(new Identifier(Main.MOD_ID, name)).workstation(type)
                        .workSound(SoundEvents.ENTITY_VILLAGER_WORK_ARMORER).build());
    }

    public static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(Main.MOD_ID, name),
                1, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.THE_DRUNK_GUY, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.WHEAT, 15),
                            new ItemStack(Items.EMERALD, 1),
                            10, 1, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.SWEET_BERRIES, 20),
                            new ItemStack(Items.EMERALD, 1),
                            10, 1, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.POTATO, 20),
                            new ItemStack(Items.EMERALD, 1),
                            10, 1, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.FERN, 5),
                            new ItemStack(Items.EMERALD, 1),
                            10, 1, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.POTION, 1),
                            new ItemStack(Items.EMERALD, 1),
                            10, 1, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.FULL_BEER_GLASS, 1),
                            new ItemStack(Items.EMERALD, 2),
                            8, 3, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.THE_DRUNK_GUY, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.BEER, 1),
                            15, 5, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.WINE, 1),
                            15, 5, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.THE_DRUNK_GUY, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.JAEGERMEISTER, 1),
                            15, 5, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.VODKA, 1),
                            15, 5, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.THE_DRUNK_GUY, 4,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModBlocks.ALCOHOL_BARREL_BLOCK, 1),
                            new ItemStack(Items.EMERALD, 2),
                            5, 10, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.BARREL, 1),
                            new ItemStack(Items.EMERALD, 3),
                            5, 10, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.THE_DRUNK_GUY, 5,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 10),
                            new ItemStack(ModItems.MOLOTOV, 3),
                            3, 30, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 10),
                            new ItemStack(ModItems.BROKEN_BOTTLE, 3),
                            3, 30, 0.05f));
                });
    }
    public static void registerVillagers() {
        Main.LOGGER.debug("Registering Villagers for " + Main.MOD_ID);
    }
}
