package com.adagio.language.sentences.chords.F;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="F#M")
public class FSharpMajor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<fis ais cis>";
	}
}