package erikalebenjamattias.petrimod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NotifyServerOfPianoStopMessages {

	public static class NotifyServerOfPianoStopMessage implements IMessage {
		
		public NotifyServerOfPianoStopMessage() {}
		
		private byte pianoLingeringSound;
		private int pianoId;
		
		public NotifyServerOfPianoStopMessage(byte lingeringSound, int pianoId) {
			this.pianoLingeringSound = lingeringSound;
			this.pianoId = pianoId;
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeByte(this.pianoLingeringSound);
			buf.writeInt(this.pianoId);
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.pianoLingeringSound = buf.readByte();
			this.pianoId = buf.readInt();
		}
	}
	
	public static class NotifyServerOfPianoStopMessageHandler implements IMessageHandler<NotifyServerOfPianoStopMessage, IMessage> {

		public NotifyServerOfPianoStopMessageHandler() {}
		
		@Override
		public IMessage onMessage(NotifyServerOfPianoStopMessage message, MessageContext ctx) {
			((WorldServer)ctx.getServerHandler().playerEntity.world).addScheduledTask(() -> {
				World world = ctx.getServerHandler().playerEntity.world;
				if(ctx.getServerHandler().playerEntity.getDistanceSqToEntity(world.getEntityByID(message.pianoId)) < 100) {
					for(EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, world.getEntityByID(message.pianoId).getEntityBoundingBox().expandXyz(30))) {
						if(player != ctx.getServerHandler().playerEntity) {
							PetriPacketHandler.INSTANCE.sendTo(new NotifyClientOfPianoStopMessages.NotifyClientOfPianoStopMessage(message.pianoLingeringSound), player);
						}
					}
				}
			});
			return null;
		}		
	}
}
