package erikalebenjamattias.petrimod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import erikalebenjamattias.petrimod.main.PetriMod;
import erikalebenjamattias.petrimod.main.Reference;

public class PetriItems {
	
	public static Item copper_ingot;
	
	public static void init() {
		copper_ingot = new Item().setUnlocalizedName("copper_ingot").setCreativeTab(PetriMod.tabPetri).setRegistryName("copper_ingot");
	}
	
	public static void register() {
		GameRegistry.register(copper_ingot);
	}
	
	public static void registerRenders() {
		registerRender(copper_ingot);
	}
	
	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
