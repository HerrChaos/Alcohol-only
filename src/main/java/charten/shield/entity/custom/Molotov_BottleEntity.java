package charten.shield.entity.custom;

import charten.shield.Item.ModItems;
import charten.shield.entity.ModEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Molotov_BottleEntity extends ThrownItemEntity{
    public Molotov_BottleEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(ModEntities.MOLOTOV_BOTTLE_ENTITY, world);
    }
    public Molotov_BottleEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.MOLOTOV_BOTTLE_ENTITY, livingEntity, world);
    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.MOLOTOV;
    }
    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }


    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient()) {
            World world = this.getWorld();
            Vec3d blockHitPos = blockHitResult.getPos();
            this.getWorld().sendEntityStatus(this, (byte) 3);
            SetFireAllAround(world, blockHitResult.getBlockPos(), 2);
            ((ServerWorld) world).spawnParticles(
                    new ItemStackParticleEffect(ParticleTypes.ITEM, ModItems.MOLOTOV.getDefaultStack()),
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
        if (!this.getWorld().isClient()) {
            World world = this.getWorld();
            Vec3d blockHitPos = entityHitResult.getPos();
            this.getWorld().sendEntityStatus(this, (byte) 3);
            SetFireAllAround(world, entityHitResult.getEntity().getBlockPos(), 2);
            ((ServerWorld) world).spawnParticles(
                    new ItemStackParticleEffect(ParticleTypes.ITEM, ModItems.MOLOTOV.getDefaultStack()),
                    blockHitPos.getX(),
                    blockHitPos.getY(),
                    blockHitPos.getZ(),
                    10, 0, 0, 0, 0.5
            );
        }
        this.discard();
        super.onEntityHit(entityHitResult);
    }
    private void SetFireAllAround(World world, BlockPos startPos, int radius) {
        for (int i = 0; i < 50; i++) {
            int offsetX = world.getRandom().nextInt(radius * 2 + 1) - radius;
            int offsetY = world.getRandom().nextInt(radius * 2 + 1) - radius;
            int offsetZ = world.getRandom().nextInt(radius * 2 + 1) - radius;

            BlockPos targetPos = startPos.add(offsetX, offsetY, offsetZ);

            if (!world.getBlockState(targetPos.down()).isAir() &&
                    (world.getBlockState(targetPos).isAir())
                    || (world.getBlockState(targetPos.east()).isBurnable())
                    || (world.getBlockState(targetPos.west()).isBurnable())
                    || (world.getBlockState(targetPos.south()).isBurnable())
                    || (world.getBlockState(targetPos.north()).isBurnable())
            ) {
                if ((world.getBlockState(targetPos).isAir())) world.setBlockState(targetPos, Blocks.FIRE.getDefaultState());
            }
        }
    }
}