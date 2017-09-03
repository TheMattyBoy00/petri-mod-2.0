package erikalebenjamattias.petrimod.entity.ai;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import erikalebenjamattias.petrimod.entity.petristaff.EntityMehmet;

public class EntityAITeleportBetweenRooms extends EntityAIBase {

	private EntityMehmet theMehmet;
	private int executionChance;
	private BlockPos destPos;
	
	public EntityAITeleportBetweenRooms(EntityMehmet mehmet, int chance) {
		this.theMehmet = mehmet;
		this.executionChance = chance;
		this.setMutexBits(3);
	}
	
	@Override
	public boolean shouldExecute() {
		if(this.theMehmet.getRNG().nextInt(this.executionChance) == 0) {
			if(this.theMehmet.isEnclosedRoom(this.theMehmet.getPosition().offset(EnumFacing.UP))) {
				if(this.tryFindEnclosedRoom()) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void startExecuting() {
		this.theMehmet.setLocationAndAngles(this.destPos.getX() + 0.5, this.destPos.getY(), this.destPos.getZ() + 0.5, this.theMehmet.rotationYaw, this.theMehmet.rotationPitch);
	}
	
	private boolean isEmptyBlock(BlockPos pos) {
        IBlockState iblockstate = this.theMehmet.world.getBlockState(pos);
        return iblockstate.getMaterial() == Material.AIR ? true : !iblockstate.isFullCube();
    }
	
	private boolean tryFindEnclosedRoom() {
		int x = this.theMehmet.getPosition().getX() + this.theMehmet.getRNG().nextInt(101) - 50;
		int y = this.theMehmet.getPosition().getY() + 1 + this.theMehmet.getRNG().nextInt(41) - 20;
		int z = this.theMehmet.getPosition().getZ() + this.theMehmet.getRNG().nextInt(101) - 50;
		if(this.theMehmet.world.getBlockState(new BlockPos(x, y - 1, z)).isSideSolid(this.theMehmet.world, new BlockPos(x, y - 1, z), EnumFacing.UP) && this.isEmptyBlock(new BlockPos(x, y, z)) && this.isEmptyBlock(new BlockPos(x, y + 1, z))) {
			if(this.theMehmet.isEnclosedRoom(new BlockPos(x, y, z))) {
				this.destPos = new BlockPos(x, y, z);
				return true;
			}
		}
		return false;
	}
}