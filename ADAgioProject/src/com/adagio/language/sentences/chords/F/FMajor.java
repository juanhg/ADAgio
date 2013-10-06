package com.adagio.language.sentences.chords.F;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="FM")
public class FMajor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<f a c>";
	}
}
