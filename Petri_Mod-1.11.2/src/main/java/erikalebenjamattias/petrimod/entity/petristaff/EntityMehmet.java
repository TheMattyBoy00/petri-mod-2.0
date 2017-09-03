package erikalebenjamattias.petrimod.entity.petristaff;

import java.util.List;

import net.minecraft.block.BlockDoor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import erikalebenjamattias.petrimod.entity.ai.EntityAITeleportBetweenRooms;

public class EntityMehmet extends EntityCreature {

	public EntityMehmet(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 2);
		this.tasks.addTask(0, new EntityAIWander(this, 0.3D));
		this.tasks.addTask(1, new EntityAITeleportBetweenRooms(this, 3));
        this.tasks.addTask(2, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityLiving.class, 16.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
	}
	
	protected void applyEntityAttributesForAgeablePrehistoricEntities() {
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0F);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	}

	@Override
	public float getEyeHeight() {
		return 1.8F;
	}
	
	public boolean isEnclosedRoom(BlockPos pos) {
		List<BlockPos> list = Lists.<BlockPos>newArrayList();
		for(int phi = -180; phi < 180; phi += 2) {
			for(int theta = -90; theta < 90; theta += 2) {
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
		        Vec3d vec3d = new Vec3d(x, y, z);
		        float f = MathHelper.cos(-phi * 0.017453292F - (float)Math.PI);
		        float f1 = MathHelper.sin(-phi * 0.017453292F - (float)Math.PI);
		        float f2 = -MathHelper.cos(-theta * 0.017453292F);
		        float f3 = MathHelper.sin(-theta * 0.017453292F);
		        float f4 = f1 * f2;
		        float f5 = f * f2;
		        double radius = 16.0D;
		        Vec3d vec3d1 = vec3d.addVector((double)f4 * radius, (double)f3 * radius, (double)f5 * radius);
		        RayTraceResult result = this.world.rayTraceBlocks(vec3d, vec3d1, false, true, true);
		        if(result != null) {
			        if(result.typeOfHit == RayTraceResult.Type.BLOCK/* && !(this.world.getBlockState(pos).getBlock() instanceof BlockDoor	&& this.world.getBlockState(pos).getValue(BlockDoor.OPEN))*/) {
			        	list.add(result.getBlockPos());
			        }
		        }
			}
		}
		return list.size() == 16200 && this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos).expandXyz(16), new Predicate() {public boolean func_180094_a(Entity entity) {return entity instanceof EntityPlayer && !((EntityPlayer)entity).isSpectator();} public boolean apply(Object p_apply_1_) {return this.func_180094_a((Entity)p_apply_1_);}}).size() == 0;
	}
	
	/*@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		System.out.println(this.isEnclosedRoom(getPosition().offset(EnumFacing.UP)));
		return super.processInteract(player, hand);
	}*/
	
	@Override
	protected boolean canDespawn() {
		return false;
	}
}