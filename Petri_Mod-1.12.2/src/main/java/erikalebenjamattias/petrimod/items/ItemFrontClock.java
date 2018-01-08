package erikalebenjamattias.petrimod.items;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import erikalebenjamattias.petrimod.entity.inanimate.EntityFrontClock;

public class ItemFrontClock extends Item {

	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);
        BlockPos blockpos = pos.offset(facing);
        
        if(hitY < 0.5F) {
        	blockpos = blockpos.offset(EnumFacing.DOWN);
        }
        if(facing == EnumFacing.NORTH && hitX > 0.5) {
        	blockpos = blockpos.offset(EnumFacing.EAST);
        }
        if(facing == EnumFacing.EAST && hitZ > 0.5) {
        	blockpos = blockpos.offset(EnumFacing.SOUTH);
        }
        if(facing == EnumFacing.SOUTH && hitX < 0.5) {
        	blockpos = blockpos.offset(EnumFacing.WEST);
        }
        if(facing == EnumFacing.WEST && hitZ < 0.5) {
        	blockpos = blockpos.offset(EnumFacing.NORTH);
        }

        if (facing != EnumFacing.DOWN && facing != EnumFacing.UP && player.canPlayerEdit(blockpos, facing, itemstack)) {
            EntityHanging entityhanging = this.createEntity(worldIn, blockpos, facing);

            if (entityhanging != null && entityhanging.onValidSurface()) {
                if (!worldIn.isRemote) {
                    entityhanging.playPlaceSound();
                    worldIn.spawnEntity(entityhanging);
                }

                itemstack.shrink(1);
            }

            return EnumActionResult.SUCCESS;
        }
        else {
            return EnumActionResult.FAIL;
        }
    }

    @Nullable
    private EntityHanging createEntity(World worldIn, BlockPos pos, EnumFacing clickedSide) {
        return new EntityFrontClock(worldIn, pos, clickedSide);
    }

}
