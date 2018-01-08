package erikalebenjamattias.petrimod.tileentity;

import erikalebenjamattias.petrimod.main.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PetriTileEntityList {

	public static void init() {
		GameRegistry.registerTileEntity(TileEntityWhiteboard.class, Reference.MOD_ID + ":whiteboard");
	}
	
}
