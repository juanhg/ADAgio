package com.adagio.language;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.MusicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.chords.Chord;
import com.adagio.language.chords.ChordIdentifier;
import com.adagio.language.chords.intervals.Interval;


/**
 * Storages the knowledge to create the Lilypond's file
 * @author Wungo
 */
public class RunData {


	public Vector<AbsoluteMusicNote> notesBar;
	public Vector<Chord> chordsBar;
	
	// Mode relative	
	AbsoluteMusicNote relative;
	
	// Clefe (bass,treble...)
	public String clef;
	
	// Time (4/4, 6/8,...)
	public Time time;
	
	// Data Base of chords defined
	Map<ChordIdentifier,List<Interval>> chordsDB;
		
	/**
	 * Class constructor
	 */
	public RunData(){
			
		notesBar = new Vector<AbsoluteMusicNote>();
		chordsBar = new Vector<Chord>();
		relative = new AbsoluteMusicNote(2, "C");
		clef = "treble";
		time = new Time(4,4);
		chordsDB = new HashMap<ChordIdentifier,List<Interval>>();
	}

	
	public Vector<AbsoluteMusicNote> getNotesBar() {
		return notesBar;
	}



	public void setNotesBar(Vector<AbsoluteMusicNote> notesBar) {
		this.notesBar = notesBar;
	}



	public Vector<Chord> getChordsBar() {
		return chordsBar;
	}



	public void setChordsBar(Vector<Chord> chordsBar) {
		this.chordsBar = chordsBar;
	}



	public Map<ChordIdentifier, List<Interval>> getChordsDB() {
		return chordsDB;
	}



	public void setChordsDB(Map<ChordIdentifier, List<Interval>> chordsDB) {
		this.chordsDB = chordsDB;
	}



	public String getClef() {
		return clef;
	}

	public void setClef(String clef) {
		this.clef = clef;
	}
	
	

	public Time getTime() {
		return time;
	}



	public void setTime(Time time) {
		this.time = time;
	}



	public AbsoluteMusicNote getRelative() {
		return relative;
	}
	
	public void setRelative(AbsoluteMusicNote relative) {
		this.relative = relative;
	}
	
			
	public int alterationFromReference(MusicNoteName note){
		
		boolean up = false;
		boolean down = false;
			
		String rNoteName = this.getRelative().getBasicNoteNameString();
		int octave = this.getRelative().getOctave().intValue();
		
		int distance = this.getRelative().getMusicNoteName().getBaseNoteName().shortestDistance(note.getBaseNoteName());
		
		if(distance == 3 && (rNoteName.equals("A") || rNoteName.equals("B")|| rNoteName.equals("C"))){
			up = true;
		}
		else if(distance == 2 && (rNoteName.equals("A") || rNoteName.equals("B"))){
			up = true;
		}
		else if(distance == 1 && (rNoteName.equals("B"))){
			up = true;
		}
		else if(distance == -3 && (rNoteName.equals("C") || rNoteName.equals("D") || rNoteName.equals("E"))){
			down = true;
		}
		else if(distance == -2 && (rNoteName.equals("C") || rNoteName.equals("D"))){
			down = true;
		}
		else if(distance == -1 && (rNoteName.equals("C"))){
			down = true;
		}
		
		if(up){ octave++;}
		else if(down){ octave--;}
		
		return octave;
	}
	
	/**
	 * Calculate the number of semitones between a two notes (superior one)
	 * @param name1
	 * @param name2
	 * @return Number of semitones
	 */
	public int semitones2Notes(MusicNoteName name1, MusicNoteName name2){
		int semitones = 0;
		int note1 =  BasicNoteName.nameToInt(name1.getBaseNoteName());
		int note2 =  BasicNoteName.nameToInt(name2.getBaseNoteName());
		Alteration alteration1 = name1.getAlteration();
		Alteration alteration2 = name2.getAlteration();
		
		while(note1 != note2){
			semitones += (BasicNoteName.intToName(note1)).semitonesToNextNote();
			note1 = (note1 +1)%7;
		}
		
		if(alteration1 != null){
			if(alteration1.getValue().equals("#")){
				semitones--;
			}
			else if(alteration1.getValue().equals("x")){
				semitones = semitones-2;
			}
			else if(alteration1.getValue().equals("b")){
				semitones++;
			}
			else if(alteration1.getValue().equals("bb")){
				semitones = semitones+2;
			}
		}
		
		if(alteration2 != null){
			if(alteration2.getValue().equals("#")){
				semitones++;
			}
			else if(alteration2.getValue().equals("x")){
				semitones = semitones+2;
			}
			else if(alteration2.getValue().equals("b")){
				semitones--;
			}
			else if(alteration2.getValue().equals("bb")){
				semitones = semitones-2;
			}
		}
		return semitones;
	}
	
	/**
	 * Looks up the chord identifier in the Data base
	 * @param chord 
	 * @return True if is Defined. False in other case
	 */
	public boolean isDefined(Chord chord){
		return this.chordsDB.containsKey(chord.getIdentifier());
	}
	

}
