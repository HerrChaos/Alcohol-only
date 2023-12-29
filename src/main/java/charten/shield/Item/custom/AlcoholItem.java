package charten.shield.Item.custom;

import charten.shield.Item.ModItems;
import charten.shield.block.ModBlocks;
import charten.shield.entity.ModEntities;
import charten.shield.entity.custom.BottleEntity;
import charten.shield.entity.custom.Molotov_BottleEntity;
import charten.shield.entity.custom.Vodka_bottle;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

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

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (user.isSneaking()) {
            ItemStack itemStack1 = user.getStackInHand(hand); // creates a new ItemStack instance of the user's itemStack in-hand
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 1F); // plays a globalSoundEvent
            if (!world.isClient) {
                Vodka_bottle entity = new Vodka_bottle(user, world);
                entity.setItem(itemStack1);
                entity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 0F);
                world.spawnEntity(entity); // spawns entity
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.getAbilities().creativeMode) {
                itemStack1.decrement(1); // decrements itemStack if user is not in creative mode
            }
            return TypedActionResult.success(itemStack, world.isClient());
        }
        super.use(world, user, hand);
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        if (user.isPlayer() && !((PlayerEntity) user).getAbilities().creativeMode) {
            if (stack.getItem() == ModItems.FULL_BEER_GLASS) {
                ((PlayerEntity) user).giveItemStack(new ItemStack(ModBlocks.BEER_GLASS_BLOCK, 1));
            }
            ((PlayerEntity) user).giveItemStack(new ItemStack(GLASS_BOTTLE, 1));
        }
        return stack;
    }
}
