package com.adagio.language;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.MusicNoteName;
import com.adagio.language.channels.Channel;
import com.adagio.language.channels.ChannelIdentifier;
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
	
	// Data Base of defined chords
	Map<ChordIdentifier,List<Interval>> chordsDB;
	
	// Data Base of defined channels
	Map<ChannelIdentifier,Channel> channelsDB;
		
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
		channelsDB = new HashMap<ChannelIdentifier,Channel>();
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


	public Map<ChannelIdentifier, Channel> getChannelsDB() {
		return channelsDB;
	}


	public void setChannelsDB(Map<ChannelIdentifier, Channel> channelsDB) {
		this.channelsDB = channelsDB;
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
	 * Looks up the chord identifier in the Data base
	 * @param chord 
	 * @return True if is Defined. False in other case
	 */
	public boolean isDefined(Chord chord){
		return this.chordsDB.containsKey(chord.getIdentifier());
	}
	

}
