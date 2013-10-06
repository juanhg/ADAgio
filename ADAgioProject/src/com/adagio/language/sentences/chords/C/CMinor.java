package com.adagio.language.sentences.chords.C;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="Cm")
public class CMinor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<c ees g>";
	}
}