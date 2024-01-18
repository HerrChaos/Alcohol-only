package charten.shield.Item;

import charten.shield.Main;
import charten.shield.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup VANILLAY_END_STUFF = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Main.MOD_ID, "all_the_alcohol"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.all_the_alcohol"))
                    .icon(() -> new ItemStack(ModItems.BEER)).entries((displayContext, entries) -> {

                        entries.add(ModItems.BEER);
                        entries.add(ModItems.WINE);
                        entries.add(ModItems.VODKA);
                        entries.add(ModItems.JAEGERMEISTER);
                        entries.add(ModItems.FULL_BEER_GLASS);
                        entries.add(ModBlocks.BEER_GLASS_BLOCK);
                        entries.add(ModItems.BROKEN_BOTTLE);
                        entries.add(ModItems.MOLOTOV);
                        entries.add(ModBlocks.ALCOHOL_BARREL_BLOCK);

                    }).build());

    public static void registerItemGroups() {
        Main.LOGGER.info("Registering Item Groups for" + Main.MOD_ID);
    }
}
