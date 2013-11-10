package com.adagio.language.musicnotes;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.types.IntegerModel;

import com.adagio.language.RunData;
import com.adagio.language.musicnotes.notealterations.Alteration;

public class AbsoluteMusicNote extends MusicNote implements IModel {
	  
	IntegerModel octave;
	
	@Constraint
    public boolean check() {
      if (octave.intValue() >=-5 && octave.intValue() <=5) {
        return true;
      }
      return false;
    }
	
	MusicNoteName musicNoteName;
	
	//------CONSTRUCTORS------//
	public AbsoluteMusicNote(){}
	
	public AbsoluteMusicNote(AbsoluteMusicNote aNote){
		this.octave = new IntegerModel(aNote.octave.intValue());
		this.musicNoteName = aNote.getMusicNoteName().clone();
	}

	public AbsoluteMusicNote(IntegerModel octave, MusicNoteName musicNoteName) {
		super();
		this.octave = octave;
		this.musicNoteName = musicNoteName;
	}

	
	public AbsoluteMusicNote(int octave, String noteName){
		this.setOctave(octave);
		this.setMusicNoteName(noteName);
	}
	//------------------------//
	
	public AbsoluteMusicNote clone(){
		AbsoluteMusicNote aMusicNote = new AbsoluteMusicNote();
		aMusicNote.setMusicNoteName(this.musicNoteName);
		aMusicNote.setOctave(this.octave);
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
	 * Checks if the actual AbsoluteMusicNote is Higher int the stave
	 * @param note AbsoluteMusicNote to compare with
	 * @return true if is higher. False in other way.
	 */
	public boolean isHigher(AbsoluteMusicNote note){
		boolean higher = false;
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
		return higher;
	}
	
	/**
	 * TODO test this function
	 * Calculate the next absoluteMusicNote (Based on next BasicNoteName)
	 * @return The next absoluteMusicNote
	 */
	public AbsoluteMusicNote next(){
		// this == "B"
		AbsoluteMusicNote result;
		int octave = this.getOctave().intValue();
		BasicNoteName bName;
		MusicNoteName noteName;
		Alteration alteration;
		
		if(this.getMusicNoteName().getBaseNoteName().getValue().equals(BasicNoteName.getPattern().get(BasicNoteName.B))){
			octave++;
		}
		if(this.getMusicNoteName().getAlteration() != null){
			bName = this.getMusicNoteName().getBaseNoteName().next();
			alteration = this.getMusicNoteName().getAlteration().clone();
			noteName = new AlteredNoteName(bName, alteration);
			result = new AbsoluteMusicNote(new IntegerModel(octave),noteName);
		}
		else{
			bName = this.getMusicNoteName().getBaseNoteName().next();
			result = new AbsoluteMusicNote(octave,bName.getValue());
		}
		return result;
	}
	
	/**
	 * TODO test this function
	 * Calculate the previous absoluteMusicNote (Based on next BasicNoteName)
	 * @return The previous absoluteMusicNote
	 */
	public AbsoluteMusicNote previous(){
		// this == "C"
		AbsoluteMusicNote result = new AbsoluteMusicNote();
		if(this.getMusicNoteName().getBaseNoteName().equals(BasicNoteName.getPattern().get(BasicNoteName.C))){
			result.setOctave(this.getOctave().intValue()-1);
		}
		result.getMusicNoteName().getBaseNoteName().setValue(this.getMusicNoteName().getBaseNoteName().previous().getValue());
		return result;
	}
	
	public void increaseOctave(){
		this.setOctave(this.getOctave().intValue()+1);
	}
	
	public void decreaseOctave(){
		this.setOctave(this.getOctave().intValue()-1);
	}
	
	
	public int semitones2Notes2(AbsoluteMusicNote aNote){
		int semitones = 0;
		AbsoluteMusicNote aNote1 = this.clone();
		AbsoluteMusicNote aNote2 = aNote.clone();
		Alteration alteration1 = this.getMusicNoteName().getAlteration();
		Alteration alteration2 = aNote.getMusicNoteName().getAlteration();
		
		while(!aNote1.getBasicNoteNameString().equals(aNote2.getBasicNoteNameString())
			|| aNote1.octave.intValue() != aNote2.octave.intValue()	
		){
			semitones += aNote1.getMusicNoteName().getBaseNoteName().semitonesToNextNote();
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
		
		return semitones;
	}
	
	
	//TODO complete and test this function
	public int semitones2Notes(AbsoluteMusicNote name2){
		int semitones = 0;
		int note1 =  BasicNoteName.nameToInt(this.getMusicNoteName().getBaseNoteName());
		int note2 =  BasicNoteName.nameToInt(name2.getMusicNoteName().getBaseNoteName());
		Alteration alteration1 = this.getMusicNoteName().getAlteration();
		Alteration alteration2 = name2.getMusicNoteName().getAlteration();
		AbsoluteMusicNote aNote1 = this.clone();
		AbsoluteMusicNote aNote2 = name2.clone();
		
		if (name2.isHigher(this)) {
			
			/*if(name2.getOctave().intValue() > this.getOctave().intValue()){
				semitones += 12*Math.abs((name2.getOctave().intValue() - this.getOctave().intValue()));
			}*/
			
			while (note1 != note2 || aNote1.octave.intValue() != aNote2.octave.intValue()) {
				semitones += (BasicNoteName.intToName(note1)).semitonesToNextNote();
				note1++;
				if(note1 >= BasicNoteName.NUM_NOTES){
					note1 = note1 % BasicNoteName.NUM_NOTES;
				}
				if(note1 == 2){
					aNote1.increaseOctave();
				}
				
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
		}
		return semitones;
	}
	
	
	@Override
	public AbsoluteMusicNote toAbsoluteMusicNote(RunData data) {
		return this;
	}
	
	public boolean equals (AbsoluteMusicNote aNote){
	
		if(this.getClass().equals(aNote.getClass())){
			if(this.getOctave().intValue() == aNote.getOctave().intValue() 
				&&	this.getMusicNoteName().equals(aNote.getMusicNoteName()))
			{
				return true;
			}
				
		}
		return false;
	}

	@Override
	public String toString() {
		return this.getOctave().toString() +  this.getMusicNoteName().toString();
	}
	
	
}
