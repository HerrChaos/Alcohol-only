package charten.shield.entity.renderer;

import charten.shield.Main;
import charten.shield.entity.custom.Bottle_entity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class BottleEntityRenderer extends EntityRenderer<Bottle_entity> {

    public BottleEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(Bottle_entity entity) {
        return new Identifier(Main.MOD_ID, "textures/entity/bottle_entity.png");
    }
}
