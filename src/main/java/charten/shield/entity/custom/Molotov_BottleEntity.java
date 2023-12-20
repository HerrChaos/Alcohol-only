package charten.shield.entity.custom;

import charten.shield.Item.ModItems;
import charten.shield.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class Molotov_BottleEntity extends BottleEntity{
    public Molotov_BottleEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    public Molotov_BottleEntity(LivingEntity livingEntity, World world, EntityType modEntities) {
        super(livingEntity, world, modEntities);
    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.MOLOTOV;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        System.out.println("Hit a block");
        if (!this.getWorld().isClient()) {
            System.out.println("is on client");
        this.getWorld().sendEntityStatus(this, (byte) 3);
        World world = this.getWorld();
        Vec3d blockHitPos = blockHitResult.getPos();
        SetFireAllAround(world, blockHitResult.getBlockPos(), 5);
        ((ServerWorld) world).spawnParticles(
                new BlockStateParticleEffect(ParticleTypes.BLOCK, ModBlocks.VODKA_BLOCK.getDefaultState()),
                blockHitPos.getX(),
                blockHitPos.getY(),
                blockHitPos.getZ(),
                10, 0, 0, 0, 0.5
            );
        }
        this.discard();
        super.onBlockHit(blockHitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        System.out.println("Hit an Entity");
        super.onEntityHit(entityHitResult);
    }
/*
    @Override
    protected void onCollision(HitResult hitResult) {

        System.out.println("Hit smth");
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte) 3);
            World world = this.getWorld();
            Vec3d blockHitPos = hitResult.getPos();
            SetFireAllAround(world, BlockPos.ofFloored(blockHitPos), 5);
            ((ServerWorld) world).spawnParticles(
                    new BlockStateParticleEffect(ParticleTypes.BLOCK, ModBlocks.VODKA_BLOCK.getDefaultState()),
                    blockHitPos.getX(),
                    blockHitPos.getY(),
                    blockHitPos.getZ(),
                    10, 0, 0, 0, 0.5
            );
        }
        this.discard();

        super.onCollision(hitResult);

    }
*/
    private void SetFireAllAround(World world, BlockPos startPos, int radius) {
        System.out.println("trying to set shit on fire");
        for (int i = 0; i < 100; i++) {
            int offsetX = world.getRandom().nextInt(radius * 2 + 1) - radius;
            int offsetY = world.getRandom().nextInt(radius * 2 + 1) - radius;
            int offsetZ = world.getRandom().nextInt(radius * 2 + 1) - radius;

            BlockPos targetPos = startPos.add(offsetX, offsetY, offsetZ);
            System.out.println("trying " + targetPos);

            if ((!world.getBlockState(targetPos.down()).isAir() && world.getBlockState(targetPos).isAir())
                    || (world.getBlockState(targetPos.east()).isBurnable())
                    || (world.getBlockState(targetPos.west()).isBurnable())
                    || (world.getBlockState(targetPos.south()).isBurnable())
                    || (world.getBlockState(targetPos.north()).isBurnable())
            ) {
                System.out.println("selected " + targetPos);
                world.setBlockState(targetPos, Blocks.FIRE.getDefaultState());
            }
        }
    }
}