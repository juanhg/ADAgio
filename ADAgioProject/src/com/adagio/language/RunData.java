package com.adagio.language;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.MusicNoteName;
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

	public Vector<AbsoluteMusicNote> notesBar;
	public Vector<Chord> chordsBar;
	
	// Mode relative	
	AbsoluteMusicNote relative;
	
	// Clefe (bass,treble...)
	public String clef;
	
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
	public int noteDistance(String note1, String note2){

		int result = 0;
		int result1 = 0;
		int result2 = 0;
		int data1 = 0;
		int data2 = 0;
		
		note1.toUpperCase();
		note2.toUpperCase();
		
		if(note1.equals("A")){
			data1 = A;
		}
		else if(note1.equals("B")){
			data1 = B;
		}
		else if(note1.equals("C")){
			data1 = C;
		}
		else if(note1.equals("D")){
			data1 = D;
		}
		else if(note1.equals("E")){
			data1 = E;
		}
		else if(note1.equals("F")){
			data1 = F;
		}
		else if(note1.equals("G")){
			data1 = G;
		}
		
		if(note2.equals("A")){
			data2 = A;
		}
		else if(note2.equals("B")){
			data2 = B;
		}
		else if(note2.equals("C")){
			data2 = C;
		}
		else if(note2.equals("D")){
			data2 = D;
		}
		else if(note2.equals("E")){
			data2 = E;
		}
		else if(note2.equals("F")){
			data2 = F;
		}
		else if(note2.equals("G")){
			data2 = G;
		}
	
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
	
	public int alterationFromReference(MusicNoteName note){
		
		boolean up = false;
		boolean down = false;
			
		String rNoteName = this.getRelative().getBasicNoteNameString();
		int octave = this.getRelative().getOctave().intValue();
		
		int distance = this.noteDistance(rNoteName, note.getBaseNoteName().getValue());
		
		
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
	

}
