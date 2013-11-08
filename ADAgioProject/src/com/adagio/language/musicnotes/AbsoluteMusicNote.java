package com.adagio.language.musicnotes;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.types.IntegerModel;

import com.adagio.language.RunData;

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
	
	
}
