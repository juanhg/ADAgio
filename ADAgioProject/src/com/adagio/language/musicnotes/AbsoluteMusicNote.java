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
	
	
	public AbsoluteMusicNote(int octave, String noteName){
		this.setOctave(octave);;
		this.setMusicNoteName(noteName);
	}
	
	public AbsoluteMusicNote(){}
	
	static public String toString(String noteName, int octave){
		String result = noteName;
		
		for(int i = 0; i < Math.abs(octave); i++){
			if(octave > 0){
				result += "'";
			}
			else{
				result += ",";
			}
		}
		
		return result;
	}
	
	/**
	 * Obtain the absolute note's form in ADAgio syntax
	 * @param data
	 * @return A String that contains the absolute form o the note
	 */
	/*public String toAbsolute(RunData data){
		String result = this.octave.toString(); 
		result += musicNoteName.toString(data);
		
		return result;
	}*/
	
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
	
	@Override
	public AbsoluteMusicNote toAbsoluteMusicNote(RunData data) {
		return this;
	}
	
	
}
