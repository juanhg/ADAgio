package com.adagio.language.sentences;
import com.adagio.language.*;
import com.adagio.language.chords.Chord;

import org.modelcc.*;

@Prefix("(?i)play")
public class PlaySentence extends Sentence implements IModel {

	Chord [] chords;
	
	@Override
	public void run(RunData data) {
		//AbsoluteMusicNote aMusicNote = new AbsoluteMusicNote();
		
		for(Chord current: chords){
			data.getChordsBar().add(current);
		}
	}

}
