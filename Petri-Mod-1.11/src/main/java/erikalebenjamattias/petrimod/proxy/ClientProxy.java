package erikalebenjamattias.petrimod.proxy;

import erikalebenjamattias.petrimod.init.PetriBlocks;
import erikalebenjamattias.petrimod.init.PetriItems;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		PetriBlocks.registerRenders();
		PetriItems.registerRenders();
	}
}
