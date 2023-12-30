package charten.shield.statuseffect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
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

        }

        if (amplifier >= 3) {
            if (entity instanceof PlayerEntity) {
                if (random.nextInt(160) == 1) {
                    PlayerEntity player = ((PlayerEntity) entity);
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40,0));
                }
            }
        }
    }
}
