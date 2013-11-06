package com.adagio.language.statements;
import com.adagio.language.*;
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
	public void run(RunData data) {
		
		AbsoluteMusicNote aNote;
		Chord auxChord;
		
		for(Chord current: chords){
			if(data.isDefined(current)){
				aNote =  current.getNote().toAbsoluteMusicNote(data);
				data.setRelative(aNote);
				
				auxChord = new Chord(aNote, current.getIdentifier(), current.getBassNote());
				data.getChordsBar().add(auxChord);
			}
			else{
				System.err.println("Error 1: The chord identifier \"" + current.getIdentifier().getValue() + "\" is not defined");
				System.exit(1);
			}
		}
	}

}
