package com.adagio.instruments;

import java.util.ArrayList;
import java.util.List;

import org.modelcc.IModel;

import com.adagio.language.instruments.features.Register;
import com.adagio.language.instruments.features.Timbre;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class PolyphonicInstrument extends Instrument implements IModel {
	
	public PolyphonicInstrument(){
		super();
	}
	
	public PolyphonicInstrument(Timbre timbre, Register registers[]){
		super(timbre,registers);
	}
	
	public PolyphonicInstrument(Timbre timbre){
		super(timbre);
	}
	
	
	/**
	 * There is no limit in the number of notes to play.
	 * Transport all the notes to the register
	 */
	
	@Override
	public List<AbsoluteMusicNote> aNotesToInstrumentRegister(List<AbsoluteMusicNote> aNotes) {
		List<AbsoluteMusicNote> transportedNotes = new ArrayList<AbsoluteMusicNote>();
		
		for(AbsoluteMusicNote current: aNotes){
			transportedNotes.add(registers[0].aNoteToRegister(current));
		}
		
		//Fix octave to achieve that the first note is always de lower
		if(transportedNotes.size() >= 2){
			for(int i = 1; i < transportedNotes.size(); i++){
				while(transportedNotes.get(i-1).semitonesTill(transportedNotes.get(i)) < 0){
					transportedNotes.get(i-1).decreaseOctave();
				}
			}
		}
		
		return transportedNotes;
	}

}
