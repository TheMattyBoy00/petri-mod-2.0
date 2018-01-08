package erikalebenjamattias.petrimod.client.render.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import erikalebenjamattias.petrimod.client.model.ModelGrandPiano;
import erikalebenjamattias.petrimod.entity.inanimate.EntityGrandPiano;

public class RenderGrandPiano<T extends EntityGrandPiano> extends Render {

	private static final ResourceLocation pianoTexture = new ResourceLocation("petrimod:textures/entity/grand_piano.png");
	private ModelGrandPiano model;

	public RenderGrandPiano(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelGrandPiano();
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindEntityTexture(entity);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 2.4, z);
		GlStateManager.rotate(180, 0, 0, 1);
		this.model.render(entity, 0, 0, entity.ticksExisted + partialTicks, 0, 0, 0.1F);
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return pianoTexture;
	}

}
