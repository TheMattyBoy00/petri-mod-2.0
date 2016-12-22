package erikalebenjamattias.petrimod.worldgen;

import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PetriOreGenerationRegistry {
	
	public static void main() {
		init();
	}
	
	public static void init() {
		registerWorldGen(new WorldGenCopper(), 1);
	}
	
	public static void registerWorldGen(IWorldGenerator worldGenClass, int weightedProbabaility) {
		GameRegistry.registerWorldGenerator(worldGenClass, weightedProbabaility);
	}

}
