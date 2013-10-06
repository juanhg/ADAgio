package com.adagio.language.sentences.chords.D;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="D#m")
public class DSharpMinor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<dis fis ais>";
	}
}