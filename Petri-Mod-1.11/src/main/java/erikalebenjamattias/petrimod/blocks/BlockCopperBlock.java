package erikalebenjamattias.petrimod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockCopperBlock extends Block {

	public BlockCopperBlock(Material materialIn) {
		super(materialIn);
		this.setHarvestLevel("pickaxe", 1);
		this.setSoundType(SoundType.METAL);
	}
}