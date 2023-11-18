package charten.shield.Item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {

    public static final FoodComponent BEER = new FoodComponent.Builder().hunger(4).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000, 1), 1f).build();

    public static final FoodComponent VODKA = new FoodComponent.Builder().hunger(4).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 250), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000,1), 1f).build();

    public static final FoodComponent WINE = new FoodComponent.Builder().hunger(4).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000, 1), 1f).build();

    public static final FoodComponent JAEGERMEISTER = new FoodComponent.Builder().hunger(4).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 250), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000, 1), 1f).build();
}
