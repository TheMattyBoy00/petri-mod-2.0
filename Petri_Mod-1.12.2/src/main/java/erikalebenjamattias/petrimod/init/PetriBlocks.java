package erikalebenjamattias.petrimod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import erikalebenjamattias.petrimod.blocks.BlockCopperBlock;
import erikalebenjamattias.petrimod.blocks.BlockCopperOre;
import erikalebenjamattias.petrimod.blocks.BlockCorrodedCopperBlock;
import erikalebenjamattias.petrimod.blocks.BlockCorrodedCopperRoof;
import erikalebenjamattias.petrimod.blocks.BlockPetriWindow;
import erikalebenjamattias.petrimod.blocks.BlockTerracottaRoof;
import erikalebenjamattias.petrimod.blocks.BlockUncoloredConcretePowder;
import erikalebenjamattias.petrimod.blocks.BlockWhiteboard;
import erikalebenjamattias.petrimod.main.PetriMod;
import erikalebenjamattias.petrimod.main.Reference;

public class PetriBlocks {
	
	public static Block copper_ore;
	public static Block copper_block;
	public static Block terracotta_roof;
	public static Block petri_bricks;
	public static Block corroded_copper_block;
	public static Block corroded_copper_roof;
	public static Block petri_window;
	public static Block uncolored_concrete_powder;
	public static Block uncolored_concrete;
	public static BlockWhiteboard whiteboard;
	
	public static void init() {
		copper_ore = new BlockCopperOre().setUnlocalizedName("copper_ore").setHardness(3.0F).setResistance(5.0F).setCreativeTab(PetriMod.tabPetri).setRegistryName("copper_ore");
		copper_block = new BlockCopperBlock(Material.IRON, MapColor.ADOBE).setUnlocalizedName("copper_block").setHardness(4.0F).setResistance(10.0F).setCreativeTab(PetriMod.tabPetri).setRegistryName("copper_block");
		terracotta_roof = new BlockTerracottaRoof(Material.ROCK, MapColor.BROWN).setUnlocalizedName("terracotta_roof").setHardness(1.25F).setResistance(7.0F).setCreativeTab(PetriMod.tabPetri).setRegistryName("terracotta_roof");
		petri_bricks = new Block(Material.ROCK, MapColor.CLAY).setUnlocalizedName("petri_bricks").setHardness(2.0F).setResistance(10.0F).setCreativeTab(PetriMod.tabPetri).setRegistryName("petri_bricks");
		Block block = new BlockCorrodedCopperBlock(Material.IRON, MapColor.GREEN).setUnlocalizedName("corroded_copper_block").setHardness(3.0F).setResistance(10.0F).setCreativeTab(PetriMod.tabPetri).setRegistryName("corroded_copper_block");
		corroded_copper_block = block;
		corroded_copper_roof = new BlockCorrodedCopperRoof(block.getDefaultState()).setUnlocalizedName("corroded_copper_roof").setRegistryName("corroded_copper_roof");
		petri_window = new BlockPetriWindow(Material.GLASS).setUnlocalizedName("petri_window").setHardness(0.3F).setCreativeTab(PetriMod.tabPetri).setRegistryName("petri_window");
		uncolored_concrete_powder = new BlockUncoloredConcretePowder(Material.SAND).setUnlocalizedName("uncolored_concrete_powder").setHardness(0.5F).setCreativeTab(PetriMod.tabPetri).setRegistryName("uncolored_concrete_powder");
		uncolored_concrete = new Block(Material.ROCK).setUnlocalizedName("uncolored_concrete").setHardness(1.8F).setCreativeTab(PetriMod.tabPetri).setRegistryName("uncolored_concrete");
		whiteboard = (BlockWhiteboard) new BlockWhiteboard(Material.CIRCUITS).setUnlocalizedName("whiteboard").setHardness(0.2F).setCreativeTab(PetriMod.tabPetri).setRegistryName("whiteboard");
	}
	
	/*public static void register() {
		GameRegistry.register(copper_ore);
		GameRegistry.register(new ItemBlock(copper_ore).setRegistryName(copper_ore.getRegistryName()));
		GameRegistry.register(copper_block);
		GameRegistry.register(new ItemBlock(copper_block).setRegistryName(copper_block.getRegistryName()));
		GameRegistry.register(terracotta_roof);
		GameRegistry.register(new ItemBlock(terracotta_roof).setRegistryName(terracotta_roof.getRegistryName()));
		GameRegistry.register(petri_bricks);
		GameRegistry.register(new ItemBlock(petri_bricks).setRegistryName(petri_bricks.getRegistryName()));
	}*/
	
	public static void register(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
        	copper_ore,
        	copper_block,
        	terracotta_roof,
        	petri_bricks,
        	corroded_copper_block,
        	corroded_copper_roof,
        	petri_window,
        	uncolored_concrete_powder,
        	uncolored_concrete,
        	whiteboard
        );
	}
	
	public static void registerRenders() {
		registerRender(copper_ore);
		registerRender(copper_block);
		registerRender(terracotta_roof);
		registerRender(petri_bricks);
		registerRender(corroded_copper_block);
		registerRender(corroded_copper_roof);
		registerRender(petri_window);
		registerRender(uncolored_concrete_powder);
		registerRender(uncolored_concrete);
		registerRender(whiteboard);
	}
	
	public static void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
	/*public static void registerSpecialRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}*/
}