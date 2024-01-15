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
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {
    public static final PointOfInterestType ALCOHOLIC_POI = registerPOI("alcoholic_poi", ModBlocks.ALCOHOL_BARREL_BLOCK);
    public static final VillagerProfession ALCOHOLIC = registerProfession("alcoholic",
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
        TradeOfferHelper.registerVillagerOffers(ALCOHOLIC,1,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.GLASS_BOTTLE, 3),
                            new ItemStack(ModItems.WINE, 5),
                            6, 2, 0.02f
                    )));
                });
    }
    public static void registerVillagers() {
        Main.LOGGER.debug("Registering Villagers for " + Main.MOD_ID);
    }
}
