package com.adagio.language.sentences;
import com.adagio.language.*;
import com.adagio.language.sentences.musicnotes.MusicNote;

import org.modelcc.*;

@Prefix("(?i)play")
public class PlaySentence extends Sentence implements IModel {

	MusicNote [] notes;
	
	@Override
	public void run(RunData data) {
		
		for(MusicNote current: notes){
			data.notes += current.getValue();
			data.notes += " ";
		}
		data.notes += "\n";
	}

}
