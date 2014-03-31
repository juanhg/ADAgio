package com.adagio.language.bars;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Suffix;

import com.adagio.language.bars.chords.Chord;

public class Bar implements IModel {
	
	@Prefix("( |\\n|\\r|\\t)*")
	@Suffix("( |\\n|\\r|\\t)*")
	@Separator("( |\\n|\\r|\\t)+")
	@Multiplicity(minimum = 1)
	Chord [] barChords;

	public Chord[] getBarChords() {
		return barChords;
	}

}
