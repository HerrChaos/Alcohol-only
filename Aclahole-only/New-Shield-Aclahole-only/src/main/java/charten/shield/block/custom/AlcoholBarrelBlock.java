package charten.shield.block.custom;

import charten.shield.Item.ModItems;
import charten.shield.block.custom.blockentity.Alcohol_Barrel_blockentity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static charten.shield.Item.ModItems.*;

public class AlcoholBarrelBlock extends Block implements BlockEntityProvider {

    public static final IntProperty FILL_LEVEL = IntProperty.of("fill_level", 0,8);
    public static final BooleanProperty BEER = BooleanProperty.of("beer");
    public static final BooleanProperty WINE = BooleanProperty.of("wine");
    public static final BooleanProperty VODKA = BooleanProperty.of("vodka");
    public static final BooleanProperty JAEGERMEISTER = BooleanProperty.of("jaegermeister");
    public AlcoholBarrelBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FILL_LEVEL, 0).with(BEER, false).with(WINE, false).with(VODKA, false).with(JAEGERMEISTER, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FILL_LEVEL);
        builder.add(BEER);
        builder.add(WINE);
        builder.add(VODKA);
        builder.add(JAEGERMEISTER);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return blockState.cycle(FILL_LEVEL);
        }
        if (blockState.isOf(this)) {
            return blockState.cycle(BEER);
        }
        if (blockState.isOf(this)) {
            return blockState.cycle(WINE);
        }
        if (blockState.isOf(this)) {
            return blockState.cycle(VODKA);
        }
        if (blockState.isOf(this)) {
            return blockState.cycle(JAEGERMEISTER);
        }
        return super.getPlacementState(ctx);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        /*
        if (hasItemInHand(player, GLASS_BOTTLE) && getActiveStateinItem(state) != null && state.get(FILL_LEVEL) > 0) {
            player.getStackInHand(hand).decrement(1);
            world.setBlockState(pos, state.with(FILL_LEVEL, state.get(FILL_LEVEL) - 1));
            if (state.get(FILL_LEVEL) == 0) {
                setAllStatesFalse(state, world, pos);
            }
            player.giveItemStack(new ItemStack(getActiveStateinItem(state)));
            return ActionResult.success(world.isClient);
        }

         */
        if (hasItemInHand(player, ModItems.BEER) && state.get(FILL_LEVEL) < 8) {
            if (allStatesFalse(state)) {
                System.out.println("Tried to set beer to true");
                world.setBlockState(pos, state.cycle(BEER));
                world.setBlockState(pos, state.cycle(FILL_LEVEL));
                //if (!player.getAbilities().creativeMode) player.getStackInHand(hand).decrement(1);
                return ActionResult.success(world.isClient);
            }
            if (state.get(BEER)) {
                System.out.println("Tried to set fill level");
                if (!player.getAbilities().creativeMode) player.getStackInHand(hand).decrement(1);
                world.setBlockState(pos, state.cycle(FILL_LEVEL));
                return ActionResult.success(world.isClient);
            }
        }

        if (hasItemInHand(player, ModItems.WINE) && state.get(FILL_LEVEL) < 8) {
            if (allStatesFalse(state)) {
                if (!player.getAbilities().creativeMode) player.getStackInHand(hand).decrement(1);
                world.setBlockState(pos, state.cycle(WINE));
                world.setBlockState(pos, state.cycle(FILL_LEVEL));
                return ActionResult.success(world.isClient);
            }
            if (state.get(WINE)) {
                if (!player.getAbilities().creativeMode) player.getStackInHand(hand).decrement(1);
                world.setBlockState(pos, state.cycle(FILL_LEVEL));
                return ActionResult.success(world.isClient);
            }
        }

        if (hasItemInHand(player, ModItems.VODKA) && state.get(FILL_LEVEL) < 8) {
            if (allStatesFalse(state)) {
                if (!player.getAbilities().creativeMode) player.getStackInHand(hand).decrement(1);
                world.setBlockState(pos, state.cycle(VODKA));
                world.setBlockState(pos, state.cycle(FILL_LEVEL));
                return ActionResult.success(world.isClient);
            }
            if (state.get(VODKA)) {
                if (!player.getAbilities().creativeMode) player.getStackInHand(hand).decrement(1);
                world.setBlockState(pos, state.cycle(FILL_LEVEL));
                return ActionResult.success(world.isClient);
            }
        }

        if (hasItemInHand(player, ModItems.JAEGERMEISTER) && state.get(FILL_LEVEL) < 8) {
            if (allStatesFalse(state)) {
                if (!player.getAbilities().creativeMode) player.getStackInHand(hand).decrement(1);
                world.setBlockState(pos, state.cycle(JAEGERMEISTER));
                world.setBlockState(pos, state.cycle(FILL_LEVEL));
                return ActionResult.success(world.isClient);
            }
            if (state.get(JAEGERMEISTER)) {
                if (!player.getAbilities().creativeMode) player.getStackInHand(hand).decrement(1);
                world.setBlockState(pos, state.cycle(FILL_LEVEL));
                return ActionResult.success(world.isClient);
            }
        }

        if (hasItemInHand(player, FULL_BEER_GLASS) && state.get(FILL_LEVEL) < 8) {
            if (allStatesFalse(state)) {
                if (!player.getAbilities().creativeMode) player.getStackInHand(hand).decrement(1);
                world.setBlockState(pos, state.cycle(BEER));
                world.setBlockState(pos, state.cycle(FILL_LEVEL));
                return ActionResult.success(world.isClient);
            }
            if (state.get(BEER)) {
                if (!player.getAbilities().creativeMode) player.getStackInHand(hand).decrement(1);
                world.setBlockState(pos, state.cycle(FILL_LEVEL));
                return ActionResult.success(world.isClient);
            }
        }

        return ActionResult.FAIL;
    }

    private boolean hasItemInHand(PlayerEntity player, Item item) {
        return player.getStackInHand(player.getActiveHand()).getItem() == item;
    }

    private boolean allStatesFalse(BlockState state) {
        return !state.get(BEER) && !state.get(WINE) && !state.get(VODKA) && !state.get(JAEGERMEISTER);
    }
    private Item getActiveStateinItem(BlockState state) {
        if (state.get(BEER)) {
            return ModItems.BEER;
        }
        if (state.get(WINE)) {
            return ModItems.WINE;
        }
        if (state.get(VODKA)) {
            return ModItems.VODKA;
        }
        if (state.get(JAEGERMEISTER)) {
            return ModItems.JAEGERMEISTER;
        }
        return null;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new Alcohol_Barrel_blockentity(pos, state);
    }
    /*
    private void setAllStatesFalse(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(BEER, false));
        world.setBlockState(pos, state.with(WINE, false));
        world.setBlockState(pos, state.with(VODKA, false));
        world.setBlockState(pos, state.with(JAEGERMEISTER, false));
    }

     */
}
