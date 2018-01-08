package erikalebenjamattias.petrimod.blocks;

import java.util.Random;

import erikalebenjamattias.petrimod.init.PetriBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCopperBlock extends Block {

	public BlockCopperBlock(Material materialIn, MapColor color) {
		super(materialIn, color);
		this.setHarvestLevel("pickaxe", 1);
		this.setSoundType(SoundType.METAL);
		this.setTickRandomly(true);
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon) {
		return true;
	}
	
	protected void tryCorrode(World worldIn, BlockPos pos) {

        for (EnumFacing enumfacing : EnumFacing.values()) {
            if (enumfacing != EnumFacing.DOWN) {
                BlockPos blockpos = pos.offset(enumfacing);

                if (worldIn.getBlockState(blockpos).getMaterial() == Material.WATER) {
                    worldIn.setBlockState(pos, PetriBlocks.corroded_copper_block.getDefaultState(), 3);
                    break;
                }
            }
        }
    }
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		this.tryCorrode(worldIn, pos);
	}
}