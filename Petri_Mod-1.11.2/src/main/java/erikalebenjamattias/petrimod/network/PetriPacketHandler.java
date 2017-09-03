package erikalebenjamattias.petrimod.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import erikalebenjamattias.petrimod.main.Reference;

public class PetriPacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
	private static int id = 0;
	
	public static void initServer() {
		INSTANCE.registerMessage(NotifyServerOfPianoPlayMessages.NotifyServerOfPianoPlayMessageHandler.class, NotifyServerOfPianoPlayMessages.NotifyServerOfPianoPlayMessage.class, id++, Side.SERVER);
	}
	
	public static void initClient() {
		INSTANCE.registerMessage(OpenPianoGUIMessages.OpenPianoGUIMessageHandler.class, OpenPianoGUIMessages.OpenPianoGUIMessage.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(NotifyClientOfPianoPlayMessages.NotifyClientOfPianoPlayMessageHandler.class, NotifyClientOfPianoPlayMessages.NotifyClientOfPianoPlayMessage.class, id++, Side.CLIENT);
	}
}
