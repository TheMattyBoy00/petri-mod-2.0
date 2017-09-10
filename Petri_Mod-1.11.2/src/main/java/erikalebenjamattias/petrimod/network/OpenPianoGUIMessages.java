package erikalebenjamattias.petrimod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import erikalebenjamattias.petrimod.client.gui.GuiPianoKeyboard;
import erikalebenjamattias.petrimod.entity.inanimate.EntityGrandPiano;
import erikalebenjamattias.petrimod.main.PetriMod;

public class OpenPianoGUIMessages {

	public static class OpenPianoGUIMessage implements IMessage {
		
		public OpenPianoGUIMessage() {}
		
		private int pianoId;
		
		public OpenPianoGUIMessage(int pianoId) {
			this.pianoId = pianoId;
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(this.pianoId);
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.pianoId = buf.readInt();
		}
	}
	
	public static class OpenPianoGUIMessageHandler implements IMessageHandler<OpenPianoGUIMessage, IMessage> {

		public OpenPianoGUIMessageHandler() {}
		
		@Override
		public IMessage onMessage(OpenPianoGUIMessage message, MessageContext ctx) {
			/*if(ctx.side == Side.CLIENT) {
				Minecraft.getMinecraft().addScheduledTask(() -> {
					World world = Minecraft.getMinecraft().world;
					/*World world = ctx.getServerHandler().playerEntity.world;
					if(world.getEntityByID(message.playerId) instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer)world.getEntityByID(message.playerId);
					}*/
					//System.out.println("Hai");
					/*if(world.getEntityByID(message.pianoId) instanceof EntityGrandPiano) {
						Minecraft.getMinecraft().displayGuiScreen(new GuiPianoKeyboard((EntityGrandPiano)world.getEntityByID(message.pianoId)));
					}
				});
			}*/
			PetriMod.proxy.clientPacketPianoGUI(message.pianoId);
			return null;
		}		
	}
}
