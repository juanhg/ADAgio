package com.adagio.language.sentences;

import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.language.RunData;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.MusicNote;

/**
 * THIS IS A TEST CLASS. WILL BE ERASED OR REFACTOR IN A FUTURE
 * @author Wungo
 */

@Prefix("(?i)PLAYNOTES")
public class PlayNotesSentence extends Sentence implements IModel {
	MusicNote [] notes;
	
	@Override
	public void run(RunData data) {
		AbsoluteMusicNote aMusicNote = new AbsoluteMusicNote();
		
		for(MusicNote current: notes){
			aMusicNote = current.toAbsoluteMusicNote(data);
			data.notesBar.add(aMusicNote);
			data.setRelative(aMusicNote);
		}
	}
}
