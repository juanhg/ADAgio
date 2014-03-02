package com.adagio.language.rhythm;

import org.modelcc.IModel;

import com.adagio.language.musicnotes.MusicNote;

public abstract class RhythmNote implements IModel{
	public abstract String toString();
	
	/**
	 * If is a PositionalRhythmNote will obtain the position
	 * @return A integer bigger than 0 if is a PositionalRhythmNote.
	 * -1 in other case.
	 */
	public abstract int getNotePosition();
	
	public abstract MusicNote getMusicNote();
	
}
