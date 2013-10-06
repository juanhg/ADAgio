package com.adagio.language.sentences.chords.G;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="Gm")
public class GMinor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<g bes d>";
	}
}