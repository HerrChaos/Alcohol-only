package charten.shield.Item.custom;

import charten.shield.Item.ModItems;
import charten.shield.block.ModBlocks;
import charten.shield.entity.ModEntities;
import charten.shield.entity.custom.Bottle_entity;
import charten.shield.statuseffect.ModStatusEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.*;

import static net.minecraft.item.Items.GLASS_BOTTLE;

public class AlcoholItem extends BlockItem {

    private static final int MAX_USE_TIME = 60;

    public AlcoholItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return MAX_USE_TIME;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }


    public ActionResult useOnBlock(ItemUsageContext context) {
        List<Item> items = new ArrayList<>(5);
        items.add(ModItems.JAEGERMEISTER);
        items.add(ModItems.VODKA);
        items.add(ModItems.WINE);
        items.add(ModItems.FULL_BEER_GLASS);
        items.add(ModItems.BEER);
        if (context.getPlayer().getStackInHand(Hand.OFF_HAND).getItem() == null) {
            return ActionResult.PASS;
        }
        if (items.contains(context.getPlayer().getStackInHand(Hand.OFF_HAND).getItem())) {
            super.use(context.getWorld(),context.getPlayer(),context.getHand());
            return ActionResult.FAIL;
        }
        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.incrementStat(Stats.USED.getOrCreateStat(this));

        List<Item> items = new ArrayList<>(5);
        items.add(ModItems.JAEGERMEISTER);
        items.add(ModItems.VODKA);
        items.add(ModItems.WINE);
        items.add(ModItems.FULL_BEER_GLASS);
        items.add(ModItems.BEER);

        if (items.contains(user.getStackInHand(Hand.OFF_HAND).getItem())) {

            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 1F); // plays a globalSoundEvent

            if (!world.isClient) {
                ThrownItemEntity entity = null;
                if (items.contains(ModItems.VODKA)) {
                    entity = new Bottle_entity(ModEntities.VODKA_BOTTLE_PROJECTILE, user, world);
                }

                if (items.contains(ModItems.BEER)) {
                    entity = new Bottle_entity(ModEntities.BEER_BOTTLE_PROJECTILE, user, world);
                }

                if (items.contains(ModItems.WINE)) {
                    entity = new Bottle_entity(ModEntities.JAEGERMEISTER_BOTTLE_PROJECTILE, user, world);
                }

                if (items.contains(ModItems.JAEGERMEISTER)) {
                    entity = new Bottle_entity(ModEntities.WINE_BOTTLE_PROJECTILE, user, world);
                }

                if (entity != null) {
                    entity.setItem(itemStack);
                    entity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 0F);
                    world.spawnEntity(entity);
                }
                if (!user.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
            }
            return TypedActionResult.success(itemStack, world.isClient());
        }
        super.use(world, user, hand);
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        StatusEffectInstance apliedStatuseffect = user.getStatusEffect(ModStatusEffects.Drunkinnis);
        if (apliedStatuseffect != null) {
            if (apliedStatuseffect.getAmplifier() >= 5) {
                user.addStatusEffect(new StatusEffectInstance(ModStatusEffects.Drunkinnis, 20 * 20, 5));
            }
            user.addStatusEffect(new StatusEffectInstance(ModStatusEffects.Drunkinnis, 20 * 20, apliedStatuseffect.getAmplifier() + 1));
        } else {
            user.addStatusEffect(new StatusEffectInstance(ModStatusEffects.Drunkinnis, 20 * 20, 0));
        }
        if (user.isPlayer() && !((PlayerEntity) user).getAbilities().creativeMode) {
            if (stack.getItem() == ModItems.FULL_BEER_GLASS) {
                ((PlayerEntity) user).giveItemStack(new ItemStack(ModBlocks.BEER_GLASS_BLOCK, 1));
            }
            ((PlayerEntity) user).giveItemStack(new ItemStack(GLASS_BOTTLE, 1));
        }
        return stack;
    }
}
