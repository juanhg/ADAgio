package com.adagio.language.sentences.chords.A;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="AbM")
public class AFlatMajor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<aes c ees>";
	}
}
