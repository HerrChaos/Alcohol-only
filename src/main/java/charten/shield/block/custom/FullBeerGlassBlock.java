package charten.shield.block.custom;

import charten.shield.statuseffect.ModStatusEffects;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Objects;

import static charten.shield.block.ModBlocks.BEER_GLASS_BLOCK;
import static net.minecraft.sound.SoundEvents.ENTITY_GENERIC_DRINK;

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
            StatusEffectInstance apliedStatuseffect = player.getStatusEffect(ModStatusEffects.Drunkinnis);
            if (apliedStatuseffect != null) {
                if (apliedStatuseffect.getAmplifier() >= 5) {
                    player.addStatusEffect(new StatusEffectInstance(ModStatusEffects.Drunkinnis, 20 * 20, 5));
                }
                player.addStatusEffect(new StatusEffectInstance(ModStatusEffects.Drunkinnis, 20 * 20, apliedStatuseffect.getAmplifier() + 1));
            } else {
                player.addStatusEffect(new StatusEffectInstance(ModStatusEffects.Drunkinnis, 20 * 20, 0));
            }

            for (Pair<StatusEffectInstance, Float> effect : Objects.requireNonNull(this.asItem().getFoodComponent()).getStatusEffects()) {
                player.addStatusEffect(effect.getFirst());
            }

            world.playSound(player, pos, ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS,1,1);
            world.setBlockState(pos, BEER_GLASS_BLOCK.getDefaultState());
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }
}
