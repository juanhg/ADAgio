package com.adagio.language.rhythm;

import org.modelcc.IModel;

import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class AbsoluteRhythmNote extends RhythmNote implements IModel{
    AbsoluteMusicNote note;

	@Override
	public String toString() {
		return note.toString();
	}
}
