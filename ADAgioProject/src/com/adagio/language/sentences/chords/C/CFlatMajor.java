package com.adagio.language.sentences.chords.C;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="CbM")
public class CFlatMajor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<ces ees ges>";
	}
}