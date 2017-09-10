package erikalebenjamattias.petrimod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import erikalebenjamattias.petrimod.client.gui.GuiPianoKeyboard;
import erikalebenjamattias.petrimod.main.PetriMod;

public class NotifyClientOfPianoResetMessages {

	public static class NotifyClientOfPianoResetMessage implements IMessage {
		
		public NotifyClientOfPianoResetMessage() {}
		
		private byte pianoNoteIndex;
		
		public NotifyClientOfPianoResetMessage(byte noteIndex) {
			this.pianoNoteIndex = noteIndex;
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeByte(this.pianoNoteIndex);
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.pianoNoteIndex = buf.readByte();
		}
	}
	
	public static class NotifyClientOfPianoResetMessageHandler implements IMessageHandler<NotifyClientOfPianoResetMessage, IMessage> {

		public NotifyClientOfPianoResetMessageHandler() {}
		
		@Override
		public IMessage onMessage(NotifyClientOfPianoResetMessage message, MessageContext ctx) {
			PetriMod.proxy.clientPacketResetPianoSound(message.pianoNoteIndex);
			return null;
		}		
	}
}
