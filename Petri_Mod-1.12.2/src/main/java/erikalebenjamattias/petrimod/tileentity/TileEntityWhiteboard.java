package erikalebenjamattias.petrimod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityWhiteboard extends TileEntity {

	private byte[][] pixels = new byte[16][16];
	
	@Override
	public void setWorldCreate(World world) {
		this.pixels = new byte[16][16];
	}
	
	public void colorPixel(byte color, int x, int y) {
		this.pixels[x][y] = color;
		this.markDirty();
		this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos), 3);
	}
	
	public void erasePixel(int x, int y) {
		this.pixels[x][y] = 0;
		this.markDirty();
		this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos), 3);
	}
	
	public byte getColorAt(int x, int y) {
		return this.pixels[x][y];
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		for(int i = 0; i < 16; i++) {
			compound.setByteArray("PixelColumn" + (i + 1), this.pixels[i]);
		}
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		for(int i = 0; i < 16; i++) {
			this.pixels[i] = compound.getByteArray("PixelColumn" + (i + 1));
		}
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound compound = pkt.getNbtCompound();
		this.readUpdateTag(compound);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound compound = new NBTTagCompound();
		this.writeUpdateTag(compound);
		return new SPacketUpdateTileEntity(this.pos, this.getBlockMetadata(), compound);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound compound = super.getUpdateTag();
		this.writeUpdateTag(compound);
		return compound;
	}
	
	public void writeUpdateTag(NBTTagCompound compound) {
		for(int i = 0; i < 16; i++) {
			compound.setByteArray("PixelColumn" + (i + 1), this.pixels[i]);
		}
	}
	
	public void readUpdateTag(NBTTagCompound compound) {
		for(int i = 0; i < 16; i++) {
			this.pixels[i] = compound.getByteArray("PixelColumn" + (i + 1));
		}
	}
	
}
