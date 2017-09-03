package erikalebenjamattias.petrimod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import erikalebenjamattias.petrimod.items.ItemCopperIngot;
import erikalebenjamattias.petrimod.main.PetriMod;
import erikalebenjamattias.petrimod.main.Reference;

public class PetriItems {
	
	public static Item copper_ingot;
	public static Item terracotta_mass;
	public static Item terracotta;
	
	public static void init() {
		copper_ingot = new ItemCopperIngot().setUnlocalizedName("copper_ingot").setCreativeTab(PetriMod.tabPetri).setRegistryName("copper_ingot");
		terracotta_mass = new Item().setUnlocalizedName("terracotta_mass").setCreativeTab(PetriMod.tabPetri).setRegistryName("terracotta_mass");
		terracotta = new Item().setUnlocalizedName("terracotta").setCreativeTab(PetriMod.tabPetri).setRegistryName("terracotta");
	}
	
	public static void register() {
		GameRegistry.register(copper_ingot);
		GameRegistry.register(terracotta_mass);
		GameRegistry.register(terracotta);
	}
	
	public static void registerRenders() {
		registerRender(copper_ingot);
		registerRender(terracotta_mass);
		registerRender(terracotta);
	}
	
	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
