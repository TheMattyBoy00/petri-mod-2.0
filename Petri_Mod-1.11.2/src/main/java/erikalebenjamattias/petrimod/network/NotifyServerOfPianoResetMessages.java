package erikalebenjamattias.petrimod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NotifyServerOfPianoResetMessages {

	public static class NotifyServerOfPianoResetMessage implements IMessage {
		
		public NotifyServerOfPianoResetMessage() {}
		
		private byte pianoNoteIndex;
		private int pianoId;
		
		public NotifyServerOfPianoResetMessage(byte noteIndex, int pianoId) {
			this.pianoNoteIndex = noteIndex;
			this.pianoId = pianoId;
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeByte(this.pianoNoteIndex);
			buf.writeInt(this.pianoId);
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.pianoNoteIndex = buf.readByte();
			this.pianoId = buf.readInt();
		}
	}
	
	public static class NotifyServerOfPianoResetMessageHandler implements IMessageHandler<NotifyServerOfPianoResetMessage, IMessage> {

		public NotifyServerOfPianoResetMessageHandler() {}
		
		@Override
		public IMessage onMessage(NotifyServerOfPianoResetMessage message, MessageContext ctx) {
			((WorldServer)ctx.getServerHandler().playerEntity.world).addScheduledTask(() -> {
				World world = ctx.getServerHandler().playerEntity.world;
				if(ctx.getServerHandler().playerEntity.getDistanceSqToEntity(world.getEntityByID(message.pianoId)) < 100) {
					for(EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, world.getEntityByID(message.pianoId).getEntityBoundingBox().expandXyz(30))) {
						if(player != ctx.getServerHandler().playerEntity) {
							PetriPacketHandler.INSTANCE.sendTo(new NotifyClientOfPianoResetMessages.NotifyClientOfPianoResetMessage(message.pianoNoteIndex), player);
						}
					}
				}
			});
			return null;
		}		
	}
}
