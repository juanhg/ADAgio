package com.adagio.language.musicnotes;

import java.util.List;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.types.IntegerModel;

import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.structures.Duration;

public class AbsoluteMusicNote extends MusicNote implements IModel {
	  
	private IntegerModel octave;
	private MusicNoteName musicNoteName;
	
	
	@Constraint
	public boolean silenceRestriction(){
		if(musicNoteName.isSilence()){
			return false;
		}
		return true;
	}
	
	//------CONSTRUCTORS------//
	public AbsoluteMusicNote(){}
	
	public AbsoluteMusicNote(AbsoluteMusicNote aNote){
		this.octave = new IntegerModel(aNote.octave.intValue());
		this.musicNoteName = aNote.getMusicNoteName().clone();
		this.optional = false;
		this.duration = null;
		this.ligatured = false;
	}

	public AbsoluteMusicNote(IntegerModel octave, MusicNoteName musicNoteName) {
		super();
		this.octave = octave;
		this.musicNoteName = musicNoteName;
		this.optional = false;
		this.duration = null;
		this.ligatured = false;
	}

	
	public AbsoluteMusicNote(int octave, String noteName){
		this.setOctave(octave);
		this.setMusicNoteName(noteName);
		this.optional = false;
		this.duration = null;
		this.ligatured = false;
	}
	
	public AbsoluteMusicNote(int octave, String noteName, Duration duration){
		this.setOctave(octave);
		this.setMusicNoteName(noteName);
		this.duration = duration;
		this.optional = false;
		this.ligatured = false;
	}
	
	public AbsoluteMusicNote(int octave, String noteName, Duration duration, boolean ligature){
		this.setOctave(octave);
		this.setMusicNoteName(noteName);
		this.duration = duration;
		this.ligatured = ligature;
		this.optional = false;
	}
	
	public AbsoluteMusicNote(int octave, String noteName, Duration duration, boolean ligature, boolean optional){
		this.setOctave(octave);
		this.setMusicNoteName(noteName);
		this.duration = duration;
		this.ligatured = ligature;
		this.optional = optional;
	}
	
	public AbsoluteMusicNote(int octave, String noteName, Alteration alteration){
		this.octave = new IntegerModel(octave);
		this.musicNoteName = new AlteredNoteName(new BasicNoteName(noteName), alteration);
		this.optional = false;
		this.duration = null;
		this.ligatured = false;
	}

	//------------------------//
	
	public AbsoluteMusicNote clone(){
		AbsoluteMusicNote aMusicNote = new AbsoluteMusicNote();
		aMusicNote.musicNoteName = this.musicNoteName.clone();
		aMusicNote.octave = this.octave;
		aMusicNote.ligatured = this.ligatured;
		aMusicNote.optional = this.optional;
		if(duration != null){
			aMusicNote.duration = this.duration.clone();
		}
		
		return aMusicNote;
	}
		
	public IntegerModel getOctave() {
		return octave;
	}
	public void setOctave(IntegerModel octave) {
		this.octave = octave;
	}
	
	public void setOctave(int octave) {
		IntegerModel aux = new IntegerModel(octave);
		this.octave =  aux;
	}
	
	public MusicNoteName getMusicNoteName() {
		return musicNoteName;
	}
	public void setMusicNoteName(MusicNoteName noteName) {
		this.musicNoteName = noteName;
	}
	public void setMusicNoteName(BasicNoteName name){
		this.musicNoteName = name;
	}
	public void setMusicNoteName(String name){
		BasicNoteName bNoteName = new BasicNoteName();
		bNoteName.setValue(name);
		this.musicNoteName = bNoteName;
	}

	public String getBasicNoteNameString(){
		return this.getMusicNoteName().getBaseNoteName().getValue();
	}
	
	/**
	 * Checks if the actual AbsoluteMusicNote is Higher in the stave.
	 * DOESN'T LOOK THE ALTERATIONS.
	 * @param note AbsoluteMusicNote to compare with
	 * @return true if is higher. False in other way.
	 */
	public boolean isHigher(AbsoluteMusicNote note){
		int distance = 0;
		
		if(this.getOctave().intValue() > note.getOctave().intValue()){
			return true;
		}
		else if(this.getOctave().intValue() == note.getOctave().intValue()){	
			distance = this.getMusicNoteName().getBaseNoteName().distance(note.getMusicNoteName().getBaseNoteName());
			if(distance >= 0){
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Calculates the next absoluteMusicNote without alterations (Based on next BasicNoteName) 
	 * @return The next absoluteMusicNote
	 */
	public AbsoluteMusicNote next(){
		
		AbsoluteMusicNote result;
		int octave = this.getOctave().intValue();
		BasicNoteName bName;
		
		// this == "B"
		if(this.getMusicNoteName().getBaseNoteName().getValue().equals(BasicNoteName.getPattern().get(BasicNoteName.B))){
			octave++;
		}		
		
		bName = this.getMusicNoteName().getBaseNoteName().next();
		result = new AbsoluteMusicNote(octave,bName.getValue());
		
		return result;
	}
	
	/**
	 * Calculates the previous absoluteMusicNote without alterations (Based on next BasicNoteName)
	 * @return The previous absoluteMusicNote
	 */
	public AbsoluteMusicNote previous(){
		AbsoluteMusicNote result;
		int octave = this.getOctave().intValue();
		BasicNoteName bName;
		
		// this == "C"
		if(this.getMusicNoteName().getBaseNoteName().getValue().equals(BasicNoteName.getPattern().get(BasicNoteName.C))){
			octave--;
		}		
		
		bName = this.getMusicNoteName().getBaseNoteName().previous();
		result = new AbsoluteMusicNote(octave,bName.getValue());
		
		return result;
	}
	
	public void increaseOctave(int increment){
		for(int i = 0; i < increment; i++){
			this.increaseOctave();
		}
	}
	
	public void increaseOctave(){
		this.setOctave(this.getOctave().intValue()+1);
	}
	
	public void decreaseOctave(int increment){
		for(int i = 0; i < increment; i++){
			this.decreaseOctave();
		}
	}
	
	public void decreaseOctave(){
		this.setOctave(this.getOctave().intValue()-1);
	}
	
	
	/**
	 * Return the pos of the lower note in a List of AbsoluteMusicNote
	 * @param aNotes List of absolute notes
	 * @return Integer that indicates the position of the lower note
	 */
	public static int lowerNotePosition(List<AbsoluteMusicNote> aNotes){

		int referenceSemitones = 0;
		int semitones = 0;
		int posLower = 0;
		
		if(aNotes.size() != 0 && aNotes != null){
			
			//Find the lower note
			for(int i = 0; i < aNotes.size(); i++){
				semitones = aNotes.get(0).semitonesTill(aNotes.get(i));
				if(semitones < referenceSemitones){
					referenceSemitones = semitones;
					posLower = i;
				}
			}
		}
		else{
			System.err.println("(posLowerNote) Error 13: Null or Empty List.");
			System.exit(13);
		}
		return posLower;
	}
	
	
	/**
	 * Calculates the number of semitones between the actual note an aNote
	 * @param aNote 
	 * @return A positive int of semitones if aNote is higher than this. A negative 
	 * integer if aNote is not higher than this. Zero if are the same notes
	 */
	public int semitonesTill(AbsoluteMusicNote aNote){
		int semitones = 0;
		AbsoluteMusicNote aNote1 = null;
		AbsoluteMusicNote aNote2 = null;
		
		if(aNote.isHigher(this)){
			aNote1 = this.clone();
			aNote2 = aNote.clone();
		}
		else{
			aNote2 = this.clone();
			aNote1 = aNote.clone();
		}
		
		Alteration alteration1 = aNote1.getMusicNoteName().getAlteration();
		Alteration alteration2 = aNote2.getMusicNoteName().getAlteration();
		
		while(!aNote1.getBasicNoteNameString().equals(aNote2.getBasicNoteNameString())
			|| aNote1.octave.intValue() != aNote2.octave.intValue()	
		){
			semitones += aNote1.getMusicNoteName().getBaseNoteName().semitonesToNext();
			aNote1 = aNote1.next();
		}
		
		if (alteration1 != null) {
			if (alteration1.getValue().equals("#")) {
				semitones--;
			} else if (alteration1.getValue().equals("x")) {
				semitones = semitones - 2;
			} else if (alteration1.getValue().equals("b")) {
				semitones++;
			} else if (alteration1.getValue().equals("bb")) {
				semitones = semitones + 2;
			}
		}

		if (alteration2 != null) {
			if (alteration2.getValue().equals("#")) {
				semitones++;
			} else if (alteration2.getValue().equals("x")) {
				semitones = semitones + 2;
			} else if (alteration2.getValue().equals("b")) {
				semitones--;
			} else if (alteration2.getValue().equals("bb")) {
				semitones = semitones - 2;
			}
		}
		
		if(aNote.isHigher(this)){
			return semitones;
		}
		else{
			return -semitones;
		}
	}
	
	@Override
	public AbsoluteMusicNote toAbsoluteMusicNote(AbsoluteMusicNote relative) {
		return this;
	}
	
	@Override
	public String toString() {
		String composition = "";
		composition += this.getOctave().toString() +  this.getMusicNoteName().toString();
		if(duration != null){
			composition += duration.toString();
		}
		if(ligatured){
			composition += "~";
		}
		if(optional){
			composition = "(" +  composition + ")";
		}
		return composition;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!super.equals(obj)){
			return false;
		}
		else{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AbsoluteMusicNote other = (AbsoluteMusicNote) obj;
			if (musicNoteName == null) {
				if (other.musicNoteName != null)
					return false;
			} else if (!musicNoteName.equals(other.musicNoteName))
				return false;
			if (octave == null) {
				if (other.octave != null)
					return false;
			} else if (!(octave.intValue() == other.octave.intValue()))
				return false;
		}
		return true;
	}
	
	
	
	

	@Override
	public boolean isSilence() {
		return this.musicNoteName.isSilence();
	}


	/**
	 * Generates an AbsoluteMusicNote that represents a silence
	 * @return Silence-AbsoluteMusicNote. If duration is no valid -> null
	 */
	public static AbsoluteMusicNote genSilence(Duration duration) {
		if(duration.duration() <= 0){
			System.err.println("Error (genSilence): Invalid duration value");
			return null;
		}
		return new AbsoluteMusicNote(0, BasicNoteName.silencePattern, duration.clone());
	}
	
	/**
	 * Generates an AbsoluteMusicNote that represents a silence
	 * @return Silence note without duration
	 */
	public static AbsoluteMusicNote genSilence() {
		return new AbsoluteMusicNote(0, BasicNoteName.silencePattern);
	}
	
	
	
	
}
