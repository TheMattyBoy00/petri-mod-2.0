package erikalebenjamattias.petrimod.blocks;

import erikalebenjamattias.petrimod.init.PetriBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUncoloredConcretePowder extends BlockFalling {

	public BlockUncoloredConcretePowder(Material materialIn) {
		super(materialIn);
		this.setSoundType(SoundType.SAND);
	}

    @Override
	public void onEndFalling(World worldIn, BlockPos pos, IBlockState p_176502_3_, IBlockState p_176502_4_) {
        if (p_176502_4_.getMaterial().isLiquid()) {
            worldIn.setBlockState(pos, PetriBlocks.uncolored_concrete.getDefaultState(), 3);
        }
    }

    protected boolean tryTouchWater(World worldIn, BlockPos pos, IBlockState state) {
        boolean flag = false;

        for (EnumFacing enumfacing : EnumFacing.values()) {
            if (enumfacing != EnumFacing.DOWN) {
                BlockPos blockpos = pos.offset(enumfacing);

                if (worldIn.getBlockState(blockpos).getMaterial() == Material.WATER) {
                    flag = true;
                    break;
                }
            }
        }

        if (flag) {
            worldIn.setBlockState(pos, PetriBlocks.uncolored_concrete.getDefaultState(), 3);
        }

        return flag;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.tryTouchWater(worldIn, pos, state)) {
            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!this.tryTouchWater(worldIn, pos, state)) {
            super.onBlockAdded(worldIn, pos, state);
        }
    }

}
