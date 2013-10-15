package com.adagio.language.musicnotes;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.types.SignedIntegerModel;

public class AbsoluteMusicNote extends MusicNote implements IModel {
	  
	SignedIntegerModel octave;
	@Constraint
    public boolean check() {
      if (octave.intValue() >=-5 && octave.intValue() <=5) {
        return true;
      }
      return false;
    }
  
	MusicNoteName noteName;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
