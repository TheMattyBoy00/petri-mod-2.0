package erikalebenjamattias.petrimod.main;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.Metadata;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import erikalebenjamattias.petrimod.entity.PetriEntityList;
import erikalebenjamattias.petrimod.init.PetriBlocks;
import erikalebenjamattias.petrimod.init.PetriItems;
import erikalebenjamattias.petrimod.init.PetriSounds;
import erikalebenjamattias.petrimod.network.PetriPacketHandler;
import erikalebenjamattias.petrimod.proxy.CommonProxy;
import erikalebenjamattias.petrimod.tileentity.PetriTileEntityList;
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
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
        PetriBlocks.init();
        //PetriBlocks.register();
        PetriItems.init();
        //PetriItems.register();
        PetriSounds.init();
		PetriPacketHandler.init();
		PetriTileEntityList.init();
        
        PetriOreGenerationRegistry.main();
	}
	
	@Mod.EventHandler
    public void init(FMLInitializationEvent event) {
		proxy.init();
		
		PetriEntityList.mainRegistry();
		
		/*GameRegistry.addShapedRecipe(new ItemStack(PetriBlocks.copper_block), "iii", "iii", "iii", 'i', PetriItems.copper_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(PetriItems.copper_ingot, 9), "b", 'b', PetriBlocks.copper_block);
		GameRegistry.addShapedRecipe(new ItemStack(PetriItems.terracotta_mass, 8), "ccc", "cnc", "ccc", 'c', Items.CLAY_BALL, 'n', Items.field_191525_da);
		GameRegistry.addShapedRecipe(new ItemStack(PetriBlocks.terracotta_roof), "tt", "tt", 't', PetriItems.terracotta);*/
		
		GameRegistry.addSmelting(PetriBlocks.copper_ore, new ItemStack(PetriItems.copper_ingot), 0.75F);
		GameRegistry.addSmelting(PetriItems.terracotta_mass, new ItemStack(PetriItems.terracotta), 0.35F);
		GameRegistry.addSmelting(PetriItems.sanded_clay, new ItemStack(PetriItems.sanded_brick), 0.35F);
    }
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@Mod.EventBusSubscriber
	public static class EventHandler {
		
		@SubscribeEvent
	    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
	        PetriBlocks.register(event);
	    }
		
		@SubscribeEvent
	    public static void registerItems(final RegistryEvent.Register<Item> event) {
	        PetriItems.register(event);
	    }
		
		@SubscribeEvent
	    public static void registerSounds(final RegistryEvent.Register<SoundEvent> event) {
	        PetriSounds.register(event);
	    }
	}
	
}
