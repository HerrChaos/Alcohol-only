package charten.shield.entity;

import charten.shield.Main;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.Identifier;

public class ModEntityRenderer<B extends ThrownItemEntity> {
    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier(Main.MOD_ID, "vodka_bottleentity"), "main");
    public static void registerModEntityRenderer() {
        EntityRendererRegistry.register(ModEntities.WINE_BOTTLE_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BEER_BOTTLE_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.JAEGERMEISTER_BOTTLE_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.VODKA_BOTTLE_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MOLOTOV_BOTTLE_ENTITY, FlyingItemEntityRenderer::new);
    }
}
