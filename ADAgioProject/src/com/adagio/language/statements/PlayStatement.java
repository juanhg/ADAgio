package com.adagio.language.statements;
import java.util.Vector;

import com.adagio.events.MusicEventListener;
import com.adagio.events.statements.MusicPlayStatementEvent;
import com.adagio.language.chords.Chord;


import org.modelcc.*;

@Prefix("(?i)play")
public class PlayStatement extends Statement implements IModel {

	Chord [] chords;
	
	@Override
	/**
	 * Takes chords, transforms the fundamental note in AbsoluteNote, and stores them
	 * in the attribute "chords". Each absolute-fundamental-note updates the Relative note.
	 */
	public void run(MusicEventListener listener) {
		
		Vector<Chord> auxVector = new Vector<Chord>();
		for(Chord current: chords){
			auxVector.add(current);
		}

		listener.musicPlay(new MusicPlayStatementEvent(this, auxVector));
	}

}
