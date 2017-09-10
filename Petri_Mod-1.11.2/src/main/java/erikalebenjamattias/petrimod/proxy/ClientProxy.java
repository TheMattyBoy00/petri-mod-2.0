package erikalebenjamattias.petrimod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import erikalebenjamattias.petrimod.client.audio.PianoSound;
import erikalebenjamattias.petrimod.client.gui.GuiPianoKeyboard;
import erikalebenjamattias.petrimod.client.model.ModelMehmet;
import erikalebenjamattias.petrimod.client.render.RenderGrandPiano;
import erikalebenjamattias.petrimod.client.render.RenderMehmet;
import erikalebenjamattias.petrimod.entity.inanimate.EntityGrandPiano;
import erikalebenjamattias.petrimod.entity.petristaff.EntityMehmet;
import erikalebenjamattias.petrimod.init.PetriBlocks;
import erikalebenjamattias.petrimod.init.PetriItems;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerBlockAndItemRenders() {
		PetriBlocks.registerRenders();
		PetriItems.registerRenders();
	}
	
	@Override
	public void registerEntityRenders() {
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
	
	@Override
	public void clientPacketPianoGUI(int pianoId) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			World world = Minecraft.getMinecraft().world;
			if(world.getEntityByID(pianoId) instanceof EntityGrandPiano) {
				Minecraft.getMinecraft().displayGuiScreen(new GuiPianoKeyboard((EntityGrandPiano)world.getEntityByID(pianoId)));
			}
		});
	}
	
	@Override
	public void clientPacketPlayPianoSound(byte noteIndex, float noteVelocity, int pianoId) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			Minecraft.getMinecraft().getSoundHandler().playSound(GuiPianoKeyboard.pianoSound[noteIndex].setEntity(Minecraft.getMinecraft().world.getEntityByID(pianoId)).setVolume(noteVelocity));
		});
	}
	
	@Override
	public void clientPacketResetPianoSound(byte noteIndex) {
		//try {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			if(noteIndex <= 72) {
				GuiPianoKeyboard.lingeringSounds.add(GuiPianoKeyboard.pianoSound[noteIndex]);
			}
			GuiPianoKeyboard.pianoSound[noteIndex] = new PianoSound(GuiPianoKeyboard.pianoSound[noteIndex].getSoundEvent(), noteIndex % 3 == 0 ? 0.9438743126885998235254131922893F : (noteIndex % 3 == 1 ? 1.0F : 1.0594630943592952645618252949463F)/*GuiPianoKeyboard.pianoSound[noteIndex].getPitch()*/);
		});
		//} catch(Exception e) {
			
		//}
	}
	
	@Override
	public void clientPacketStopPianoSound(byte lingeringSound) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			GuiPianoKeyboard.lingeringSounds.get(lingeringSound).stopPlaying();
			GuiPianoKeyboard.lingeringSounds.remove(lingeringSound);
		});
	}
}
