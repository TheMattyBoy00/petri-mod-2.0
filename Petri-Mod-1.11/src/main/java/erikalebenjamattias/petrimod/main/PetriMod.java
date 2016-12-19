package erikalebenjamattias.petrimod.main;

import erikalebenjamattias.petrimod.init.PetriBlocks;
import erikalebenjamattias.petrimod.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.Metadata;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class PetriMod {
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@Metadata
    public static ModMetadata meta;
	
	@Instance(Reference.MOD_ID)
    public static PetriMod modInstance;
	
	public static final PetriTab tabPetri = new PetriTab("tabPetri");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
    public void init(FMLInitializationEvent event) {
        PetriBlocks.init();
        PetriBlocks.register();
    }
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
