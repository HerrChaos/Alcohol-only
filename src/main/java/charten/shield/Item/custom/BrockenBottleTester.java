package charten.shield.Item.custom;

import charten.shield.Item.ModItems;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BrockenBottleTester {

    public static void InitializeEventListiner() {
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            List<Item> items = new ArrayList<>(5);
            items.add(ModItems.JAEGERMEISTER);
            items.add(ModItems.VODKA);
            items.add(ModItems.WINE);
            items.add(ModItems.FULL_BEER_GLASS);
            items.add(ModItems.BEER);

            ItemStack heldItem = player.getStackInHand(hand);

            if (items.contains(heldItem.getItem())) {
                if (world.getBlockState(pos).getBlock().getHardness() >= Blocks.STONE.getHardness()) {
                    player.setStackInHand(player.getActiveHand(), ModItems.BROKEN_BOTTLE.getDefaultStack());
                    world.playSound(null, pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1);
                    return ActionResult.SUCCESS;
                }
            }

            return ActionResult.PASS;
        });
    }
}
