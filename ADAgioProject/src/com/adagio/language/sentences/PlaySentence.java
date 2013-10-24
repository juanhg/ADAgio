package com.adagio.language.sentences;
import com.adagio.language.*;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.MusicNote;

import org.modelcc.*;

@Prefix("(?i)play")
public class PlaySentence extends Sentence implements IModel {

	MusicNote [] notes;
	
	@Override
	public void run(RunData data) {
		AbsoluteMusicNote aMusicNote = new AbsoluteMusicNote();
		
		for(MusicNote current: notes){
			aMusicNote = current.toAbsoluteMusicNote(data);
			data.bars.add(aMusicNote);
			data.setRelative(aMusicNote);
		}
	}

}
