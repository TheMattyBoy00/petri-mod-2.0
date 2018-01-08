package erikalebenjamattias.petrimod.worldgen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import erikalebenjamattias.petrimod.init.PetriBlocks;

public class WorldGenCopper implements IWorldGenerator {
	
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider ChunkProvider) {
		if(world.provider.getDimension() == 0) {
			for(int i = 0; i < 12; i++) {
				int randPosX = chunkX * 16 + rand.nextInt(16);
				int randPosY = rand.nextInt(64);
				int randPosZ = chunkZ * 16 + rand.nextInt(16);
				
				(new WorldGenMinable(PetriBlocks.copper_ore.getDefaultState(), 6)).generate(world, rand, new BlockPos(randPosX, randPosY, randPosZ));
			}
		}
	}
}