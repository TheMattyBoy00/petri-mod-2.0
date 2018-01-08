package erikalebenjamattias.petrimod.blocks;

import erikalebenjamattias.petrimod.main.PetriMod;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockCorrodedCopperRoof extends BlockStairs {

	public BlockCorrodedCopperRoof(IBlockState modelState) {
		super(modelState);
		this.setCreativeTab(PetriMod.tabPetri);
	}

}
