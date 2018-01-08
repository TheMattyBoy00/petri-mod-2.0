package erikalebenjamattias.petrimod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockCorrodedCopperBlock extends Block {

	public BlockCorrodedCopperBlock(Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
		this.setHarvestLevel("pickaxe", 1);
		this.setSoundType(SoundType.METAL);
	}

}
