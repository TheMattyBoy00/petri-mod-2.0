package erikalebenjamattias.petrimod.main;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.Metadata;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import erikalebenjamattias.petrimod.init.PetriBlocks;
import erikalebenjamattias.petrimod.init.PetriItems;
import erikalebenjamattias.petrimod.proxy.CommonProxy;
import erikalebenjamattias.petrimod.worldgen.PetriOreGenerationRegistry;

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
        PetriBlocks.init();
        PetriBlocks.register();
        PetriItems.init();
        PetriItems.register();
        
        PetriOreGenerationRegistry.main();
	}
	
	@EventHandler
    public void init(FMLInitializationEvent event) {
		proxy.registerRenders();
		
		GameRegistry.addSmelting(PetriBlocks.copper_ore, new ItemStack(PetriItems.copper_ingot), 0.75F);
    }
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
