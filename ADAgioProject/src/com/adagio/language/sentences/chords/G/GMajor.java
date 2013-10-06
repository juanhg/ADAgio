package com.adagio.language.sentences.chords.G;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="GM")
public class GMajor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<g b d>";
	}
}
