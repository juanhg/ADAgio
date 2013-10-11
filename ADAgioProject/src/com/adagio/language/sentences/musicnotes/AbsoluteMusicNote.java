package com.adagio.language.sentences.musicnotes;

import org.modelcc.Constraint;
import org.modelcc.types.SignedIntegerModel;

public class AbsoluteMusicNote {
	  
	SignedIntegerModel octave;
	@Constraint
    public boolean check() {
      if (octave.intValue() >=-5 && octave.intValue() <=5) {
        return true;
      }
      return false;
    }
  
	MusicNoteName noteName;
	
}
