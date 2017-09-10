package erikalebenjamattias.petrimod.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import erikalebenjamattias.petrimod.main.Reference;

public class PetriPacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
	private static int id = 0;
	
	/*public static void initServer() {
		
	}*/
	
	public static void init() {
		INSTANCE.registerMessage(OpenPianoGUIMessages.OpenPianoGUIMessageHandler.class, OpenPianoGUIMessages.OpenPianoGUIMessage.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(NotifyServerOfPianoPlayMessages.NotifyServerOfPianoPlayMessageHandler.class, NotifyServerOfPianoPlayMessages.NotifyServerOfPianoPlayMessage.class, id++, Side.SERVER);
		INSTANCE.registerMessage(NotifyClientOfPianoPlayMessages.NotifyClientOfPianoPlayMessageHandler.class, NotifyClientOfPianoPlayMessages.NotifyClientOfPianoPlayMessage.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(NotifyServerOfPianoResetMessages.NotifyServerOfPianoResetMessageHandler.class, NotifyServerOfPianoResetMessages.NotifyServerOfPianoResetMessage.class, id++, Side.SERVER);
		INSTANCE.registerMessage(NotifyClientOfPianoResetMessages.NotifyClientOfPianoResetMessageHandler.class, NotifyClientOfPianoResetMessages.NotifyClientOfPianoResetMessage.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(NotifyServerOfPianoStopMessages.NotifyServerOfPianoStopMessageHandler.class, NotifyServerOfPianoStopMessages.NotifyServerOfPianoStopMessage.class, id++, Side.SERVER);
		INSTANCE.registerMessage(NotifyClientOfPianoStopMessages.NotifyClientOfPianoStopMessageHandler.class, NotifyClientOfPianoStopMessages.NotifyClientOfPianoStopMessage.class, id++, Side.CLIENT);
	}
}
