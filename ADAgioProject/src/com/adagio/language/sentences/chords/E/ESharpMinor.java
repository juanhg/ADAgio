package com.adagio.language.sentences.chords.E;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="E#m")
public class ESharpMinor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<eis gis bis>";
	}
}