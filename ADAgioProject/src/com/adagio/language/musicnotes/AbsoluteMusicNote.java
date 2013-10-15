package com.adagio.language.musicnotes;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.types.IntegerModel;

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
	@Override
	public String toString() {
		String result = musicNoteName.toString();
		int intOctave = octave.intValue();
		int absOctave = Math.abs(intOctave);
		
		for(int i = 0; i < absOctave; i++){
			if(intOctave > 0){
				result += "'";
			}
			else{
				result += ",";
			}
		}
		return result;
	}
	public IntegerModel getOctave() {
		return octave;
	}
	public void setOctave(IntegerModel octave) {
		this.octave = octave;
	}
	public MusicNoteName getMusicNoteName() {
		return musicNoteName;
	}
	public void setMusicNoteName(MusicNoteName noteName) {
		this.musicNoteName = noteName;
	}
	
	
}
