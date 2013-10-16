package com.adagio.language.sentences;

import com.adagio.language.*;
import com.adagio.language.clefs.Clef;

import org.modelcc.*;


@Prefix("(?i)clef")
public class ClefSentence extends Sentence implements IModel {

	 Clef clef;
	
	@Override
	public void run(RunData data) {
		data.setClef(clef.toString());
	}

}
