package erikalebenjamattias.petrimod.init;

import erikalebenjamattias.petrimod.main.PetriMod;
import erikalebenjamattias.petrimod.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PetriBlocks {
	
	public static Block copper_ore;
	
	public static void init() {
		copper_ore = new BlockOre().setUnlocalizedName("copperOre").setHardness(3.0F).setResistance(5.0F).setCreativeTab(PetriMod.tabPetri).setRegistryName("copperOre");
	}
	
	public static void register() {
		GameRegistry.register(copper_ore);
	}
	
	public static void registerRenders() {
		registerRender(copper_ore);
	}
	
	public static void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
	
}