package charten.shield.statuseffect.custom;

import charten.shield.statuseffect.ModStatusEffects;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class DrunkennisStatusEffect extends StatusEffect {

    public DrunkennisStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x549c33);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }

    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        Random random = new Random();
        amplifier += 1;
        if (random.nextInt(20) == 1) {
            Vec3d playerLook = entity.getRotationVector();

            Vec3d force_right = new Vec3d(-playerLook.z, 0, playerLook.x).normalize().multiply(0.1);
            Vec3d force_left = new Vec3d(playerLook.z, 0, -playerLook.x).normalize().multiply(0.1);


            if (random.nextInt(2) == 1) {
                entity.addVelocity(force_right);
            } else {
                entity.addVelocity(force_left);
            }
        }

        if (amplifier >= 2) {
            if (random.nextInt(300) == 1) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 3, 0));
            }
        }

        if (amplifier >= 3) {
            if (random.nextInt(160) == 1) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40,0));
            }
        }
        if (amplifier >= 4) {
            if (random.nextInt(160) == 1) {
                entity.dropStack(entity.getStackInHand(entity.getActiveHand()));
                entity.setStackInHand(entity.getActiveHand(), Items.AIR.getDefaultStack());
            }
        }
        if (amplifier >= 5) {
            if (random.nextInt(160) == 1) {
            }
        }
        if (amplifier >= 6) {
            if (random.nextInt(160) == 1) {
            }
        }
        if (amplifier >= 7) {
            if (random.nextInt(160) == 1) {
            }
        }
    }
}
