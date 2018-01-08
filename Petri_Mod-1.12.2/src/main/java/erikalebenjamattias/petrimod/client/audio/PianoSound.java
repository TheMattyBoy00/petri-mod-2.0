package erikalebenjamattias.petrimod.client.audio;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class PianoSound extends MovingSound	 {
	
	private Entity entity;
	//private boolean loaded;
	private boolean stop = false;
	private SoundEvent event;

	public PianoSound(SoundEvent soundIn, float pitch) {
		super(soundIn, SoundCategory.BLOCKS);
		this.pitch = pitch;
		this.event = soundIn;
		this.volume = 2.0F;
	}
	
	public PianoSound(PianoSound sound) {
		this(sound.getSoundEvent(), sound.getPitch());
	}
	
	public void stopPlaying() {
		//Minecraft.getMinecraft().getSoundHandler().stopSound(this);
		//this.donePlaying = true;
		//System.out.println("Hi");
		this.stop = true;
	}
	
	public PianoSound setEntity(Entity entity) {
		this.entity = entity;
		return this;
	}
	
	/*public PianoSound setLoaded(boolean loaded) {
		this.loaded = loaded;
		return this;
	}
	
	public boolean isLoaded() {
		return this.loaded;
	}*/
	
	public PianoSound setVolume(float volume) {
		this.volume = volume;
		return this;
	}
	
	public SoundEvent getSoundEvent() {
		return this.event;
	}

	@Override
	public void update() {
		if(stop) {
			if((this.volume -= 0.2) <= 0.2) {
				Minecraft.getMinecraft().getSoundHandler().stopSound(this);
			}
		}
		if(this.entity != null) {
			this.xPosF = (float) this.entity.posX;
			this.yPosF = (float) this.entity.posY;
			this.zPosF = (float) this.entity.posZ;
		}
		else {
			this.donePlaying = true;
		}
	}
}