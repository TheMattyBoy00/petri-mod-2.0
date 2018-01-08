package erikalebenjamattias.petrimod.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sound.midi.MidiMessage;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.input.Keyboard;

import erikalebenjamattias.petrimod.client.audio.PianoSound;
import erikalebenjamattias.petrimod.entity.inanimate.EntityGrandPiano;
import erikalebenjamattias.petrimod.init.PetriSounds;
import erikalebenjamattias.petrimod.main.Reference;
import erikalebenjamattias.petrimod.network.NotifyServerOfPianoPlayMessages;
import erikalebenjamattias.petrimod.network.NotifyServerOfPianoResetMessages;
import erikalebenjamattias.petrimod.network.NotifyServerOfPianoStopMessages;
import erikalebenjamattias.petrimod.network.PetriPacketHandler;
import erikalebenjamattias.petrimod.util.MidiHandler;

public class GuiPianoKeyboard extends GuiScreen {
	
	private EntityGrandPiano piano;
	/**An array of all 88 keys, saying whether they are down or not, plus one (index 0) to indicate not hitting any keys at all.*/
	private boolean[] keysDown = new boolean[89] /*{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}*/;
	private static final ResourceLocation KEYBOARD_GUI_TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/piano_keyboard.png");
	public static final PianoSound[] pianoSound = new PianoSound[] {
		null,
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A0, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A0, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C1, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C1, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C1, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp1, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp1, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp1, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp1, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp1, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp1, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A1, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A1, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A1, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C2, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C2, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C2, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp2, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp2, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp2, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp2, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp2, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp2, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A2, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A2, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A2, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C3, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C3, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C3, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp3, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp3, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp3, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp3, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp3, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp3, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A3, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A3, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A3, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C4, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C4, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C4, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp4, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp4, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp4, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp4, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp4, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp4, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A4, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A4, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A4, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C5, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C5, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C5, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp5, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp5, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp5, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp5, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp5, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp5, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A5, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A5, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A5, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C6, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C6, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C6, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp6, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp6, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp6, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp6, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp6, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp6, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A6, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A6, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A6, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C7, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C7, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C7, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp7, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp7, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp7, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp7, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp7, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp7, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A7, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A7, 1.0F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A7, 1.0594630943592952645618252949463F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C8, 0.9438743126885998235254131922893F),
		new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C8, 1.0F)
	};
	//public static final List<PianoSound> pianoSound = new ArrayList<PianoSound>();
	private List<Integer> keysThatAreDown = new ArrayList<Integer>();
	private boolean pedal;
	public static List<PianoSound> lingeringSounds = new ArrayList<PianoSound>();
	private MidiHandler midiHandler = new MidiHandler(this);
	private int midiIndex;
	public List<MidiMessage> midiMessage = new CopyOnWriteArrayList<MidiMessage>();
	
	byte rightClickNote = 0;
	byte leftClickNote = 0;
	
    public GuiPianoKeyboard(EntityGrandPiano piano) {
    	this.piano = piano;
    	/*this.pianoSound.add(null);
    	this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A0, 1.0F));
    	this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A0, 1.0594630943592952645618252949463F));
    	this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C1, 0.9438743126885998235254131922893F));
    	this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C1, 1.0F));
    	this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C1, 1.0594630943592952645618252949463F));
    	this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp1, 0.9438743126885998235254131922893F));
    	this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp1, 1.0F));
    	this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp1, 1.0594630943592952645618252949463F));
    	this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp1, 0.9438743126885998235254131922893F));
    	this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp1, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp1, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A1, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A1, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A1, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C2, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C2, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C2, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp2, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp2, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp2, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp2, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp2, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp2, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A2, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A2, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A2, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C3, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C3, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C3, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp3, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp3, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp3, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp3, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp3, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp3, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A3, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A3, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A3, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C4, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C4, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C4, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp4, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp4, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp4, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp4, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp4, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp4, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A4, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A4, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A4, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C5, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C5, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C5, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp5, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp5, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp5, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp5, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp5, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp5, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A5, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A5, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A5, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C6, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C6, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C6, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp6, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp6, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp6, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp6, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp6, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp6, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A6, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A6, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A6, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C7, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C7, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C7, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp7, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp7, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Dsharp7, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp7, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp7, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_Fsharp7, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A7, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A7, 1.0F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_A7, 1.0594630943592952645618252949463F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C8, 0.9438743126885998235254131922893F));
		this.pianoSound.add(new PianoSound(PetriSounds.ENTIY_GRAND_PIANO_C8, 1.0F));*/
	}
    
    /*@Override
    public void initGui() {
    	this.buttonList.clear();
    	this.buttonList.add(new GuiButton(100, (this.width - 300) / 2, 175, 300, 20, I18n.format("gui.piano.midi", new Object[] {this.midiHandler.devices.get(this.midiIndex).getDeviceInfo()})));
    }*/
    
    public byte getKeyNumFromXY(int mouseX, int mouseY) {
    	int i = (this.width - 328) / 2 - 1;
    	if(mouseY >= 138 && mouseY <= 168 && mouseX >= i + 8 && mouseX <= i + 320) {
    		if(mouseX > i + 8 && mouseX <= i + 14 && (mouseY >= 154 || mouseX  <= i + 12)) {
    			return 1;
    		}
    		if(mouseY < 154 && mouseX > i + 12 && mouseX <= i + 16) {
    			return 2;
    		}
    		for(byte j = 0; j < 7; j++) {
    			int k = i + j * 42;
    			if(mouseX > k + 14 && mouseX <= k + 20 && (mouseY >= 154 || mouseX > k + 16)) {
    				return (byte) (3 + j * 12);
    			}
    			if(mouseX > k + 20 && mouseX <= k + 26 && (mouseY >= 154 || mouseX <= k + 24)) {
    				return (byte) (4 + j * 12);
    			}
    			if(mouseY < 154 && mouseX > k + 24 && mouseX <= k + 28) {
    				return (byte) (5 + j * 12);
    			}
    			if(mouseX > k + 26 && mouseX <= k + 32 && (mouseY >= 154 || mouseX > k + 28 && mouseX <= k + 30)) {
    				return (byte) (6 + j * 12);
    			}
    			if(mouseY < 154 && mouseX > k + 30 && mouseX <= k + 34) {
    				return (byte) (7 + j * 12);
    			}
    			if(mouseX > k + 32 && mouseX <= k + 38 && (mouseY >= 154 || mouseX > k + 34)) {
    				return (byte) (8 + j * 12);
    			}
    			if(mouseX > k + 38 && mouseX <= k + 44 && (mouseY >= 154 || mouseX <= k + 42)) {
    				return (byte) (9 + j * 12);
    			}
    			if(mouseY < 154 && mouseX > k + 42 && mouseX <= k + 46) {
    				return (byte) (10 + j * 12);
    			}
    			if(mouseX > k + 44 && mouseX <= k + 50 && (mouseY >= 154 || mouseX > k + 46 && mouseX <= k + 48)) {
    				return (byte) (11 + j * 12);
    			}
    			if(mouseY < 154 && mouseX > k + 48 && mouseX <= k + 52) {
    				return (byte) (12 + j * 12);
    			}
    			if(mouseX > k + 50 && mouseX <= k + 56 && (mouseY >= 154 || mouseX > k + 52 && mouseX <= k + 54)) {
    				return (byte) (13 + j * 12);
    			}
    			if(mouseY < 154 && mouseX > k + 54 && mouseX <= k + 58) {
    				return (byte) (14 + j * 12);
    			}
    		}
			if(mouseX > i + 308 && mouseX <= i + 314 && (mouseY >= 154 || mouseX > i + 310)) {
				return 87;
			}
			if(mouseX > 314) {
				return 88;
			}
    	}
    	return 0;
    }
    
    public byte getWhiteKeyFromIndex(byte index) {
    	if(index != 0) {
    		for(int i = 0; i < 7; i++) {
    			if(index == 1 + i * 12) {
    				return (byte) (1 + i * 7);
    			}
    			if(index == 3 + i * 12) {
    				return (byte) (2 + i * 7);
    			}
    			if(index == 4 + i * 12) {
    				return (byte) (3 + i * 7);
    			}
    			if(index == 6 + i * 12) {
    				return (byte) (4 + i * 7);
    			}
    			if(index == 8 + i * 12) {
    				return (byte) (5 + i * 7);
    			}
    			if(index == 9 + i * 12) {
    				return (byte) (6 + i * 7);
    			}
    			if(index == 11 + i * 12) {
    				return (byte) (7 + i * 7);
    			}
    		}
    		if(index == 85) {
    			return 50;
    		}
    		if(index == 87) {
    			return 51;
    		}
    		if(index == 88) {
    			return 52;
    		}
    	}
    	return 0;
    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
    	super.mouseClicked(mouseX, mouseY, mouseButton);
    	if(mouseButton == 1) {
    		this.keysDown[this.getKeyNumFromXY(mouseX, mouseY)] = true;
    		this.rightClickNote = this.getKeyNumFromXY(mouseX, mouseY);
    	}
    	if(mouseButton == 0) {
    		this.keysDown[this.getKeyNumFromXY(mouseX, mouseY)] = true;
    		this.leftClickNote = this.getKeyNumFromXY(mouseX, mouseY);
    	}
    }
    
    @Override
    protected void mouseReleased(int mouseX, int mouseY, int mouseButton) {
    	super.mouseReleased(mouseX, mouseY, mouseButton);
    	if(mouseButton == 1) {
	    	this.keysDown[this.rightClickNote] = false;
	    	this.rightClickNote = 0;
    	}
    	if(mouseButton == 0) {
    		this.keysDown[this.leftClickNote] = false;
    		this.leftClickNote = 0;
    	}
    }
    
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int mouseButton, long timeSinceLastClick) {
    	if(mouseButton == 0) {
    		this.keysDown[this.getKeyNumFromXY(mouseX, mouseY)] = true;
    		this.leftClickNote = this.getKeyNumFromXY(mouseX, mouseY);
    		for(byte i = 0; i < this.keysDown.length; i++) {
	    		if(i != this.leftClickNote && i != this.rightClickNote) {
	    			this.keysDown[i] = false;
	    		}
    		}
    	}
    }
    
    /*@Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	if(button.enabled) {
    		if(button.id == 100) {
    			if(this.midiIndex++ >= this.midiHandler.devices.size() - 1) {
    				this.midiIndex = 0;
    			}
    			this.initGui();
    		}
    	}
    }*/
    
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	/*@Override
	public void handleKeyboardInput() throws IOException {
		this.pedal = Keyboard.isKeyDown(this.mc.gameSettings.keyBindJump.getKeyCode());
		if(!Keyboard.isKeyDown(this.mc.gameSettings.keyBindJump.getKeyCode())) {
			for(byte i = 1; i < this.pianoSound.length; i++) {
				System.out.println("Hai");
				this.pianoSound[i].stopPlaying();
			}
		}
		super.handleKeyboardInput();
	}*/
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		if(keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
			this.mc.displayGuiScreen((GuiScreen)null);
		}
	}
	
	@Override
	public void updateScreen() {
		for(byte i = 1; i < this.keysDown.length; i++) {
			if(this.keysDown[i]) {
				//System.out.println(this.pianoSound[i].setEntity(this.piano));
				//try {
					//if(!this.pianoSound[i].isLoaded() && !this.mc.getSoundHandler().isSoundPlaying(this.pianoSound[i])) {
					if(!this.keysThatAreDown.contains(Integer.valueOf(i))) {
						this.mc.getSoundHandler().playSound(this.pianoSound[i].setEntity(this.piano)/*.setLoaded(true)*/);
						PetriPacketHandler.INSTANCE.sendToServer(new NotifyServerOfPianoPlayMessages.NotifyServerOfPianoPlayMessage(i, 2.0F, this.piano.getEntityId()/*, Minecraft.getMinecraft().player.getEntityId()*/));
						this.keysThatAreDown.add(Integer.valueOf(i));
					}
					//}
					/*else {
						this.pianoSound[i] = new PianoSound(this.pianoSound[i].getSoundEvent(), this.pianoSound[i].getPitch());
					}*/
				//} catch(Exception e) {}
			}
			else {
				for(int j = 0; j < this.keysThatAreDown.size(); j++) {
					if(this.keysThatAreDown.get(j) == Integer.valueOf(i)) {
						this.keysThatAreDown.remove(j);
						/*PianoSound sound = this.pianoSound[i];
						if(!Keyboard.isKeyDown(this.mc.gameSettings.keyBindJump.getKeyCode())) {
							sound.stopPlaying();
						}*/
						if(i <= 72) {
							this.lingeringSounds.add(this.pianoSound[i]);
						}
						this.pianoSound[i] = new PianoSound(this.pianoSound[i]/*.getSoundEvent(), this.pianoSound[i].getPitch()*/);
						PetriPacketHandler.INSTANCE.sendToServer(new NotifyServerOfPianoResetMessages.NotifyServerOfPianoResetMessage(i, this.piano.getEntityId()));
					}
				}
			}
		}
		this.pedal = Keyboard.isKeyDown(this.mc.gameSettings.keyBindJump.getKeyCode());
		if(!pedal) {
			for(byte i = 0; i < this.lingeringSounds.size(); i++) {
				this.lingeringSounds.get(i).stopPlaying();
				this.lingeringSounds.remove(i);
				PetriPacketHandler.INSTANCE.sendToServer(new NotifyServerOfPianoStopMessages.NotifyServerOfPianoStopMessage(i, this.piano.getEntityId()));
			}
		}
		/*for(MidiMessage midiMessage : this.midiMessage) {
			if(midiMessage != null) {
				for(int i = 0; i < midiMessage.getLength(); i++) {
					System.out.println(midiMessage.getMessage()[i]);
				}
				if(midiMessage.getMessage()[0] != -128) {
					this.keysDown[MathHelper.clamp(midiMessage.getMessage()[1] - 8, 1, 88)] = true;
				}
			}
		}*/
		//String s = "Messages: ";
		//try {
		for(MidiMessage message : this.midiMessage) {
			//s += message.getStatus() + " ";
			/*for(int i = 0; i < message.getMessage().length; i++) {
				s += message.getMessage()[i] + " ";
			}*/
			if(message.getStatus() == 144) {
				this.keysDown[MathHelper.clamp(message.getMessage()[1] - 20, 1, 88)] = true;
				if(!this.keysThatAreDown.contains(Integer.valueOf(MathHelper.clamp(message.getMessage()[1] - 20, 1, 88)))) {
					this.mc.getSoundHandler().playSound(this.pianoSound[MathHelper.clamp(message.getMessage()[1] - 20, 1, 88)].setEntity(this.piano).setVolume(message.getMessage()[2] / 63.5F));
					PetriPacketHandler.INSTANCE.sendToServer(new NotifyServerOfPianoPlayMessages.NotifyServerOfPianoPlayMessage((byte)MathHelper.clamp(message.getMessage()[1] - 20, 1, 88), 2.0F, this.piano.getEntityId()));
					this.keysThatAreDown.add(Integer.valueOf(MathHelper.clamp(message.getMessage()[1] - 20, 1, 88)));
				}
			}
			else if(message.getStatus() == 128) {
				this.keysDown[MathHelper.clamp(message.getMessage()[1] - 20, 1, 88)] = false;
			}
		}
		//} catch(ConcurrentModificationException e) {}
		//if(s.length() > 10 ) { System.out.println(s); }
		this.midiMessage.clear();
		/*try {
			if(this.midiHandler.devices.get(this.midiIndex).getTransmitter().getReceiver() instanceof MidiHandler.MidiInputReceiver) {
				System.out.println("Hellu");
				System.out.println(((MidiHandler.MidiInputReceiver)(this.midiHandler.devices.get(this.midiIndex).getTransmitter().getReceiver())).getMidiMessage());
			}
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		/*try {
			MidiSystem.getSequencer().open();
			MidiSystem.getSequencer().startRecording();
			System.out.println(MidiSystem.getSequencer().getSequence());
			MidiSystem.getSequencer().stopRecording();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}*/
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if(this.mc != null) {
			this.drawDefaultBackground();
		}
		//System.out.println(this.getKeyNumFromXY(mouseX, mouseY) + ", " + this.keysDown[1] + ", " + mouseX + ", " + mouseY);
    	/*for(byte i = 1; i < this.keysDown.length; i++) {
    		if(this.keysDown[i]) {
    			System.out.println(this.getWhiteKeyFromIndex(i));
    		}
    	}*/
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		if(this.mc != null) {
			this.mc.getTextureManager().bindTexture(KEYBOARD_GUI_TEXTURES);
		}
		GlStateManager.enableBlend();
		GlStateManager.pushMatrix();
		GlStateManager.scale(2, 2, 2);
        int i = (this.width - 328) / 4;
        this.drawTexturedModalRect(i, 2, 0, 0, 164, 82);
        for(int k = 0; k < 52; k++) {
        	this.drawTexturedModalRect(i + 4 + k * 3, 69, 250, 0, 3, 15);
        	for(byte j = 1; j < this.keysDown.length; j++) {
        		if(this.keysDown[j]) {
        			if(k + 1 == this.getWhiteKeyFromIndex(j)) {
        				this.drawTexturedModalRect(i + 4 + k * 3, 69, 253, 0, 3, 15);
        			}
        		}
        	}
        }
        for(int k = 0; k < 7; k++) {
        	this.drawTexturedModalRect(i + 6 + k * 21, 69, 251, 15, 2, 8);
        	this.drawTexturedModalRect(i + 12 + k * 21, 69, 251, 15, 2, 8);
        	this.drawTexturedModalRect(i + 15 + k * 21, 69, 251, 15, 2, 8);
        	this.drawTexturedModalRect(i + 21 + k * 21, 69, 251, 15, 2, 8);
        	this.drawTexturedModalRect(i + 24 + k * 21, 69, 251, 15, 2, 8);
        	for(byte j = 1; j < this.keysDown.length; j++) {
        		if(this.keysDown[j]) {
        			if(j == 2 + k * 12) {
        	        	this.drawTexturedModalRect(i + 6 + k * 21, 69, 254, 15, 2, 8);
        			}
        			if(j == 5 + k * 12) {
        	        	this.drawTexturedModalRect(i + 12 + k * 21, 69, 254, 15, 2, 8);
        			}
        			if(j == 7 + k * 12) {
        	        	this.drawTexturedModalRect(i + 15 + k * 21, 69, 254, 15, 2, 8);
        			}
        			if(j == 10 + k * 12) {
        	        	this.drawTexturedModalRect(i + 21 + k * 21, 69, 254, 15, 2, 8);
        			}
        			if(j == 12 + k * 12) {
        	        	this.drawTexturedModalRect(i + 24 + k * 21, 69, 254, 15, 2, 8);
        			}
        		}
        	}
        }
    	this.drawTexturedModalRect(i + 153, 69, 251, 15, 2, 8);
    	if(this.keysDown[86]) {
        	this.drawTexturedModalRect(i + 153, 69, 254, 15, 2, 8);
    	}
        GlStateManager.popMatrix();
        GlStateManager.disableBlend();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}