package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.bosses.destroyer.EntityProbe;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelProbe extends EntityModel<EntityProbe> {
	private final RendererModel bb_main;

	public ModelProbe() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -6.0F, -18.0F, -6.0F, 12, 12, 12, 0.0F, false));
	}

	@Override
	public void render(EntityProbe entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}