package charten.shield;

import charten.shield.entity.ModEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.block.MagmaBlock;
import net.minecraft.client.gui.screen.advancement.AdvancementObtainedStatus;
import net.minecraft.client.particle.SnowflakeParticle;

public class ExampleModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModEntityRenderer.registerModEntityRenderer();
	}
}