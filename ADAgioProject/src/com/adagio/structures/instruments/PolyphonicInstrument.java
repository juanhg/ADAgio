package com.adagio.structures.instruments;

import java.util.ArrayList;
import java.util.List;

import org.modelcc.IModel;

import com.adagio.language.instruments.Register;
import com.adagio.language.instruments.Timbre;
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
	public List<AbsoluteMusicNote> apply(List<AbsoluteMusicNote> aNotes) {
		List<AbsoluteMusicNote> transportedANotes = new ArrayList<AbsoluteMusicNote>();
		
		for(AbsoluteMusicNote current: aNotes){
			transportedANotes.add(registers[0].aNoteToRegister(current));
		}
		
		//Fix octave to achieve that the first note is always de lower
		if(transportedANotes.size() >= 2){
			for(int i = 1; i < transportedANotes.size(); i++){
				while(transportedANotes.get(0).semitonesTill(transportedANotes.get(i)) < 0){
					transportedANotes.get(0).decreaseOctave();
				}
			}
		}
		
		return transportedANotes;
	}

}
