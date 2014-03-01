package com.adagio.instruments;

import java.util.ArrayList;
import java.util.List;

import org.modelcc.IModel;

import com.adagio.language.instruments.Register;
import com.adagio.language.instruments.Timbre;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class LimitedPolyphonicInstrument extends Instrument implements IModel {

	public LimitedPolyphonicInstrument(){
		super();
	}
	
	public LimitedPolyphonicInstrument(Timbre timbre, Register registers[]){
		super(timbre,registers);
	}
	
	public LimitedPolyphonicInstrument(Timbre timbre){
		super(timbre);
	}
	
	@Override
	/**
	 * Assigns the lower note to the lower register. Later, assing the other notes 
	 * to the other not-used-registers.
	 */
	public List<AbsoluteMusicNote> apply(List<AbsoluteMusicNote> aNotes) {
		
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
		transportedANotes.add(this.selectRegister(aNotesCopy.get(lowerNotePos)).aNoteToRegister(aNotesCopy.get(lowerNotePos).clone()));
		this.selectRegister(aNotesCopy.get(lowerNotePos)).setUsed(true);
		aNotesCopy.remove(lowerNotePos);
	

		//Assign the other notes notes to the registers
		for(int i = 0; i < aNotesCopy.size() && this.notUsedRegisters() > 0; i++){
			aNote = aNotesCopy.get(i);
			transportedANotes.add(this.selectRegister(aNote).aNoteToRegister(aNote));
			this.selectRegister(aNote).setUsed(true);
		}
		
		//Fix octave to achieve that the first note is always de lower
				if(transportedANotes.size() >= 2){
					for(int i = 1; i < transportedANotes.size(); i++){
						while(transportedANotes.get(0).semitonesTill(transportedANotes.get(i)) < 0){
							transportedANotes.get(0).decreaseOctave();
						}
					}
				}
		
		this.resetRegisters();
		return transportedANotes;
	}
	
	/**
	 * Gets the better not-used register for an AbsoluteNote.
	 * That will be one that the note belong to (if is posible),
	 * or the lower not used
	 * @param aNote Reference AbsoluteMusicNote
	 * @return The better register for the note. "null" if doesn't exist.
	 */
	private Register selectRegister(AbsoluteMusicNote aNote){
		
		for(int i = 0; i < registers.length; i++){
			if(!registers[i].isUsed()){
				if( registers[i].belong(aNote)){
					return registers[i];
				}
				
			}
		}	
		return this.lowerNotUsedRegister();
	}
		
	/**
	 * Gets the lower not used register of the instrument
	 * @return The lower not used register of the instrument
	 */
	private Register lowerNotUsedRegister(){
		int referenceSemitones = Integer.MAX_VALUE;
		int semitones = 0;
		int lowerPos = 0;

		for(int i = 0; i < this.registers.length; i++){
			if(!registers[i].isUsed()){
				semitones = registers[0].getLowerNote().semitonesTill(registers[i].getLowerNote());
				if(referenceSemitones > semitones){
					referenceSemitones = semitones;
					lowerPos = i;
				}
			}
		}

		return registers[lowerPos];
	}
}
