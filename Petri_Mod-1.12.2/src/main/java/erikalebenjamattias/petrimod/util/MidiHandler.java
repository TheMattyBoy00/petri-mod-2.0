package erikalebenjamattias.petrimod.util;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

import erikalebenjamattias.petrimod.client.gui.GuiPianoKeyboard;

public class MidiHandler {
	
	//public final MidiDevice.Info[] midiInfos;
	public List<MidiDevice> devices = new ArrayList<MidiDevice>();

	public MidiHandler(GuiPianoKeyboard keyboard) {
		MidiDevice device;
		MidiDevice.Info[] midiInfos = MidiSystem.getMidiDeviceInfo();
		for (int i = 0; i < midiInfos.length; i++) {
			try {
				device = MidiSystem.getMidiDevice(midiInfos[i]);
				//System.out.println(infos[i]);

				List<Transmitter> transmitters = device.getTransmitters();
				
				for (int j = 0; j < transmitters.size(); j++) {
					transmitters.get(j).setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString(), keyboard));
				}

				Transmitter trans = device.getTransmitter();
				trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString(), keyboard));

				device.open();
				//System.out.println(device.getDeviceInfo() + " Was Opened");
				this.devices.add(device);

			} catch (MidiUnavailableException e) {}
		}

	}

	public class MidiInputReceiver implements Receiver {
		public String name;
		private GuiPianoKeyboard keyboard;

		public MidiInputReceiver(String name, GuiPianoKeyboard keyboard) {
			this.name = name;
			this.keyboard = keyboard;
		}

		public void send(MidiMessage msg, long timeStamp) {
			//System.out.println("midi received");
			this.keyboard.midiMessage.add(msg);
		}

		public void close() {
			
		}
	}
}