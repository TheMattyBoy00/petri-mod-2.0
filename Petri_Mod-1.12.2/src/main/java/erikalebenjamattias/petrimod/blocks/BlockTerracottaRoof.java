package erikalebenjamattias.petrimod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.google.common.collect.Lists;

import erikalebenjamattias.petrimod.init.PetriBlocks;

public class BlockTerracottaRoof extends Block {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	//public static final PropertyDirection SECONDARY_FACING = PropertyDirection.create("secondary_facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool IS_HALF = PropertyBool.create("is_half");
	public static final PropertyEnum<EnumShape> SHAPE = PropertyEnum.<EnumShape>create("shape", EnumShape.class);
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	protected static final AxisAlignedBB AABB_NORTHEAST = new AxisAlignedBB(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
	protected static final AxisAlignedBB AABB_NORTHWEST = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 0.5F);
	protected static final AxisAlignedBB AABB_SOUTHEAST = new AxisAlignedBB(0.5F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
	protected static final AxisAlignedBB AABB_SOUTHWEST = new AxisAlignedBB(0.0F, 0.0F, 0.5F, 0.5F, 1.0F, 1.0F);
	
	public BlockTerracottaRoof(Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(SHAPE, EnumShape.STRAIGHT).withProperty(IS_HALF, Boolean.valueOf(false)));
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		state = this.getActualState(state, world, pos);
		if(this.isOuter(state)) {
			List<AxisAlignedBB> list = Lists.<AxisAlignedBB>newArrayList();
			if(state.getValue(SHAPE) == EnumShape.OUTER_RIGHT) {
				if(state.getValue(FACING) == EnumFacing.NORTH) {
					list.add(AABB_NORTH);
					list.add(AABB_SOUTHEAST);
				}
				else if(state.getValue(FACING) == EnumFacing.SOUTH) {
					list.add(AABB_SOUTH);
					list.add(AABB_NORTHWEST);
				}
				else if(state.getValue(FACING) == EnumFacing.WEST) {
					list.add(AABB_WEST);
					list.add(AABB_NORTHEAST);
				}
				else if(state.getValue(FACING) == EnumFacing.EAST) {
					list.add(AABB_EAST);
					list.add(AABB_SOUTHWEST);
				}
			}
			else if(state.getValue(SHAPE) == EnumShape.OUTER_LEFT) {
				if(state.getValue(FACING) == EnumFacing.NORTH) {
					list.add(AABB_NORTH);
					list.add(AABB_SOUTHWEST);
				}
				else if(state.getValue(FACING) == EnumFacing.SOUTH) {
					list.add(AABB_SOUTH);
					list.add(AABB_NORTHEAST);
				}
				else if(state.getValue(FACING) == EnumFacing.WEST) {
					list.add(AABB_WEST);
					list.add(AABB_SOUTHEAST);
				}
				else if(state.getValue(FACING) == EnumFacing.EAST) {
					list.add(AABB_EAST);
					list.add(AABB_NORTHWEST);
				}
			}
			for(AxisAlignedBB aabb : list) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, aabb);
			}
		}
		else {
			super.addCollisionBoxToList(state, world, pos, entityBox, collidingBoxes, entityIn, p_185477_7_);
		}
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		state = this.getActualState(state, world, pos);
		if(state.getValue(IS_HALF) && !this.isOuter(state)) {
			if(state.getValue(SHAPE) == EnumShape.INNER_LEFT) {
				switch ((EnumFacing)state.getValue(FACING)) {
				case NORTH:
					return AABB_NORTHWEST;
				case SOUTH:
					return AABB_SOUTHEAST;
				case WEST:
					return AABB_SOUTHWEST;
				case EAST:
				default:
					return AABB_NORTHEAST;
				}
			}
			else if(state.getValue(SHAPE) == EnumShape.INNER_RIGHT) {
				switch ((EnumFacing)state.getValue(FACING)) {
				case NORTH:
					return AABB_NORTHEAST;
				case SOUTH:
					return AABB_SOUTHWEST;
				case WEST:
					return AABB_NORTHWEST;
				case EAST:
				default:
					return AABB_SOUTHEAST;
				}
			}
			else {
				switch ((EnumFacing)state.getValue(FACING)) {
				case NORTH:
					return AABB_NORTH;
				case SOUTH:
					return AABB_SOUTH;
				case WEST:
					return AABB_WEST;
				case EAST:
				default:
					return AABB_EAST;
				}
			}
        }
		else {
			return FULL_BLOCK_AABB;
		}
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return !state.getValue(IS_HALF);
	}
	
	/*@Override
	public boolean isFullyOpaque(IBlockState state) {
		return !state.getValue(IS_HALF);
	}*/
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return !state.getValue(IS_HALF);
	}
	
	@Override
	public boolean causesSuffocation(IBlockState state) {
		return !state.getValue(IS_HALF);
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		state = this.getActualState(state, world, pos);
		return this.isOuter(state) ? true : super.shouldSideBeRendered(state, world, pos, side);
	}
	
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		state = this.getActualState(state, world, pos);
		return state.getValue(IS_HALF) ? false : super.doesSideBlockRendering(state, world, pos, face);
	}
	
	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		state = this.getActualState(state, world, pos);
		if(state.getValue(IS_HALF) == true) {
			if(state.getValue(SHAPE) == EnumShape.STRAIGHT || this.isOuter(state)) {
				if(state.getValue(SHAPE) == EnumShape.STRAIGHT) return side == state.getValue(FACING);
				if(state.getValue(SHAPE) == EnumShape.OUTER_LEFT) return side == state.getValue(FACING).rotateYCCW() || side == state.getValue(FACING);
				if(state.getValue(SHAPE) == EnumShape.OUTER_RIGHT) return side == state.getValue(FACING).rotateY() || side == state.getValue(FACING);
			}
		}
		return super.isSideSolid(state, world, pos, side);
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing side) {
		state = this.getActualState(state, worldIn, pos);
		if(state.getValue(IS_HALF)) {
			if(state.getValue(SHAPE) == EnumShape.STRAIGHT || this.isOuter(state)) {
				if(state.getValue(SHAPE) == EnumShape.STRAIGHT) {
					return side == state.getValue(FACING) ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
				}
				if(state.getValue(SHAPE) == EnumShape.OUTER_LEFT) {
					return side == state.getValue(FACING).rotateYCCW() || side == state.getValue(FACING) ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
				}
				if(state.getValue(SHAPE) == EnumShape.OUTER_RIGHT) {
					return side == state.getValue(FACING).rotateY() || side == state.getValue(FACING) ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
				}
			}
			else {
				return BlockFaceShape.UNDEFINED;
			}
		}
		return BlockFaceShape.SOLID;
	}
	
	public boolean isInner(IBlockState state) {
		if(state.getValue(SHAPE) == EnumShape.INNER_LEFT || state.getValue(SHAPE) == EnumShape.INNER_RIGHT) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isOuter(IBlockState state) {
		if(state.getValue(SHAPE) == EnumShape.OUTER_LEFT || state.getValue(SHAPE) == EnumShape.OUTER_RIGHT) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public EnumShape getShapeForBlockPlacement(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		if(facing == EnumFacing.NORTH) {
			if(world.getBlockState(pos.north()).getBlock() == PetriBlocks.terracotta_roof && (world.getBlockState(pos.north()).getValue(FACING) == EnumFacing.EAST || world.getBlockState(pos.north()).getValue(FACING) == EnumFacing.WEST)) {
				if(world.getBlockState(pos.north()).getValue(FACING) == EnumFacing.EAST && world.getBlockState(pos.north()).getValue(SHAPE) != EnumShape.OUTER_RIGHT && world.getBlockState(pos.north()).getValue(SHAPE) != EnumShape.INNER_LEFT) {
					return EnumShape.INNER_RIGHT;
				}
				else if(world.getBlockState(pos.north()).getValue(FACING) == EnumFacing.WEST && world.getBlockState(pos.north()).getValue(SHAPE) != EnumShape.OUTER_LEFT && world.getBlockState(pos.north()).getValue(SHAPE) != EnumShape.INNER_RIGHT) {
					return EnumShape.INNER_LEFT;
				}
			}
			else if(world.getBlockState(pos.south()).getBlock() == PetriBlocks.terracotta_roof) {
				if(world.getBlockState(pos.south()).getValue(FACING) == EnumFacing.EAST && world.getBlockState(pos.south()).getValue(SHAPE) != EnumShape.OUTER_LEFT && world.getBlockState(pos.south()).getValue(SHAPE) != EnumShape.INNER_RIGHT) {
					return EnumShape.OUTER_RIGHT;
				}
				else if(world.getBlockState(pos.south()).getValue(FACING) == EnumFacing.WEST && world.getBlockState(pos.south()).getValue(SHAPE) != EnumShape.OUTER_RIGHT && world.getBlockState(pos.south()).getValue(SHAPE) != EnumShape.INNER_LEFT) {
					return EnumShape.OUTER_LEFT;
				}
			}
		}
		else if(facing == EnumFacing.EAST) {
			if(world.getBlockState(pos.east()).getBlock() == PetriBlocks.terracotta_roof && (world.getBlockState(pos.east()).getValue(FACING) == EnumFacing.SOUTH || world.getBlockState(pos.east()).getValue(FACING) == EnumFacing.NORTH)) {
				if(world.getBlockState(pos.east()).getValue(FACING) == EnumFacing.SOUTH && world.getBlockState(pos.east()).getValue(SHAPE) != EnumShape.OUTER_RIGHT && world.getBlockState(pos.east()).getValue(SHAPE) != EnumShape.INNER_LEFT) {
					return EnumShape.INNER_RIGHT;
				}
				else if(world.getBlockState(pos.east()).getValue(FACING) == EnumFacing.NORTH && world.getBlockState(pos.east()).getValue(SHAPE) != EnumShape.OUTER_LEFT && world.getBlockState(pos.east()).getValue(SHAPE) != EnumShape.INNER_RIGHT) {
					return EnumShape.INNER_LEFT;
				}
			}
			else if(world.getBlockState(pos.west()).getBlock() == PetriBlocks.terracotta_roof) {
				if(world.getBlockState(pos.west()).getValue(FACING) == EnumFacing.SOUTH && world.getBlockState(pos.west()).getValue(SHAPE) != EnumShape.OUTER_LEFT && world.getBlockState(pos.west()).getValue(SHAPE) != EnumShape.INNER_RIGHT) {
					return EnumShape.OUTER_RIGHT;
				}
				else if(world.getBlockState(pos.west()).getValue(FACING) == EnumFacing.NORTH && world.getBlockState(pos.west()).getValue(SHAPE) != EnumShape.OUTER_RIGHT && world.getBlockState(pos.west()).getValue(SHAPE) != EnumShape.INNER_LEFT) {
					return EnumShape.OUTER_LEFT;
				}
			}
		}
		else if(facing == EnumFacing.SOUTH) {
			if(world.getBlockState(pos.south()).getBlock() == PetriBlocks.terracotta_roof && (world.getBlockState(pos.south()).getValue(FACING) == EnumFacing.WEST || world.getBlockState(pos.south()).getValue(FACING) == EnumFacing.EAST)) {
				if(world.getBlockState(pos.south()).getValue(FACING) == EnumFacing.WEST && world.getBlockState(pos.south()).getValue(SHAPE) != EnumShape.OUTER_RIGHT && world.getBlockState(pos.south()).getValue(SHAPE) != EnumShape.INNER_LEFT) {
					return EnumShape.INNER_RIGHT;
				}
				else if(world.getBlockState(pos.south()).getValue(FACING) == EnumFacing.EAST && world.getBlockState(pos.south()).getValue(SHAPE) != EnumShape.OUTER_LEFT && world.getBlockState(pos.south()).getValue(SHAPE) != EnumShape.INNER_RIGHT) {
					return EnumShape.INNER_LEFT;
				}
			}
			else if(world.getBlockState(pos.north()).getBlock() == PetriBlocks.terracotta_roof) {
				if(world.getBlockState(pos.north()).getValue(FACING) == EnumFacing.WEST && world.getBlockState(pos.north()).getValue(SHAPE) != EnumShape.OUTER_LEFT && world.getBlockState(pos.north()).getValue(SHAPE) != EnumShape.INNER_RIGHT) {
					return EnumShape.OUTER_RIGHT;
				}
				else if(world.getBlockState(pos.north()).getValue(FACING) == EnumFacing.EAST && world.getBlockState(pos.north()).getValue(SHAPE) != EnumShape.OUTER_RIGHT && world.getBlockState(pos.north()).getValue(SHAPE) != EnumShape.INNER_LEFT) {
					return EnumShape.OUTER_LEFT;
				}
			}
		}
		else if(facing == EnumFacing.WEST) {
			if(world.getBlockState(pos.west()).getBlock() == PetriBlocks.terracotta_roof && (world.getBlockState(pos.west()).getValue(FACING) == EnumFacing.NORTH || world.getBlockState(pos.west()).getValue(FACING) == EnumFacing.SOUTH)) {
				if(world.getBlockState(pos.west()).getValue(FACING) == EnumFacing.NORTH && world.getBlockState(pos.west()).getValue(SHAPE) != EnumShape.OUTER_RIGHT && world.getBlockState(pos.west()).getValue(SHAPE) != EnumShape.INNER_LEFT) {
					return EnumShape.INNER_RIGHT;
				}
				else if(world.getBlockState(pos.west()).getValue(FACING) == EnumFacing.SOUTH && world.getBlockState(pos.west()).getValue(SHAPE) != EnumShape.OUTER_LEFT && world.getBlockState(pos.west()).getValue(SHAPE) != EnumShape.INNER_RIGHT) {
					return EnumShape.INNER_LEFT;
				}
			}
			else if(world.getBlockState(pos.east()).getBlock() == PetriBlocks.terracotta_roof) {
				if(world.getBlockState(pos.east()).getValue(FACING) == EnumFacing.NORTH && world.getBlockState(pos.east()).getValue(SHAPE) != EnumShape.OUTER_LEFT && world.getBlockState(pos.east()).getValue(SHAPE) != EnumShape.INNER_RIGHT) {
					return EnumShape.OUTER_RIGHT;
				}
				else if(world.getBlockState(pos.east()).getValue(FACING) == EnumFacing.SOUTH && world.getBlockState(pos.east()).getValue(SHAPE) != EnumShape.OUTER_RIGHT && world.getBlockState(pos.east()).getValue(SHAPE) != EnumShape.INNER_LEFT) {
					return EnumShape.OUTER_LEFT;
				}
			}
		}
		return EnumShape.STRAIGHT;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(IS_HALF, world.getBlockState(pos.down()).getBlock() == PetriBlocks.terracotta_roof).withProperty(SHAPE, this.getShapeForBlockPlacement(world, pos, placer.getHorizontalFacing()));
    }
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, world, pos, blockIn, fromPos);
		world.setBlockState(pos, this.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(IS_HALF, world.getBlockState(pos.down()).getBlock() == PetriBlocks.terracotta_roof ? true : false).withProperty(SHAPE, this.getShapeForBlockPlacement(world, pos, state.getValue(FACING))));
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.withProperty(SHAPE, this.getShapeForBlockPlacement(world, pos, state.getValue(FACING)))/*.withProperty(IS_HALF, world.getBlockState(pos.down()).getBlock() == PetriBlocks.terracotta_roof ? true : false)*/;
	}
	
	/*public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.withProperty(IS_HALF, world.getBlockState(pos.down()).getBlock() == PetriBlocks.terracotta_roof ? true : false).withProperty(SHAPE, this.getShapeForBlockPlacement((World)world, pos, state.getValue(FACING)));
	}*/
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(IS_HALF, Boolean.valueOf((meta & 4) > 0)).withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

	@Override
	public int getMetaFromState(IBlockState state) {
		byte b0 = 0;
    	int i = b0 | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();

        if (((Boolean)state.getValue(IS_HALF)).booleanValue()) {
            i |= 4;
        }

        return i;
    }

	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING, IS_HALF, SHAPE});
    }
	
	public static enum EnumShape implements IStringSerializable {

		STRAIGHT("straight"),
        INNER_LEFT("inner_left"),
        INNER_RIGHT("inner_right"),
        OUTER_LEFT("outer_left"),
        OUTER_RIGHT("outer_right");
		
		private final String name;
		
		private EnumShape(String name) {
            this.name = name;
        }
		
		public String toString() {
            return this.name;
        }
		
		public String getName() {
			return name;
		}
	}
}