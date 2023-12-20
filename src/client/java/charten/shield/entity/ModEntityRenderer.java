package charten.shield.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class ModEntityRenderer {
    public static void registerModEntityRenderer() {
        EntityRendererRegistry.register(ModEntities.VODKA_BOTTLE_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MOLOTOV_BOTTLE_ENTITY, FlyingItemEntityRenderer::new);
    }
}
