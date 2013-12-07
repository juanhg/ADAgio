package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;

import com.adagio.events.MusicEventListener;
import com.adagio.language.musicnotes.MusicNote;

/**
 * THIS IS A TEST CLASS. WILL BE ERASED OR REFACTOR IN A FUTURE
 * @author Wungo
 */

@Prefix("(?i)PLAYNOTES")
public class PlayNotesStatement extends Statement implements IModel {
	@Multiplicity(minimum = 1)
	MusicNote [] notes;
	
	@Override
	public void run(MusicEventListener listener) {
		/*AbsoluteMusicNote aMusicNote = new AbsoluteMusicNote();
		AbsoluteMusicNote relative = data.getRelative();
		
		for(MusicNote current: notes){
			aMusicNote = current.toAbsoluteMusicNote(data);
			data.notesBar.add(aMusicNote);
			listener.setRelative(new MusicRelativeStatementEvent(this, aMusicNote));
		}
		
		listener.setRelative(new MusicRelativeStatementEvent(this, relative));*/
	}
}
