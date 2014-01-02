package com.adagio.instruments;

import java.util.ArrayList;
import java.util.List;

import org.modelcc.IModel;

import com.adagio.language.instruments.features.Register;
import com.adagio.language.instruments.features.Timbre;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class MonophonicInstrument extends Instrument implements IModel {

	public MonophonicInstrument(){
		super();
	}
	
	public MonophonicInstrument(Timbre timbre, Register registers[]){
		super(timbre,registers);
	}
	
	@Override
	/**
	 * MonophonicInstrument can play ONLY ONE NOTE per time.
	 * The note chosen (and transported to the register) is the lower
	 */
	public List<AbsoluteMusicNote> aNotesToInstrumentRegister(List<AbsoluteMusicNote> aNotes) {
		List<AbsoluteMusicNote> aNotesTransported = new ArrayList<AbsoluteMusicNote>();
		int lowerNoteIndex = AbsoluteMusicNote.lowerNotePosition(aNotes);
		AbsoluteMusicNote transportedNote;
		
		
		//Transport to the register the lower note
		transportedNote = this.registers[0].aNoteToRegister(aNotes.get(lowerNoteIndex));
		aNotesTransported.add(transportedNote);
		return aNotesTransported;
	}
}
