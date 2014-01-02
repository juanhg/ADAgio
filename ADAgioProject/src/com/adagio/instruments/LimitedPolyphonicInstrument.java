package com.adagio.instruments;

import java.util.ArrayList;
import java.util.List;

import org.modelcc.IModel;

import com.adagio.language.instruments.features.Register;
import com.adagio.language.instruments.features.Timbre;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class LimitedPolyphonicInstrument extends Instrument implements IModel {

	public LimitedPolyphonicInstrument(){
		super();
	}
	
	public LimitedPolyphonicInstrument(Timbre timbre, Register registers[]){
		super(timbre,registers);
	}
	
	@Override
	/**
	 * Assigns the lower note to the lower register. Later, assing the other notes 
	 * to the other not-used-registers.
	 */
	public List<AbsoluteMusicNote> aNotesToInstrumentRegister(List<AbsoluteMusicNote> aNotes) {
		
		List<AbsoluteMusicNote> aNotesCopy = new ArrayList<AbsoluteMusicNote>();
		List<AbsoluteMusicNote> transportedANotes = new ArrayList<AbsoluteMusicNote>();
		int lowerNotePos = 0;
		AbsoluteMusicNote aNote = new AbsoluteMusicNote();
		
		//Make a copy of the vector
		for(AbsoluteMusicNote current: aNotes){
			aNotesCopy.add(current.clone());
		}
		
		//Assign the lower note, to the lower register
		lowerNotePos = AbsoluteMusicNote.lowerNotePosition(aNotesCopy);
		transportedANotes.add(this.lowerRegister().aNoteToRegister(aNotesCopy.get(lowerNotePos).clone()));
		aNotesCopy.remove(lowerNotePos);
		this.lowerRegister().setUsed(true);

		//Assign the other notes notes to the registers
		for(int i = 0; i < aNotesCopy.size() && this.notUsedRegisters() > 0; i++){
			aNote = aNotesCopy.get(i);
			transportedANotes.add(this.selectRegister(aNote).aNoteToRegister(aNote));
			this.selectRegister(aNote).setUsed(true);
		}
		
		this.resetRegisters();
		return aNotesCopy;
	}
	
	/**
	 * Gets the better not-used register for an AbsoluteNote.
	 * That will be one that the note belong to (if is posible),
	 * or the last not-used.
	 * @param aNote Reference AbsoluteMusicNote
	 * @return The better register for the note. "null" if doesn't exist.
	 */
	private Register selectRegister(AbsoluteMusicNote aNote){
		int pos = 0;
		
		for(int i = 0; i < registers.length; i++){
			if(!registers[i].isUsed()){
				pos = i;
				if( registers[i].belong(aNote)){
					return registers[i];
				}
				
			}
		}	
		return registers[pos];
	}
	
	/**
	 * Gets the lower register of the instrument
	 * @return The lower register of the instrument
	 */
	private Register lowerRegister(){
		int referenceSemitones = 0;
		int semitones = 0;
		int lowerPos = -1;
		
		for(int i = 0; i < this.registers.length; i++){
			semitones = registers[0].getLowerNote().semitonesTill(registers[i].getLowerNote());
			if(referenceSemitones > semitones){
				referenceSemitones = semitones;
				lowerPos = i;
			}
		}
		
		return registers[lowerPos];
	}
}
