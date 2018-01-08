package erikalebenjamattias.petrimod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import erikalebenjamattias.petrimod.items.ItemCopperIngot;
import erikalebenjamattias.petrimod.items.ItemFrontClock;
import erikalebenjamattias.petrimod.items.ItemWhiteboardMarkerBlack;
import erikalebenjamattias.petrimod.items.ItemWhiteboardMarkerBlue;
import erikalebenjamattias.petrimod.items.ItemWhiteboardMarkerGreen;
import erikalebenjamattias.petrimod.items.ItemWhiteboardMarkerRed;
import erikalebenjamattias.petrimod.main.PetriMod;
import erikalebenjamattias.petrimod.main.Reference;

public class PetriItems {
	
	public static Item copper_ingot;
	public static Item terracotta_mass;
	public static Item terracotta;
	public static Item sanded_clay;
	public static Item sanded_brick;
	public static Item front_clock;
	public static Item black_whiteboard_marker;
	public static Item red_whiteboard_marker;
	public static Item green_whiteboard_marker;
	public static Item blue_whiteboard_marker;
	
	public static void init() {
		copper_ingot = new ItemCopperIngot().setUnlocalizedName("copper_ingot").setCreativeTab(PetriMod.tabPetri).setRegistryName("copper_ingot");
		terracotta_mass = new Item().setUnlocalizedName("terracotta_mass").setCreativeTab(PetriMod.tabPetri).setRegistryName("terracotta_mass");
		terracotta = new Item().setUnlocalizedName("terracotta").setCreativeTab(PetriMod.tabPetri).setRegistryName("terracotta");
		sanded_clay = new Item().setUnlocalizedName("sanded_clay").setCreativeTab(PetriMod.tabPetri).setRegistryName("sanded_clay");
		sanded_brick = new Item().setUnlocalizedName("sanded_brick").setCreativeTab(PetriMod.tabPetri).setRegistryName("sanded_brick");
		front_clock = new ItemFrontClock().setUnlocalizedName("front_clock").setCreativeTab(PetriMod.tabPetri).setRegistryName("front_clock");
		black_whiteboard_marker = new ItemWhiteboardMarkerBlack().setUnlocalizedName("whiteboard_marker_black").setCreativeTab(PetriMod.tabPetri).setRegistryName("whiteboard_marker_black");
		red_whiteboard_marker = new ItemWhiteboardMarkerRed().setUnlocalizedName("whiteboard_marker_red").setCreativeTab(PetriMod.tabPetri).setRegistryName("whiteboard_marker_red");
		green_whiteboard_marker = new ItemWhiteboardMarkerGreen().setUnlocalizedName("whiteboard_marker_green").setCreativeTab(PetriMod.tabPetri).setRegistryName("whiteboard_marker_green");
		blue_whiteboard_marker = new ItemWhiteboardMarkerBlue().setUnlocalizedName("whiteboard_marker_blue").setCreativeTab(PetriMod.tabPetri).setRegistryName("whiteboard_marker_blue");
	}
	
	/*public static void register() {
		GameRegistry.register(copper_ingot);
		GameRegistry.register(terracotta_mass);
		GameRegistry.register(terracotta);
	}*/
	
	public static void register(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
        	new ItemBlock(PetriBlocks.copper_ore).setRegistryName(PetriBlocks.copper_ore.getRegistryName()),
    		new ItemBlock(PetriBlocks.copper_block).setRegistryName(PetriBlocks.copper_block.getRegistryName()),
    		new ItemBlock(PetriBlocks.terracotta_roof).setRegistryName(PetriBlocks.terracotta_roof.getRegistryName()),
    		new ItemBlock(PetriBlocks.petri_bricks).setRegistryName(PetriBlocks.petri_bricks.getRegistryName()),
    		new ItemBlock(PetriBlocks.corroded_copper_block).setRegistryName(PetriBlocks.corroded_copper_block.getRegistryName()),
    		new ItemBlock(PetriBlocks.corroded_copper_roof).setRegistryName(PetriBlocks.corroded_copper_roof.getRegistryName()),
    		new ItemBlock(PetriBlocks.petri_window).setRegistryName(PetriBlocks.petri_window.getRegistryName()),
    		new ItemBlock(PetriBlocks.uncolored_concrete_powder).setRegistryName(PetriBlocks.uncolored_concrete_powder.getRegistryName()),
    		new ItemBlock(PetriBlocks.uncolored_concrete).setRegistryName(PetriBlocks.uncolored_concrete.getRegistryName()),
    		new ItemBlock(PetriBlocks.whiteboard).setRegistryName(PetriBlocks.whiteboard.getRegistryName()),
    		
    		copper_ingot,
    		terracotta_mass,
    		terracotta,
    		sanded_clay,
    		sanded_brick,
    		front_clock,
    		black_whiteboard_marker,
    		red_whiteboard_marker,
    		green_whiteboard_marker,
    		blue_whiteboard_marker
        );
	}
	
	public static void registerRenders() {
		registerRender(copper_ingot);
		registerRender(terracotta_mass);
		registerRender(terracotta);
		registerRender(sanded_clay);
		registerRender(sanded_brick);
		registerRender(front_clock);
		registerRender(black_whiteboard_marker);
		registerRender(red_whiteboard_marker);
		registerRender(green_whiteboard_marker);
		registerRender(blue_whiteboard_marker);
	}
	
	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
