package com.adagio.language.bars;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Separator;

import com.adagio.language.bars.chords.Chord;

public class Bar implements IModel {
	
	@Separator("( |\\n|\\r|\\t)+")
	@Multiplicity(minimum = 1)
	Chord [] barChords;

	public Chord[] getBarChords() {
		return barChords;
	}

}
