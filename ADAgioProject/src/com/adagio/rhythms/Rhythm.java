package com.adagio.rhythms;

import java.util.ArrayList;
import java.util.List;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.rhythm.RhythmComponent;
import com.adagio.language.times.Time;

public class Rhythm {

	List<RhythmComponent> components;
	
	public Rhythm(){
		components = new ArrayList<RhythmComponent>();
	}
	
	public Rhythm(List<RhythmComponent> components){
		this.components = components;
	}
	
	/**
	 * Takes a list of notes and applies to them the components of the rhythm
	 * @param aNotes
	 * @return
	 */
	List<AbsoluteMusicNote> apply(List<AbsoluteMusicNote> aNotes){
		//TODO implement
		return null;
	}
	
	/**
	 * For a given index of the voice and a list of chords displayed as absolute notes, 
	 * select the absolute note that correspond to the voice.
	 * @param chordsAsAbsoluteNotes Chord represented as lists of lists of absolute notes 
	 * @param voiceIndex Position of the voice
	 * @return The note selected. Null if is not possible to select a note. 
	 * For example: there is no component in the rhythm that matches with the voice
	 */
	public AbsoluteMusicNote selectNoteforVoice(List<List<AbsoluteMusicNote>> chordsAsAbsoluteNotes, 
			int voiceIndex){
		AbsoluteMusicNote aNoteSelected = null;
		int indexChordSelected = -1;
		
		double factor = 1.0/(double)chordsAsAbsoluteNotes.size();
		double initTime = components.get(voiceIndex).getInitTime();
		double finalTime = components.get(voiceIndex).getFinalTime();
		
		
		for(int i = 0; i < chordsAsAbsoluteNotes.size(); i++){
			
				if((initTime >= (factor*i)) && (initTime < (factor*(i+1)))){
					indexChordSelected = i;
					break;
				}
			
		}
		
		int chordSize =  chordsAsAbsoluteNotes.get(indexChordSelected).size();
		if(indexChordSelected != -1){
			aNoteSelected = chordsAsAbsoluteNotes.get(indexChordSelected).get(voiceIndex%chordSize).clone();
		}
		
		return aNoteSelected;
	}
}
