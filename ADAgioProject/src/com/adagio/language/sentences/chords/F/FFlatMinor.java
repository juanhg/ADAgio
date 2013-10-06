package com.adagio.language.sentences.chords.F;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="Fbm")
public class FFlatMinor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<fes aeses ces>";
	}
}
