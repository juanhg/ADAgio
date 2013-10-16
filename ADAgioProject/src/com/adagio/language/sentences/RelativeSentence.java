package com.adagio.language.sentences;
import com.adagio.language.*;
import com.adagio.language.musicnotes.RelativeMusicNote;
import org.modelcc.*;


@Prefix("(?i)relative")
public class RelativeSentence extends Sentence implements IModel {

	RelativeMusicNote val;
	@Constraint
	   public boolean check() {
		    RunData data = new RunData();
		     if (val.getMusicNoteName().toString(data).equals("c")){
		    	 return true;
		     }
		     return false;
		    }
	
	@Override
	public void run(RunData data) {
		//Clean the relative value. It produces interferences with the new one.
		data.setRelative(null);
		data.setRelative(val.toString(data));
	}

}