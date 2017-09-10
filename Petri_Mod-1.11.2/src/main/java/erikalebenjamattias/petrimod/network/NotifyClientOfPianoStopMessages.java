package erikalebenjamattias.petrimod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import erikalebenjamattias.petrimod.main.PetriMod;

public class NotifyClientOfPianoStopMessages {

	public static class NotifyClientOfPianoStopMessage implements IMessage {
		
		public NotifyClientOfPianoStopMessage() {}
		
		private byte pianoLingeringSound;
		
		public NotifyClientOfPianoStopMessage(byte lingeringSound) {
			this.pianoLingeringSound = lingeringSound;
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeByte(this.pianoLingeringSound);
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.pianoLingeringSound = buf.readByte();
		}
	}
	
	public static class NotifyClientOfPianoStopMessageHandler implements IMessageHandler<NotifyClientOfPianoStopMessage, IMessage> {

		public NotifyClientOfPianoStopMessageHandler() {}
		
		@Override
		public IMessage onMessage(NotifyClientOfPianoStopMessage message, MessageContext ctx) {
			PetriMod.proxy.clientPacketStopPianoSound(message.pianoLingeringSound);
			return null;
		}		
	}
}
