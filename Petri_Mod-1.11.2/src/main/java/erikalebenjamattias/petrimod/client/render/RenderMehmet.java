package erikalebenjamattias.petrimod.client.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import erikalebenjamattias.petrimod.client.model.ModelMehmet;
import erikalebenjamattias.petrimod.entity.petristaff.EntityMehmet;

public class RenderMehmet extends RenderBiped<EntityMehmet> {

	private static final ResourceLocation mehmetTexture = new ResourceLocation("petrimod:textures/entity/mehmet.png");
	
	public RenderMehmet(RenderManager rendermanagerIn, ModelBiped modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
		LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelMehmet(0.5F);
                this.modelArmor = new ModelMehmet(1.0F);
            }
        };
        this.addLayer(layerbipedarmor);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMehmet entity) {
		return mehmetTexture;
	}
}