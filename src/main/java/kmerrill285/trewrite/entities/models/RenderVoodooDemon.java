package kmerrill285.trewrite.entities.models;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.monsters.EntityVoodooDemon;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderVoodooDemon extends MobRenderer<EntityVoodooDemon, ModelVoodooDemon>
{
    private ResourceLocation texture;

    public RenderVoodooDemon(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelVoodooDemon(), 4);
        this.texture = new ResourceLocation("trewrite:textures/entity/voodoo_demon.png");
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(EntityVoodooDemon entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
//    	BossStatus.setBossStatus(entity, true);
        this.shadowSize = 0.25F;
        super.doRender(entity, x, y, z, 0, partialTicks);
        
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityVoodooDemon entitylivingbaseIn, float partialTickTime)
    {
    	GlStateManager.scalef(0.5f, 0.5f, 0.5f);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityVoodooDemon entity)
    {
    	return texture;
    }
}
