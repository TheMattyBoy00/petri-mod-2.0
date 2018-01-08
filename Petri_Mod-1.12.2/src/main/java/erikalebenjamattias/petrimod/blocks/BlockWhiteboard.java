package erikalebenjamattias.petrimod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import erikalebenjamattias.petrimod.init.PetriBlocks;
import erikalebenjamattias.petrimod.items.ItemWhiteboardMarker;
import erikalebenjamattias.petrimod.tileentity.TileEntityWhiteboard;

public class BlockWhiteboard extends Block implements ITileEntityProvider {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.0F, 0.0F, 0.9375F, 1.0F, 1.0F, 1.0F);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.9375F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.0625F, 1.0F, 1.0F);
	
	public BlockWhiteboard(Material materialIn) {
		super(materialIn);
		this.setSoundType(SoundType.METAL);
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
	public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

	@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
	
	/**
	 * Returns if there is an adjacent whiteboard to the given one, to a direction determined by the index parameter.
	 * An index of 0 checks upward, index 1 checks to the left, index 2 down and index 3 to the right.
	 */
	public boolean isAdjacentWhiteboardAt(int index, IBlockAccess world, IBlockState state, BlockPos pos) {
		index %= 4;
		BlockPos blockpos;
		switch(index) {
		case 0:
			blockpos = pos.offset(EnumFacing.UP);
			return world.getBlockState(blockpos).getBlock() == PetriBlocks.whiteboard && world.getBlockState(blockpos).getValue(FACING) == state.getValue(FACING);
		case 1:
			blockpos = pos.offset(state.getValue(FACING).rotateYCCW());
			return world.getBlockState(blockpos).getBlock() == PetriBlocks.whiteboard && world.getBlockState(blockpos).getValue(FACING) == state.getValue(FACING);
		case 2:
			blockpos = pos.offset(EnumFacing.DOWN);
			return world.getBlockState(blockpos).getBlock() == PetriBlocks.whiteboard && world.getBlockState(blockpos).getValue(FACING) == state.getValue(FACING);
		case 3:
			blockpos = pos.offset(state.getValue(FACING).rotateY());
			return world.getBlockState(blockpos).getBlock() == PetriBlocks.whiteboard && world.getBlockState(blockpos).getValue(FACING) == state.getValue(FACING);
		}
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(facing == state.getValue(FACING) && worldIn.getTileEntity(pos) instanceof TileEntityWhiteboard) {
			TileEntityWhiteboard te = (TileEntityWhiteboard)worldIn.getTileEntity(pos);
			int x;
			int y = (int)(hitY * 16);
			
			if(worldIn.getBlockState(pos).getValue(FACING) == EnumFacing.NORTH) {
				x = (int)(hitX * 16);
			}
			else if(worldIn.getBlockState(pos).getValue(FACING) == EnumFacing.EAST) {
				x = (int)(hitZ * 16);
			}
			else if(worldIn.getBlockState(pos).getValue(FACING) == EnumFacing.SOUTH) {
				x = (int)((1 - hitX) * 16);
			}
			else {
				x = (int)((1 - hitZ) * 16);
			}
			
			if(playerIn.getHeldItem(hand).getItem() instanceof ItemWhiteboardMarker) {
				ItemWhiteboardMarker item = (ItemWhiteboardMarker)playerIn.getHeldItem(hand).getItem();
				te.colorPixel(item.getColor(), x, y);
				/*if(worldIn.getBlockState(pos).getValue(FACING) == EnumFacing.NORTH) {
					te.colorPixel(item.getColor(), (int)(hitX * 16), (int)(hitY * 16));
					x = (int)(hitX * 16);
				}
				else if(worldIn.getBlockState(pos).getValue(FACING) == EnumFacing.EAST) {
					te.colorPixel(item.getColor(), (int)(hitZ * 16), (int)(hitY * 16));
					x = (int)(hitZ * 16);
				}
				else if(worldIn.getBlockState(pos).getValue(FACING) == EnumFacing.SOUTH) {
					te.colorPixel(item.getColor(), (int)((1 - hitX) * 16), (int)(hitY * 16));
					x = (int)((1 - hitX) * 16);
				}
				else if(worldIn.getBlockState(pos).getValue(FACING) == EnumFacing.WEST) {
					te.colorPixel(item.getColor(), (int)((1 - hitZ) * 16), (int)(hitY * 16));
					x = (int)((1 - hitZ) * 16);
				}*/
			}
			else {
				te.erasePixel(x, y);
				/*if(worldIn.getBlockState(pos).getValue(FACING) == EnumFacing.NORTH) {
					te.erasePixel((int)(hitX * 16), (int)(hitY * 16));
				}
				else if(worldIn.getBlockState(pos).getValue(FACING) == EnumFacing.EAST) {
					te.erasePixel((int)(hitZ * 16), (int)(hitY * 16));
				}
				else if(worldIn.getBlockState(pos).getValue(FACING) == EnumFacing.SOUTH) {
					te.erasePixel((int)((1 - hitX) * 16), (int)(hitY * 16));
				}
				else if(worldIn.getBlockState(pos).getValue(FACING) == EnumFacing.WEST) {
					te.erasePixel((int)((1 - hitZ) * 16), (int)(hitY * 16));
				}*/
			}
			if(worldIn.isRemote) {
				ReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), 0, 58);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityWhiteboard();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityWhiteboard();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        for (EnumFacing enumfacing : FACING.getAllowedValues()) {
            if (this.canPlaceAt(worldIn, pos, enumfacing)) {
                return true;
            }
        }

        return false;
    }

    private boolean canPlaceAt(World worldIn, BlockPos pos, EnumFacing facing) {
        BlockPos blockpos = pos.offset(facing.getOpposite());
        IBlockState iblockstate = worldIn.getBlockState(blockpos);
        Block block = iblockstate.getBlock();
        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, blockpos, facing);

        if (facing != EnumFacing.UP && facing != EnumFacing.DOWN) {
            return !isExceptBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID;
        }
        else {
            return false;
        }
    }
	
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        if (this.canPlaceAt(worldIn, pos, facing)) {
            return this.getDefaultState().withProperty(FACING, facing);
        }
        else {
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
                if (this.canPlaceAt(worldIn, pos, enumfacing)) {
                    return this.getDefaultState().withProperty(FACING, enumfacing);
                }
            }

            return this.getDefaultState();
        }
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
