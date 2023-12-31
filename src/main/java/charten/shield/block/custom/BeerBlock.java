package charten.shield.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class BeerBlock extends Block{
    public static final IntProperty BOTTLES = IntProperty.of("bottles", 1,6);
    public static final BooleanProperty WOODED = BooleanProperty.of("wooded");

    private static final VoxelShape WOODED_BOTTLE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 14.0, 9.0, 14.0);
    private static final VoxelShape ONE_BOTTLE_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 11.0, 10.0);
    private static final VoxelShape TWO_BOTTLE_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 11.0, 13.0);
    private static final VoxelShape THREE_BOTTLE_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 11.0, 13.0);
    private static final VoxelShape FOUR_BOTTLE_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 11.0, 13.0);
    private static final VoxelShape FIVE_BOTTLE_SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 11.0, 14.0);
    private static final VoxelShape SIX_BOTTLE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 11.0, 15.0);
    public BeerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BOTTLES, 1).with(WOODED, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BOTTLES);
        builder.add(WOODED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return blockState.cycle(BOTTLES);
        }
        if (blockState.isOf(this)) {
            return blockState.cycle(WOODED);
        }
        return super.getPlacementState(ctx);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        if (!context.shouldCancelInteraction() && context.getStack().getItem() == this.asItem() && state.get(BOTTLES) < 6) {
            return true;
        }

        return super.canReplace(state, context);
    }

    private void breakOneBottle(World world, BlockPos pos, BlockState state) {
        world.playSound(null, pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 0.7f, 0.9f + world.random.nextFloat() * 0.2f);
        int i = state.get(BOTTLES);
        if (i <= 1) {
            world.breakBlock(pos, false);
        } else {
            world.setBlockState(pos, state.with(BOTTLES, i - 1), Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(state));
            world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(state));
        }
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        this.breakOneBottle(world, pos, state);
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(WOODED)) {
            return WOODED_BOTTLE_SHAPE;
        }
        switch (state.get(BOTTLES)) {
            default -> {
                return ONE_BOTTLE_SHAPE;
            }
            case 2 -> {
                return TWO_BOTTLE_SHAPE;
            }
            case 3 -> {
                return THREE_BOTTLE_SHAPE;
            }
            case 4 -> {
                return FOUR_BOTTLE_SHAPE;
            }
            case 5 -> {
                return FIVE_BOTTLE_SHAPE;
            }
            case 6 -> {
                return SIX_BOTTLE_SHAPE;
            }
        }
    }

}
