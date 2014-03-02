package com.adagio.language.rhythm;

import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.types.UnsignedIntegerModel;

import com.adagio.language.musicnotes.MusicNote;

public class PositionalRhythmNote extends RhythmNote implements IModel{
     

	@Prefix("(?i)Note")
	UnsignedIntegerModel notePosition;	
	
	public PositionalRhythmNote() {}
	
	public PositionalRhythmNote(int notePosition) {
		this.notePosition = new UnsignedIntegerModel(notePosition);
	}

	@Override
	public String toString() {
		return "NOTE"+notePosition.toString();
	}

	@Override
	public int getNotePosition() {
		return notePosition.intValue();
	}

	@Override
	public MusicNote getMusicNote() {
		return null;
	}
	
	
}

