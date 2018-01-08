package erikalebenjamattias.petrimod.client.render.entity;

import java.util.Calendar;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import erikalebenjamattias.petrimod.entity.inanimate.EntityFrontClock;

public class RenderFrontClock<T extends EntityFrontClock> extends Render<EntityFrontClock> {

	private static final ResourceLocation clockTexture = new ResourceLocation("petrimod:textures/entity/front_clock.png");

	public RenderFrontClock(RenderManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void doRender(EntityFrontClock entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
        GlStateManager.translate(x + (entity.facingDirection == EnumFacing.SOUTH || entity.facingDirection == EnumFacing.WEST ? 0.5 : -0.5), y + 0.5, z + (entity.facingDirection == EnumFacing.NORTH || entity.facingDirection == EnumFacing.WEST ? 0.5 : -0.5));
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.enableRescaleNormal();
		this.bindEntityTexture(entity);
        GlStateManager.scale(0.0625F, 0.0625F, 0.0625F);
		
		if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }
		
		this.renderClock();
		
		if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	private void renderClock() {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        bufferbuilder.pos(-16, 16, -0.5D).tex(0.5, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(16, 16, -0.5D).tex(0, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(16, -16, -0.5D).tex(0, 1).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(-16, -16, -0.5D).tex(0.5, 1).normal(0.0F, 0.0F, -1.0F).endVertex();
        
        bufferbuilder.pos(-2, 2, -1.5D).tex(0.5625, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2, 2, -1.5D).tex(0.5, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2, -2, -1.5D).tex(0.5, 0.125).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(-2, -2, -1.5D).tex(0.5625, 0.125).normal(0.0F, 0.0F, -1.0F).endVertex();
        
        bufferbuilder.pos(2, 2, -1.5D).tex(0.515625, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2, 2, -0.5D).tex(0.5, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2, -2, -0.5D).tex(0.5, 0.125).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2, -2, -1.5D).tex(0.515625, 0.125).normal(0.0F, 0.0F, -1.0F).endVertex();
        
        bufferbuilder.pos(-2, 2, -0.5D).tex(0.515625, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(-2, 2, -1.5D).tex(0.5, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(-2, -2, -1.5D).tex(0.5, 0.125).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(-2, -2, -0.5D).tex(0.515625, 0.125).normal(0.0F, 0.0F, -1.0F).endVertex();
        
        bufferbuilder.pos(-2, 2, -1.5D).tex(0.515625, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(-2, 2, -0.5D).tex(0.5, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2, 2, -0.5D).tex(0.5, 0.125).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2, 2, -1.5D).tex(0.515625, 0.125).normal(0.0F, 0.0F, -1.0F).endVertex();
        
        bufferbuilder.pos(2, -2, -1.5D).tex(0.515625, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2, -2, -0.5D).tex(0.5, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(-2, -2, -0.5D).tex(0.5, 0.125).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(-2, -2, -1.5D).tex(0.515625, 0.125).normal(0.0F, 0.0F, -1.0F).endVertex();
        tessellator.draw();

        GlStateManager.rotate((getHour() % 12) * 30 + getMinute() / 2, 0.0F, 0.0F, 1.0F);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        bufferbuilder.pos(-2.5, 12, -0.85D).tex(0.921875, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2.5, 12, -0.85D).tex(0.84375, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2.5, -12, -0.85D).tex(0.84375, 0.75).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(-2.5, -12, -0.85D).tex(0.921875, 0.75).normal(0.0F, 0.0F, -1.0F).endVertex();
        tessellator.draw();

        GlStateManager.rotate(-((getHour() % 12) * 30 + getMinute() / 2), 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(getMinute() * 6, 0.0F, 0.0F, 1.0F);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        bufferbuilder.pos(-2.5, 14, -1.15D).tex(1, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2.5, 14, -1.15D).tex(0.921875, 0).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(2.5, -14, -1.15D).tex(0.921875, 0.875).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(-2.5, -14, -1.15D).tex(1, 0.875).normal(0.0F, 0.0F, -1.0F).endVertex();
        tessellator.draw();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFrontClock entity) {
		return this.clockTexture;
	}
	
	public static int getSecond() {
		return Integer.parseInt(Calendar.getInstance().getTime().toString().substring(17, 19));
	}
	
	public static int getMinute() {
		return Integer.parseInt(Calendar.getInstance().getTime().toString().substring(14, 16));
	}
	
	public static int getHour() {
		return Integer.parseInt(Calendar.getInstance().getTime().toString().substring(11, 13));
	}
}
