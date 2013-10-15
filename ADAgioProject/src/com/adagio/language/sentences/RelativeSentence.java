package com.adagio.language.sentences;
import com.adagio.language.*;
import com.adagio.language.musicnotes.RelativeMusicNote;
import org.modelcc.*;


@Prefix("(?i)relative")
public class RelativeSentence extends Sentence implements IModel {

	RelativeMusicNote val;
	
	   public boolean check() {
		     if (val.getNoteName().toString() == "c"){
		    	 return true;
		     }
		     return false;
		    }
	
	@Override
	public void run(RunData data) {
		data.setRelative(val.toString());
	}

}