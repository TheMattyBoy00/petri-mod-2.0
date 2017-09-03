package erikalebenjamattias.petrimod.proxy;

import erikalebenjamattias.petrimod.network.PetriPacketHandler;

public class ServerProxy extends CommonProxy {

	@Override
	public void registerEntityRenders() {
        PetriPacketHandler.initServer();
	}
}
