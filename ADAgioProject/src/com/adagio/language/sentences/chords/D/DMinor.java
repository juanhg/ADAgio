package com.adagio.language.sentences.chords.D;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="Dm")
public class DMinor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<d f a>";
	}
}