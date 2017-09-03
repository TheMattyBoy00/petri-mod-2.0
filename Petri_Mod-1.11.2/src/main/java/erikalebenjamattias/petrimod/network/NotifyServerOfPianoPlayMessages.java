package erikalebenjamattias.petrimod.network;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NotifyServerOfPianoPlayMessages {

	public static class NotifyServerOfPianoPlayMessage implements IMessage {
		
		public NotifyServerOfPianoPlayMessage() {}
		
		private byte pianoNoteIndex;
		private float pianoNoteVelocity;
		private int pianoId;
		private int playerNotToPlayForId;
		
		public NotifyServerOfPianoPlayMessage(byte noteIndex, float noteVelocity, int pianoId, int playerId) {
			this.pianoNoteIndex = noteIndex;
			this.pianoNoteVelocity = noteVelocity;
			this.pianoId = pianoId;
			this.playerNotToPlayForId = playerId;
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeByte(this.pianoNoteIndex);
			buf.writeFloat(this.pianoNoteVelocity);
			buf.writeInt(this.pianoId);
			buf.writeInt(this.playerNotToPlayForId);
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			this.pianoNoteIndex = buf.readByte();
			this.pianoNoteVelocity = buf.readFloat();
			this.pianoId = buf.readInt();
			this.playerNotToPlayForId = buf.readInt();
		}
	}
	
	public static class NotifyServerOfPianoPlayMessageHandler implements IMessageHandler<NotifyServerOfPianoPlayMessage, IMessage> {

		public NotifyServerOfPianoPlayMessageHandler() {}
		
		@Override
		public IMessage onMessage(NotifyServerOfPianoPlayMessage message, MessageContext ctx) {
			((WorldServer)ctx.getServerHandler().playerEntity.world).addScheduledTask(() -> {
				World world = ctx.getServerHandler().playerEntity.world;
				for(EntityPlayerMP player : world.getPlayers(EntityPlayerMP.class, new Predicate<EntityPlayerMP>() {
	                public boolean apply(@Nullable EntityPlayerMP p_apply_1_) {
	                    return world.getEntityByID(message.pianoId).getDistanceSqToEntity(p_apply_1_) < 900.0D && p_apply_1_ != world.getEntityByID(message.playerNotToPlayForId);
	                }
	            })) {
					//System.out.println(player);
					PetriPacketHandler.INSTANCE.sendTo(new NotifyClientOfPianoPlayMessages.NotifyClientOfPianoPlayMessage(message.pianoNoteIndex, message.pianoNoteVelocity, message.pianoId), player);
				}
			});
			return null;
		}		
	}
}
