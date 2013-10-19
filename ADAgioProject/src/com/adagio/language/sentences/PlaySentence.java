package com.adagio.language.sentences;
import com.adagio.language.*;
import com.adagio.language.musicnotes.MusicNote;

import org.modelcc.*;

@Prefix("(?i)play")
public class PlaySentence extends Sentence implements IModel {

	MusicNote [] notes;
	
	@Override
	public void run(RunData data) {
		String aux = "";
		
		for(MusicNote current: notes){
			aux = current.toString(data);
			data.notes += aux;
			data.notes += " ";
			
			data.updateRelative(aux);
		}
		data.notes += "\n";
	}

}
