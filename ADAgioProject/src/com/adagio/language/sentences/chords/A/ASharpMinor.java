package com.adagio.language.sentences.chords.A;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="A#m")
public class ASharpMinor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<ais cis eis>";
	}
}
