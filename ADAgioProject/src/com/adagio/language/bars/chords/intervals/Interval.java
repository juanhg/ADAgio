package com.adagio.language.bars.chords.intervals;

import java.util.HashMap;
import java.util.Map;

import org.modelcc.IModel;
import org.modelcc.types.IntegerModel;

import com.adagio.events.MusicEventListener;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.MusicNote;
import com.adagio.language.musicnotes.MusicNoteName;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.DoubleSharpAlteration;
import com.adagio.language.musicnotes.notealterations.FlatAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;

public class Interval implements IModel {
	
	IntervalQuality quality;
	IntegerModel number;
	
	/**
	 * Map<NumberOfInterval, Semitones>
	 */
	 private static final Map<Integer, Integer> mayorIntervals = new HashMap<Integer, Integer>();
	    static {
	        mayorIntervals.put(2, 2);
	        mayorIntervals.put(3, 4);
	        mayorIntervals.put(6, 9);
	        mayorIntervals.put(7, 11);
	        
	        mayorIntervals.put(9, 14);
	        mayorIntervals.put(10, 16);
	        mayorIntervals.put(13, 21);
	        mayorIntervals.put(14, 23);
	    }
	 
	 /**
	   * Map<NumberOfInterval, Semitones>
	   */    
	private static final Map<Integer,Integer> perfectIntervals = new HashMap<Integer,Integer>();
		static {
			perfectIntervals.put(1,0);
			perfectIntervals.put(4,5);
			perfectIntervals.put(5,7);
			
			perfectIntervals.put(8,12);
			perfectIntervals.put(11,17);
			perfectIntervals.put(12,19);
		}
	
	
	public Interval(String quality, int number){
		this.quality = new IntervalQuality(quality);
		this.number = new IntegerModel(number);
	}
	
	public Interval(){
		
	}
	
	
	/**
	 * Calculate the interval's semitones
	 * @return The number of semitones
	 */
	public int semitones(){
		int count = 0;
		boolean mayor = false;
		
		if(getMayorintervals().containsKey(number.intValue())){
			count = getMayorintervals().get(number.intValue());
			mayor = true;
		}
		else{
			count = getPerfectintervals().get(number.intValue());
		}
		
		if(quality.getValue().equals("m")){
			count--;
		}
		else if(quality.getValue().equals("A")){
			count++;
		}
		else if (quality.getValue().equals("AA")){
			count = count + 2;
		}
		else if (quality.getValue().equals("d")){
			if(mayor){
				count = count - 2;
			}
			else{
				count--;
			}
		}
		else if (quality.getValue().equals("dd")){
			if(mayor){
				count = count - 3;
			}
			else{
				count = count - 2;
			}
		}
		return count;
	}
	
	/**
	 * The increment in the octave value of MusicNote caused by the Interval
	 * @param noteName
	 * @param data
	 * @return
	 */
	public int octaveAlterations(MusicNoteName noteName){
		int increment = 0;
		int noteIntValue =  BasicNoteName.nameToInt(noteName.getBaseNoteName());
		
		for(int i = 0; i < this.number.intValue()-1; i++){
			noteIntValue = (noteIntValue + 1) % 7;
			if(noteIntValue == 2){
				increment++;
			}
		}
				
		return increment;
	}
		
	/**
	 * Applies the interval to the note
	 * @param note
	 * @param data
	 * @return And absolute note with the result of apply the interval to the note. Always is 
	 * higher than the fundamental one
	 */
	public AbsoluteMusicNote apply(MusicNote note, MusicEventListener listener){
		
		AbsoluteMusicNote result = null;

		//if is not a silence...
		if(note.getMusicNoteName().isSilence() == false){
			AbsoluteMusicNote aNote = note.toAbsoluteMusicNote(listener);

			int octave = aNote.getOctave().intValue();
			BasicNoteName bName = (BasicNoteName) aNote.getMusicNoteName().getBaseNoteName().clone();
			MusicNoteName noteName = aNote.getMusicNoteName().clone();


			int aux = 0;
			int semitones = this.semitones();
			int semitones2Notes = 0;

			// Generating the new octave value
			octave += this.octaveAlterations(aNote.getMusicNoteName());

			// Generating the new noteName
			aux = (BasicNoteName.nameToInt(bName) + number.intValue());
			bName.setValue(( BasicNoteName.intToName((aux-1)%7).getValue()));
			noteName.getBaseNoteName().setValue(bName.getValue());
			//bName.setValue((data.intToName((aux-1)%7).getValue()));


			// Fixing the alteration
			semitones2Notes = aNote.semitonesTill(new AbsoluteMusicNote(new IntegerModel(octave),bName));
			aux = semitones - semitones2Notes;

			while(aux > 2){
				aux -= bName.semitonesToNextNote();
				if(bName.getBaseNoteName().getValue().equals("B")){
					octave++;
				}
				bName = (BasicNoteName) bName.next().clone();
				System.err.println("Warning 2: Can't apply interval \"" + this.toString() 
						+"\" to the note \""+ note.toString()+"\". The note generated is estimated.");
			}

			while(aux < -2){
				aux += bName.semitonesToPreviousNote();
				if(bName.getBaseNoteName().getValue().equals("C")){
					octave--;
				}
				bName = (BasicNoteName) bName.previous().clone();
				System.err.println("Warning 2 : Can't apply interval \"" + this.toString() 
						+"\" to the note \""+ note.toString()+"\". The note generated is estimated.");
			}

			if(aux == 1){
				result = new AbsoluteMusicNote(octave,bName.getValue(),new SharpAlteration());
			}
			else if (aux == 2){
				//Adding a DoubleSharp
				result = new AbsoluteMusicNote(octave,bName.getValue(),new DoubleSharpAlteration());
			}
			else if (aux == -1){
				//Adding a Flat
				result = new AbsoluteMusicNote(octave,bName.getValue(),new FlatAlteration());
			}
			else if (aux == -2){
				//Adding a DoubleFlat
				result = new AbsoluteMusicNote(octave,bName.getValue(),new DoubleFlatAlteration());
			}
			else if(aux == 0){
				// No changes
				result = new AbsoluteMusicNote(new IntegerModel(octave), bName);
			}
		}
		else{
			result = AbsoluteMusicNote.genSilence();
		}

		return result;
	}
	
	public static Map<Integer, Integer> getMayorintervals() {
		return mayorIntervals;
	}

	public static Map<Integer, Integer> getPerfectintervals() {
		return perfectIntervals;
	}

	public String toString(){
		return this.quality.toString() + this.number.toString();
	}
}
