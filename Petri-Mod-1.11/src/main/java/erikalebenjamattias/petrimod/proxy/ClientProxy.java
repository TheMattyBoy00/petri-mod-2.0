package erikalebenjamattias.petrimod.proxy;

import erikalebenjamattias.petrimod.init.PetriBlocks;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		PetriBlocks.registerRenders();
	}
}
