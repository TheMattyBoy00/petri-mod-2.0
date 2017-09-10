package erikalebenjamattias.petrimod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import erikalebenjamattias.petrimod.client.gui.GuiPianoKeyboard;
import erikalebenjamattias.petrimod.main.PetriMod;

public class NotifyClientOfPianoPlayMessages {

	public static class NotifyClientOfPianoPlayMessage implements IMessage {
		
		public NotifyClientOfPianoPlayMessage() {}
		
		private byte pianoNoteIndex;
		private float pianoNoteVelocity;
		private int pianoId;
		
		public NotifyClientOfPianoPlayMessage(byte noteIndex, float noteVelocity, int pianoId) {
			//System.out.println("Hello World!");
			this.pianoNoteIndex = noteIndex;
			this.pianoNoteVelocity = noteVelocity;
			this.pianoId = pianoId;
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeByte(this.pianoNoteIndex);
			buf.writeFloat(this.pianoNoteVelocity);
			buf.writeInt(this.pianoId);
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.pianoNoteIndex = buf.readByte();
			this.pianoNoteVelocity = buf.readFloat();
			this.pianoId = buf.readInt();
		}
	}
	
	public static class NotifyClientOfPianoPlayMessageHandler implements IMessageHandler<NotifyClientOfPianoPlayMessage, IMessage> {

		public NotifyClientOfPianoPlayMessageHandler() {}
		
		@Override
		public IMessage onMessage(NotifyClientOfPianoPlayMessage message, MessageContext ctx) {
			/*if(ctx.side == Side.CLIENT) {
				System.out.println("Hello");
				Minecraft.getMinecraft().addScheduledTask(() -> {
					Minecraft.getMinecraft().getSoundHandler().playSound(GuiPianoKeyboard.pianoSound[message.pianoNoteIndex].setEntity(Minecraft.getMinecraft().world.getEntityByID(message.pianoId)).setVolume(message.pianoNoteVelocity));
				});
			}*/
			PetriMod.proxy.clientPacketPlayPianoSound(message.pianoNoteIndex, message.pianoNoteVelocity, message.pianoId);
			return null;
		}		
	}
}
