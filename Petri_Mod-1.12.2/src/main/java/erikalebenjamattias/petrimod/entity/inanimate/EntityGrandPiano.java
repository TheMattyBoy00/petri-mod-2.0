package erikalebenjamattias.petrimod.entity.inanimate;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import erikalebenjamattias.petrimod.network.OpenPianoGUIMessages;
import erikalebenjamattias.petrimod.network.PetriPacketHandler;

public class EntityGrandPiano extends Entity {
	
	private static final DataParameter<Boolean> IS_STATIONED = EntityDataManager.<Boolean>createKey(EntityGrandPiano.class, DataSerializers.BOOLEAN);

	public EntityGrandPiano(World worldIn) {
		super(worldIn);
        this.preventEntitySpawning = true;
        this.setSize(2.6F, 1.5F);
	}
	
	public EntityGrandPiano(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
	}

	@Override
	protected void entityInit() {
		this.getDataManager().register(IS_STATIONED, Boolean.valueOf(true));
	}
	
	@Override
	public boolean canBeCollidedWith() {
        return true;
    }
	
	@Override
	public boolean canBePushed() {
		return !this.isStationed();
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		return this.isStationed() ? this.getEntityBoundingBox() : super.getCollisionBoundingBox();
	}
	
	@Override
	@Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn) {
        return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
    }
	
	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		if(player.isSneaking()) {
			this.setStationed(!this.isStationed());
			return true;
		}
		else {
			if(!this.world.isRemote) {
				//player.displayGui(new EntityGrandPiano.GUIGrandPianoKeyboard(this.getPosition()));
				//Minecraft.getMinecraft().displayGuiScreen(new GuiPianoKeyboard(this));
				PetriPacketHandler.INSTANCE.sendTo(new OpenPianoGUIMessages.OpenPianoGUIMessage(this.getEntityId()), (EntityPlayerMP)player);
			}
		}
		return super.processInitialInteract(player, hand);
	}
	
	@Override
	public boolean hitByEntity(Entity entity) {
        return entity instanceof EntityPlayer ? this.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)entity), 0.0F) : false;
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        }
        else {
            if (!this.isDead && !this.world.isRemote) {
            	this.setDead();
                //this.setBeenAttacked();
            }

            return true;
        }
    }
	
	public void setStationed(boolean stationed) {
		this.getDataManager().set(IS_STATIONED, Boolean.valueOf(stationed));
	}
	
	public boolean isStationed() {
		return ((Boolean)this.getDataManager().get(IS_STATIONED)).booleanValue();
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!this.isStationed()) {
			List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox(), EntitySelectors.<Entity>getTeamCollisionPredicate(this));
			if(!list.isEmpty()) {
				for (int l = 0; l < list.size(); ++l) {
		            Entity entity = (Entity)list.get(l);
		            this.applyEntityCollision(entity);
		        }
			}
		}
		if (!this.hasNoGravity()) {
            this.motionY -= 0.03999999910593033D;
		}
        this.motionX *= 0.5;
        this.motionZ *= 0.5;
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		
	}
	
	/*public static class GUIGrandPianoKeyboard implements IInteractionObject {
		
		private final BlockPos pos;
		
		public GUIGrandPianoKeyboard(BlockPos pos) {
			this.pos = pos;
		}

		@Override
		public String getName() {
			return "grand_piano_keyboard";
		}

		@Override
		public boolean hasCustomName() {
			return false;
		}

		@Override
		public ITextComponent getDisplayName() {
			return new TextComponentTranslation(Reference.MOD_ID + ":entity.GrandPiano.name", new Object[0]);
		}

		@Override
		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
			return new ContainerPianoKeyboard(pos);
		}

		@Override
		public String getGuiID() {
			return Reference.MOD_ID + ":grand_piano_keyboard";
		}
	}*/
}