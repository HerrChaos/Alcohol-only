package charten.shield.statuseffect;

import charten.shield.statuseffect.custom.DrunkennisStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final StatusEffect Drunkinnis = new DrunkennisStatusEffect();
    public static void registerStatusEffect(String modId) {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(modId, "drunkinnis"), Drunkinnis);
    }
}
