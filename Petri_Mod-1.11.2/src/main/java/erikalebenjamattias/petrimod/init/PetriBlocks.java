package erikalebenjamattias.petrimod.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import erikalebenjamattias.petrimod.blocks.BlockCopperBlock;
import erikalebenjamattias.petrimod.blocks.BlockCopperOre;
import erikalebenjamattias.petrimod.blocks.BlockTerracottaRoof;
import erikalebenjamattias.petrimod.main.PetriMod;
import erikalebenjamattias.petrimod.main.Reference;

public class PetriBlocks {
	
	public static Block copper_ore;
	public static Block copper_block;
	public static Block terracotta_roof;
	public static Block petri_bricks;
	
	public static void init() {
		copper_ore = new BlockCopperOre().setUnlocalizedName("copper_ore").setHardness(3.0F).setResistance(5.0F).setCreativeTab(PetriMod.tabPetri).setRegistryName("copper_ore");
		copper_block = new BlockCopperBlock(Material.IRON, MapColor.ADOBE).setUnlocalizedName("copper_block").setHardness(4.0F).setResistance(10.0F).setCreativeTab(PetriMod.tabPetri).setRegistryName("copper_block");
		terracotta_roof = new BlockTerracottaRoof(Material.ROCK, MapColor.BROWN).setUnlocalizedName("terracotta_roof").setHardness(1.25F).setResistance(7.0F).setCreativeTab(PetriMod.tabPetri).setRegistryName("terracotta_roof");
		petri_bricks = new Block(Material.ROCK, MapColor.CLAY).setUnlocalizedName("petri_bricks").setHardness(2.0F).setResistance(10.0F).setCreativeTab(PetriMod.tabPetri).setRegistryName("petri_bricks");
	}
	
	public static void register() {
		GameRegistry.register(copper_ore);
		GameRegistry.register(new ItemBlock(copper_ore).setRegistryName(copper_ore.getRegistryName()));
		GameRegistry.register(copper_block);
		GameRegistry.register(new ItemBlock(copper_block).setRegistryName(copper_block.getRegistryName()));
		GameRegistry.register(terracotta_roof);
		GameRegistry.register(new ItemBlock(terracotta_roof).setRegistryName(terracotta_roof.getRegistryName()));
		GameRegistry.register(petri_bricks);
		GameRegistry.register(new ItemBlock(petri_bricks).setRegistryName(petri_bricks.getRegistryName()));
	}
	
	public static void registerRenders() {
		registerRender(copper_ore);
		registerRender(copper_block);
		registerRender(terracotta_roof);
		registerRender(petri_bricks);
	}
	
	public static void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}