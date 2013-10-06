package com.adagio.language.sentences.chords.B;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="Bbm")
public class BFlatMinor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<bes des f>";
	}
}
