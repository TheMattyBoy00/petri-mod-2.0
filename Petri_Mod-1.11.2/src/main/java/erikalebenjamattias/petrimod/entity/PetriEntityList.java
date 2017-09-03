package erikalebenjamattias.petrimod.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import erikalebenjamattias.petrimod.entity.inanimate.EntityGrandPiano;
import erikalebenjamattias.petrimod.entity.petristaff.EntityMehmet;
import erikalebenjamattias.petrimod.main.PetriMod;
import erikalebenjamattias.petrimod.main.Reference;

public class PetriEntityList {
	
	/**
	 * Called in the FMLInitializationEvent of the main mod class ({@link Mesozoicraft}.java) to actually register the entities.
	 */
	public static void mainRegistry() {
		registerEntity();
	}
	
	public static void registerEntity() {
		createEntity(new ResourceLocation(Reference.MOD_ID + ":mehmet"), EntityMehmet.class, "Mehmet", 64, 0, 0x656567, 0xf1db9a);
		createEntity(new ResourceLocation(Reference.MOD_ID + ":grand_piano"), EntityGrandPiano.class, "GrandPiano", 128, 1);
	}
	
	/**
	 * Register an entity without a spawn egg or natural spawn.
	 */
	public static void createEntity(ResourceLocation registryName, Class entityClass, String entityName, int trackingRange, int id) {
		EntityRegistry.registerModEntity(registryName, entityClass, entityName, id, PetriMod.modInstance, trackingRange, 1, true);
	}
	
	/**
	 * Register an entity without a spawn egg but with a natural spawn.
	 */
	public static void createEntity(ResourceLocation registryName, Class entityClass, String entityName, int trackingRange, int id, int weightedProb, int min, int max, EnumCreatureType type, Biome... biomes) {
		EntityRegistry.registerModEntity(registryName, entityClass, entityName, id, PetriMod.modInstance, trackingRange, 1, true);
		EntityRegistry.addSpawn(entityClass, weightedProb, min, max, type, biomes);
	}
	
	/**
	 * Register an entity with a spawn egg but without a natural spawn.
	 */
	public static void createEntity(ResourceLocation registryName, Class entityClass, String entityName, int trackingRange, int id, int solidColor, int spotColor) {
		EntityRegistry.registerModEntity(registryName, entityClass, entityName, id, PetriMod.modInstance, trackingRange, 1, true, solidColor, spotColor);
	}
	
	/**
	 * Register an entity with both a spawn egg and a natural spawn.
	 */
	public static void createEntity(ResourceLocation registryName, Class entityClass, String entityName, int trackingRange, int id, int weightedProb, int min, int max, EnumCreatureType type, int solidColor, int spotColor, Biome... biomes) {
		EntityRegistry.registerModEntity(registryName, entityClass, entityName, id, PetriMod.modInstance, trackingRange, 1, true, solidColor, spotColor);
		EntityRegistry.addSpawn(entityClass, weightedProb, min, max, type, biomes);
	}
}