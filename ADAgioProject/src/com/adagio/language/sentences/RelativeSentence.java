package com.adagio.language.sentences;
import com.adagio.language.*;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

import org.modelcc.*;


@Prefix("(?i)relative")
public class RelativeSentence extends Sentence implements IModel {

	AbsoluteMusicNote val;
	/*@Constraint
	   public boolean check() {
		    RunData data = new RunData();
		     if (val.getMusicNoteName().toString(data).equals("C")){
		    	 return true;
		     }
		     return false;
		    }*/
	
	@Override
	public void run(RunData data) {
		data.setRelativeNote(val.getMusicNoteName().toString(data).toUpperCase());
		data.setRelativeOctave(val.getOctave().intValue());
	}

}