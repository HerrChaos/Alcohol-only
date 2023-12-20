package charten.shield.block.custom;

import charten.shield.block.custom.blockentity.Alcohol_Barrel_blockentity;
import charten.shield.block.custom.blockentity.ModBlockEntityTypes;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static net.minecraft.util.math.Direction.NORTH;


public class Alcohol_barrel_block extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0, 1.0, 1.0, 16.0, 15.0, 15.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0, 1.0, 1.0, 16.0, 15.0, 15.0);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(1.0, 1.0, 0.0, 15.0, 15.0, 16.0);
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(1.0, 1.0, 0.0, 15.0, 15.0, 16.0);
    public Alcohol_barrel_block(Settings settings) {
        super(settings);
    }
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, NORTH));
        return new Alcohol_Barrel_blockentity(pos, state);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntityTypes.ALCOHOL_BARREL_ENTITY, Alcohol_Barrel_blockentity::tick);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {

        switch (state.get(FACING)) {
            case EAST -> {
                return EAST_SHAPE;
            }
            case WEST -> {
                return WEST_SHAPE;
            }
            case NORTH -> {
                return NORTH_SHAPE;
            }
            case SOUTH -> {
                return SOUTH_SHAPE;
            }
        }
        return NORTH_SHAPE;
    }



    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Alcohol_Barrel_blockentity.OnUse(state, world, pos, player, hand, hit);
        return ActionResult.success(world.isClient);
    }
}

