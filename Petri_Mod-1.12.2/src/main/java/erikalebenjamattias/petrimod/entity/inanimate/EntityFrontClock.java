package erikalebenjamattias.petrimod.entity.inanimate;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import erikalebenjamattias.petrimod.init.PetriItems;

public class EntityFrontClock extends EntityHanging implements IEntityAdditionalSpawnData {

	public EntityFrontClock(World worldIn) {
		super(worldIn);
	}
	
	public EntityFrontClock(World worldIn, BlockPos pos, EnumFacing side) {
        super(worldIn, pos);
        this.updateFacingWithBoundingBox(side);
    }

	@Override
	public int getWidthPixels() {
		return 32;
	}

	@Override
	public int getHeightPixels() {
		return 32;
	}

	@Override
	public void onBroken(Entity brokenEntity) {
		if (this.world.getGameRules().getBoolean("doEntityDrops")) {
            this.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 1.0F, 0.75F);

            if (brokenEntity instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer)brokenEntity;

                if (entityplayer.capabilities.isCreativeMode) {
                    return;
                }
            }

            this.entityDropItem(new ItemStack(PetriItems.front_clock), 0.0F);
        }
	}

	@Override
	public void playPlaceSound() {
		this.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 1.0F, 0.5F);
	}
	
	@Override
	protected void updateBoundingBox() {
		super.updateBoundingBox();
        double d0 = (double)this.hangingPosition.getX() + 0.5D;
        double d1 = (double)this.hangingPosition.getY() + 0.5D;
        double d2 = (double)this.hangingPosition.getZ() + 0.5D;
        this.posX = d0;
        this.posY = d1;
        this.posZ = d2;
	}
	
	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(PetriItems.front_clock);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		Entity entity = source.getImmediateSource();
		if(entity instanceof EntityArrow || entity instanceof EntityThrowable) {
            this.playPlaceSound();
			return false;
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(this.facingDirection.getHorizontalIndex());
		buffer.writeInt(this.hangingPosition.getX());
		buffer.writeInt(this.hangingPosition.getY());
		buffer.writeInt(this.hangingPosition.getZ());
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		this.facingDirection = EnumFacing.getHorizontal(additionalData.readInt());
		this.hangingPosition = new BlockPos(additionalData.readInt(), additionalData.readInt(), additionalData.readInt());
		this.updateBoundingBox();
	}

}
