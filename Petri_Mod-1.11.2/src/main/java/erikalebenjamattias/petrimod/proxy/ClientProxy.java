package erikalebenjamattias.petrimod.proxy;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import erikalebenjamattias.petrimod.client.model.ModelMehmet;
import erikalebenjamattias.petrimod.client.render.RenderGrandPiano;
import erikalebenjamattias.petrimod.client.render.RenderMehmet;
import erikalebenjamattias.petrimod.entity.inanimate.EntityGrandPiano;
import erikalebenjamattias.petrimod.entity.petristaff.EntityMehmet;
import erikalebenjamattias.petrimod.init.PetriBlocks;
import erikalebenjamattias.petrimod.init.PetriItems;
import erikalebenjamattias.petrimod.network.PetriPacketHandler;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerBlockAndItemRenders() {
		PetriBlocks.registerRenders();
		PetriItems.registerRenders();
	}
	
	@Override
	public void registerEntityRenders() {
		PetriPacketHandler.initClient();
		RenderingRegistry.registerEntityRenderingHandler(EntityMehmet.class, new IRenderFactory<EntityMehmet>() {
            @Override
            public Render<? super EntityMehmet> createRenderFor(RenderManager manager) {
                return new RenderMehmet(manager, new ModelMehmet(), 0.5F);
            }
        });
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGrandPiano.class, new IRenderFactory<EntityGrandPiano>() {
            @Override
            public Render<? super EntityGrandPiano> createRenderFor(RenderManager manager) {
                return new RenderGrandPiano(manager);
            }
        });
	}
}
