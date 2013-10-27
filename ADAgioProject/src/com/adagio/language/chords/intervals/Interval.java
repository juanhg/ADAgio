package com.adagio.language.chords.intervals;

import org.modelcc.IModel;
import org.modelcc.types.IntegerModel;

import com.adagio.language.RunData;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.AlteredNoteName;
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
		
		if(RunData.getMayorintervals().containsKey(number.intValue())){
			count = RunData.getMayorintervals().get(number.intValue());
			mayor = true;
		}
		else{
			count = RunData.getPerfectintervals().get(number.intValue());
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
	public int octaveAlterations(MusicNoteName noteName, RunData data){
		int increment = 0;
		int noteIntValue = data.nameToInt(noteName.getBaseNoteName());
		
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
	 * @return And absolute note with the result of apply the interval to the note
	 */
	public AbsoluteMusicNote Apply(MusicNote note, RunData data){
		
		AbsoluteMusicNote aNote = (note.toAbsoluteMusicNote(data));

		int octave = aNote.getOctave().intValue();
		BasicNoteName bName = (BasicNoteName) aNote.getMusicNoteName().getBaseNoteName().clone();
		MusicNoteName noteName = aNote.getMusicNoteName().clone();
	
		AbsoluteMusicNote result;
		int aux = 0;
		int semitones = this.semitones();
		int semitones2Notes = 0;
		
		// Generating the new octave value
		octave += this.octaveAlterations(aNote.getMusicNoteName(), data);
		
		// Generating the new noteName
		aux = (data.nameToInt(bName) + number.intValue());
		bName.setValue((data.intToName((aux-1)%7).getValue()));
		noteName.getBaseNoteName().setValue(bName.getValue());
		//bName.setValue((data.intToName((aux-1)%7).getValue()));
		
		
		// Fixing the alteration
		semitones2Notes = data.semitones2Notes(aNote.getMusicNoteName(), bName);
		aux = semitones - semitones2Notes;
		
		if(aux == 1){
			//Adding a Sharp
			SharpAlteration alteration = new SharpAlteration(true);
			AlteredNoteName alteredName = new AlteredNoteName(bName,alteration);
			result = new AbsoluteMusicNote(new IntegerModel(octave),alteredName);
		}
		else if (aux == 2){
			//Adding a DoubleSharp
			DoubleSharpAlteration alteration = new DoubleSharpAlteration(true);
			AlteredNoteName alteredName = new AlteredNoteName(bName,alteration);
			result = new AbsoluteMusicNote(new IntegerModel(octave),alteredName);
		}
		else if (aux == -1){
			//Adding a Flat
			FlatAlteration alteration = new FlatAlteration(true);
			AlteredNoteName alteredName = new AlteredNoteName(bName,alteration);
			result = new AbsoluteMusicNote(new IntegerModel(octave),alteredName);
		}
		else if (aux == -2){
			//Adding a DoubleFlat
			DoubleFlatAlteration alteration = new DoubleFlatAlteration(true);
			AlteredNoteName alteredName = new AlteredNoteName(bName,alteration);
			result = new AbsoluteMusicNote(new IntegerModel(octave),alteredName);
		}
		else if(aux > 2 || aux <-2 || aux == 0){
			// No changes
			result = new AbsoluteMusicNote(new IntegerModel(octave), bName);
		}
		
		else{
			System.err.println("Error2: Error in Apply...");
			System.exit(2);
			return null;
		}
		return result;
	}
}
