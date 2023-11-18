package charten.shield.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static charten.shield.block.ModBlocks.*;
import static net.minecraft.sound.SoundEvents.*;

public class FullBeerGlassBlock extends Block{

    private static final VoxelShape ONE_BOTTLE_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 9.0, 10.0);
    public FullBeerGlassBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return ONE_BOTTLE_SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).isEmpty()) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000, 1));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 1));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000, 1));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000, 1));
            world.playSound(player, pos, ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS,1,1);
            world.setBlockState(pos, BEER_GLASS_BLOCK.getDefaultState());
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

/*
    statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000, 1), 1f))

 */
}
