package com.adagio.language.chords;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;

public class Bar implements IModel {
	@Multiplicity(minimum = 1)
	Chord [] chords;

	public Chord[] getChords() {
		return chords;
	}

}
