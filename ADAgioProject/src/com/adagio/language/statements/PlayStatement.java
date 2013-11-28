package com.adagio.language.statements;
import java.util.Vector;

import com.adagio.events.MusicEventListener;
import com.adagio.events.chords.MusicChordEvent;
import com.adagio.events.notes.MusicNoteToAbsoluteEvent;
import com.adagio.events.statements.MusicPlayStatementEvent;
import com.adagio.events.statements.MusicRelativeStatementEvent;
import com.adagio.language.chords.Chord;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

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
		
		AbsoluteMusicNote aNote = null;
		AbsoluteMusicNote bassNote = null;
		Chord auxChord;
		Vector<Chord> chordsVector = new Vector<Chord>();
		
		for(Chord current: chords){
			if(listener.chordIsDefined(new MusicChordEvent(this,current))){
				aNote = listener.toAbsolute(new MusicNoteToAbsoluteEvent(this,current.getNote())); 
				listener.setRelative(new MusicRelativeStatementEvent(this, aNote));
				
				// We add the bassNote as an absoluteMusicNote
				if(current.getBassNote() != null){
					bassNote = listener.toAbsolute(new MusicNoteToAbsoluteEvent(this,current.getBassNote()));
				}
				
				auxChord = new Chord(aNote, current.getIdentifier(), bassNote);		
				bassNote = null;
				chordsVector.add(auxChord);
			}
			else{
				System.err.println("Error 1: The chord identifier \"" + current.getIdentifier().getValue() + "\" is not defined");
				System.exit(1);
			}
		}
		
		listener.musicPlay(new MusicPlayStatementEvent(this, chordsVector));
	}

}
