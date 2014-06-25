package com.adagio.structures.instruments;

import java.util.ArrayList;
import java.util.List;

import org.modelcc.IModel;

import com.adagio.language.instruments.Register;
import com.adagio.language.instruments.Timbre;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class MonophonicInstrument extends Instrument implements IModel {

	public MonophonicInstrument(){
		super();
	}
	
	public MonophonicInstrument(Timbre timbre, Register registers[]){
		super(timbre,registers);
	}
	
	public MonophonicInstrument(Timbre timbre){
		super(timbre);
	}
	

	/**
	 * MonophonicInstrument can play ONLY ONE NOTE per time.
	 * The note chosen (and transported to the register) is the lower
	 */
	@Override
	public List<AbsoluteMusicNote> apply(List<AbsoluteMusicNote> aNotes) {
		List<AbsoluteMusicNote> aNotesTransported = new ArrayList<AbsoluteMusicNote>();
		int lowerNoteIndex = AbsoluteMusicNote.lowerNotePosition(aNotes);
		AbsoluteMusicNote transportedNote;
		
		
		//Transport to the register the lower note
		transportedNote = this.registers[0].aNoteToRegister(aNotes.get(lowerNoteIndex));
		aNotesTransported.add(transportedNote);
		return aNotesTransported;
	}
}
