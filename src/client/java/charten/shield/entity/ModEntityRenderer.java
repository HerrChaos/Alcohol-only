package charten.shield.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class ModEntityRenderer {
    public static void registerModEntityRenderer() {
        EntityRendererRegistry.register(ModEntities.BOTTLE_PROJECTILE, FlyingItemEntityRenderer::new);
    }
}
