package charten.shield.block.custom.blockentity;

import charten.shield.Item.ModItems;
import charten.shield.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowyBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.awt.*;
import java.util.Objects;

public class Alcohol_Barrel_blockentity extends BlockEntity {

    //THIS CODE MAY BE GLOBAL

    private static int FillLevel = 0;
    private static String Fluid = "none";
    public Alcohol_Barrel_blockentity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.ALCOHOL_BARREL_ENTITY, pos, state);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        Fluid = nbt.getString("fluid");
        FillLevel = nbt.getInt("fill level");
        super.readNbt(nbt);
    }
    public static void tick(World world, BlockPos pos, BlockState state, Alcohol_Barrel_blockentity de) {
        if (FillLevel > 8) FillLevel = 8;
        if (FillLevel == 0) Fluid = "none";
    }

    public static void OnUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (FillLevel < 8) {
            if (hasItemInHand(player, ModItems.BEER)) {
                MakeFluidCheck("beer", player);
            }
            if (hasItemInHand(player, ModItems.WINE)) {
                MakeFluidCheck("wine", player);
            }
            if (hasItemInHand(player, ModItems.VODKA)) {
                MakeFluidCheck("vodka", player);
            }
            if (hasItemInHand(player, ModItems.JAEGERMEISTER)) {
                MakeFluidCheck("jaegermeister", player);
            }
            if (hasItemInHand(player, ModItems.FULL_BEER_GLASS)) {
                MakeFluidCheck("beer", player);
            }
        }

        if (FillLevel > 0) {
            MakeBottleCheck("beer", ModItems.BEER, player);
            MakeBottleCheck("wine", ModItems.WINE, player);
            MakeBottleCheck("vodka", ModItems.VODKA, player);
            MakeBottleCheck("jaegermeister", ModItems.JAEGERMEISTER, player);

            if (hasItemInHand(player, ModBlocks.BEER_GLASS_BLOCK.asItem()) && Objects.equals(Fluid, "beer")) {
                if (!player.getAbilities().creativeMode) player.getStackInHand(player.getActiveHand()).decrement(1);
                player.giveItemStack(new ItemStack(ModItems.FULL_BEER_GLASS, 1));
                FillLevel --;
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("fill level", FillLevel);
        if (Fluid != "none") nbt.putString("fluid", Fluid);
        super.writeNbt(nbt);
    }



    private static boolean hasItemInHand(PlayerEntity player, Item item) {
        return player.getStackInHand(player.getActiveHand()).getItem() == item;
    }

    public static void MakeFluidCheck(String fluid, PlayerEntity player) {
        if (Fluid.equals(fluid)) {
            if (!player.getAbilities().creativeMode) player.getStackInHand(player.getActiveHand()).decrement(1);
            player.getWorld().playSound(player, player.getBlockPos(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS,1,1);
            FillLevel += 1;
        }
        if (Fluid == "none") {
            Fluid = fluid;
            FillLevel += 1;
            if (!player.getAbilities().creativeMode) player.getStackInHand(player.getActiveHand()).decrement(1);
            player.getWorld().playSound(player, player.getBlockPos(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS,1,1);
        }
    }

    public static void MakeBottleCheck(String fluid,  Item returnItem, PlayerEntity player) {
        if (hasItemInHand(player, Items.GLASS_BOTTLE) && Objects.equals(Fluid, fluid)) {
            if (!player.getAbilities().creativeMode) player.getStackInHand(player.getActiveHand()).decrement(1);
            player.giveItemStack(new ItemStack(returnItem, 1));
            FillLevel --;
        }
    }
}
