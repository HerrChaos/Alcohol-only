package charten.shield.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.9.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class Bottle extends EntityModel<Entity> {
	private final ModelPart Vodka;
	private final ModelPart bottle;
	private final ModelPart Flaschenhals;
	private final ModelPart flaschenhalsparts_r1;
	public Bottle(ModelPart root, ModelPart bottle, ModelPart flaschenhals, ModelPart flaschenhalspartsR1) {
		this.Vodka = root.getChild("Vodka");
		this.bottle = bottle;
		Flaschenhals = flaschenhals;
		flaschenhalsparts_r1 = flaschenhalspartsR1;
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Vodka = modelPartData.addChild("Vodka", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 16.0F, -1.0F));

		ModelPartData bottle = Vodka.addChild("bottle", ModelPartBuilder.create().uv(20, 15).cuboid(-1.0F, -6.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 22).cuboid(-2.0F, -5.0F, -1.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(16, 18).cuboid(-2.0F, -1.0F, -1.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 3.0F, 0.0F));

		ModelPartData Flaschenhals = bottle.addChild("Flaschenhals", ModelPartBuilder.create().uv(6, 24).cuboid(-2.0F, 0.0F, 0.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(6, 22).cuboid(-1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

		ModelPartData flaschenhalsparts_r1 = Flaschenhals.addChild("flaschenhalsparts_r1", ModelPartBuilder.create().uv(7, 24).cuboid(-1.0F, -8.0F, 12.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(7, 24).cuboid(-1.0F, -8.0F, 11.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, 8.0F, -8.0F, 0.0F, -0.7854F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Vodka.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}