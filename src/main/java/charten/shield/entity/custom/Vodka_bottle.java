package charten.shield.entity.custom;

import charten.shield.Item.ModItems;
import charten.shield.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Vodka_bottle extends BottleEntity{
    public Vodka_bottle(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    public Vodka_bottle(LivingEntity livingEntity, World world, EntityType modEntities) {
        super(livingEntity, world, modEntities);
    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.VODKA;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte) 3);
            World world = this.getWorld();
            Vec3d blockHitPos = blockHitResult.getPos();
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
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (this.getWorld().isClient) {
            return;
        }
        this.discard();
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,200,1));
        }
        entityHitResult.getEntity().getWorld().playSound(null, entityHitResult.getEntity().getBlockPos() , SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.MUSIC,1,1);
    }
}
