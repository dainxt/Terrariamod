package kmerrill285.trewrite.entities.models.wyvern;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.wyvern.EntityWyvernHead;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderWyvernHead extends MobRenderer<EntityWyvernHead, ModelWyvernHead> {
	   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/wyvern_head.png");

	   public RenderWyvernHead(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn, new ModelWyvernHead(), 0.25F);
	   }

	   public void doRender(EntityWyvernHead entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      this.shadowSize = 0.25F;
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }

	   protected void preRenderCallback(EntityWyvernHead entitylivingbaseIn, float partialTickTime) {
	      float f = 1.0F;
	      GlStateManager.rotatef(entitylivingbaseIn.rx, 1.0F, 0.0F, 0.0F);
	      GlStateManager.rotatef(entitylivingbaseIn.ry, 0.0F, 1.0F, 0.0F);
	      GlStateManager.rotatef(entitylivingbaseIn.rz, 0.0F, 0.0F, 1.0F);
	      GlStateManager.scalef(f, f, f);
	   }

	   protected ResourceLocation getEntityTexture(EntityWyvernHead entity) {
	      return texture;
	   }
	}
