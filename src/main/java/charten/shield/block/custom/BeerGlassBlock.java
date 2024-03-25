package charten.shield.block.custom;

import charten.shield.Item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static charten.shield.block.ModBlocks.FULL_BEER_GLASS_BLOCK;

public class BeerGlassBlock extends Block{

    private static final VoxelShape ONE_BOTTLE_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 8.0, 10.0);
    public BeerGlassBlock(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.getAbilities().allowModifyWorld && player.getStackInHand(hand).getItem() == ModItems.BEER) {
            if (!player.getAbilities().creativeMode){
                player.getStackInHand(hand).decrement(1);
                player.giveItemStack(Items.GLASS_BOTTLE.getDefaultStack());
            }
            world.setBlockState(pos, FULL_BEER_GLASS_BLOCK.getDefaultState());
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return ONE_BOTTLE_SHAPE;
    }

}
