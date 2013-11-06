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

	private static final int A = 0;
	private static final int B = 1;
	private static final int C = 2;
	private static final int D = 3;
	private static final int E = 4;
	private static final int F = 5;
	private static final int G = 6;
	
	
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
	        //Error (?)
	        mayorIntervals.put(10, 16);
	        mayorIntervals.put(13, 21);
	        mayorIntervals.put(14, 24);
	    }
	 
	 /**
	   * Map<NumberOfInterval, Semitones>
	   */    
	private static final Map<Integer,Integer> perfectIntervals = new HashMap<Integer,Integer>();
		static {
			perfectIntervals.put(1,0);
			perfectIntervals.put(4,5);
			perfectIntervals.put(5,7);
			//Error (?)
			perfectIntervals.put(8,12);
			perfectIntervals.put(11,17);
			perfectIntervals.put(12,19);
		}

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

	
	
	public static Map<Integer, Integer> getMayorintervals() {
		return mayorIntervals;
	}



	public static Map<Integer, Integer> getPerfectintervals() {
		return perfectIntervals;
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
	


	/**
	 * Calculate the shortest distance between two notes
	 * @param note1 Previous note
	 * @param note2 Next note
	 * @return Int value in the range [-3,3]
	 */
	public int noteDistance(BasicNoteName note1, BasicNoteName note2){
				
		int result = 0;
		int result1 = 0;
		int result2 = 0;
		int data1 = 0;
		int data2 = 0;
				
		data1 = this.nameToInt(note1);
		data2 = this.nameToInt(note2);
		
		result1 = Math.abs(data1 - data2);
		result2 = Math.abs(7 - result1)%7;
		
		if(result1 < result2){
			if(data1 > data2){
				result = -result1;
			}
			else{
				result = result1;
			}
		}
		else{
			if(data1 < data2){
				result = -result2;
			}
			else{
				result = result2;
			}
		}
		
		return result;
	}
	
	/**
	 * Return the int value of a BasicNoteName
	 * @param bName
	 * @return
	 */
	public int nameToInt(BasicNoteName bName){
		
		String value = bName.getValue();
		
		if(value.equals("A")){
			return A;
		}
		else if (value.equals("B")){
			return B;
		}
		else if (value.equals("C")){
			return C;
		}
		else if (value.equals("D")){
			return D;
		}
		else if (value.equals("E")){
			return E;
		}
		else if (value.equals("F")){
			return F;
		}
		else if (value.equals("G")){
			return G;
		}
		return -1;
	}
	
	/**
	 * Return a BasicNoteName 
	 * @param number
	 * @return
	 */
	public BasicNoteName intToName(int number){
		BasicNoteName bName = new BasicNoteName();
		
		if(number == A){
			bName.setValue("A");
		}
		else if (number == B){
			bName.setValue("B");
		}
		else if (number == C){
			bName.setValue("C");
		}
		else if (number == D){
			bName.setValue("D");
		}
		else if (number == E){
			bName.setValue("E");
		}
		else if (number == F){
			bName.setValue("F");
		}
		else if (number == G){
			bName.setValue("G");
		}
		return bName;
	}
			
	public int alterationFromReference(MusicNoteName note){
		
		boolean up = false;
		boolean down = false;
			
		String rNoteName = this.getRelative().getBasicNoteNameString();
		int octave = this.getRelative().getOctave().intValue();
		
		int distance = this.noteDistance(this.getRelative().getMusicNoteName().getBaseNoteName(), note.getBaseNoteName());
		
		
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
	
	/*
	 * Calculate the number of semitones between a note and the next
	 */
	public int semitonestoNextNote(BasicNoteName name1){
		if(name1.getValue().equals("E") || name1.getValue().equals("B")){
			return 1;
		}
		else{
			return 2;
		}
			
	}
	
	/**
	 * Calculate the number of semitones between a two notes (superior one)
	 * @param name1
	 * @param name2
	 * @return Number of semitones
	 */
	public int semitones2Notes(MusicNoteName name1, MusicNoteName name2){
		int semitones = 0;
		int note1 = this.nameToInt(name1.getBaseNoteName());
		int note2 = this.nameToInt(name2.getBaseNoteName());
		Alteration alteration1 = name1.getAlteration();
		Alteration alteration2 = name2.getAlteration();
		
		while(note1 != note2){
			semitones += this.semitonestoNextNote(this.intToName(note1));
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
