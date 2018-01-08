package erikalebenjamattias.petrimod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPetriWindow extends Block {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.0F, 0.0F, 0.0625F, 1.0F, 1.0F, 0.1875F);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.0F, 0.0F, 0.8125F, 1.0F, 1.0F, 0.9375F);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.0625F, 0.0F, 0.0F, 0.1875F, 1.0F, 1.0F);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.8125F, 0.0F, 0.0F, 0.9375F, 1.0F, 1.0F);

	public BlockPetriWindow(Material materialIn) {
		super(materialIn);
		this.setSoundType(SoundType.GLASS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		switch ((EnumFacing)state.getValue(FACING)) {
		default:
		case NORTH:
			return AABB_NORTH;
		case SOUTH:
			return AABB_SOUTH;
		case WEST:
			return AABB_WEST;
		case EAST:
			return AABB_EAST;
		}
	}

	@Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

	@Override
	public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

	@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public int quantityDropped(Random random) {
        return 0;
    }

	@Override
    protected boolean canSilkHarvest() {
        return true;
    }

	/*@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
    }

	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        this.checkForDrop(worldIn, pos, state);
    }

    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos)) {
            worldIn.destroyBlock(pos, false);
            return false;
        }
        return true;
    }

    private boolean canBlockStay(World worldIn, BlockPos pos) {
    	IBlockState state = worldIn.getBlockState(pos);
    	try {
        	return worldIn.isSideSolid(pos.down(), EnumFacing.UP) || worldIn.isSideSolid(pos.up(), EnumFacing.DOWN) || worldIn.isSideSolid(pos.offset(((EnumFacing)state.getValue(FACING)).rotateY()), ((EnumFacing)state.getValue(FACING)).rotateYCCW()) || worldIn.isSideSolid(pos.offset(((EnumFacing)state.getValue(FACING)).rotateYCCW()), ((EnumFacing)state.getValue(FACING)).rotateY());
    	} catch(IllegalArgumentException e) {
    		return false;
    	}
    }*/
	
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

	@Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
    }
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

}
