package com.adagio.language.bars;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;

import com.adagio.language.bars.chords.Chord;

public class Bar implements IModel {
	@Multiplicity(minimum = 1)
	private Chord [] barChords;

	public Chord[] getBarChords() {
		return barChords;
	}

}
