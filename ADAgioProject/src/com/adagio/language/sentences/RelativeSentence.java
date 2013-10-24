package com.adagio.language.sentences;
import com.adagio.language.*;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

import org.modelcc.*;


@Prefix("(?i)relative")
public class RelativeSentence extends Sentence implements IModel {

	AbsoluteMusicNote value;
	
	@Override
	public void run(RunData data) {
		data.setRelative(this.value);
	}

}