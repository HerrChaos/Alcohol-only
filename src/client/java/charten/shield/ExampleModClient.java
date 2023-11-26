package charten.shield;

import charten.shield.entity.ModEntityRenderer;
import net.fabricmc.api.ClientModInitializer;

public class ExampleModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModEntityRenderer.registerModEntityRenderer();
	}
}