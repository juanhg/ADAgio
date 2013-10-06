package com.adagio.language.sentences.chords.D;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.language.sentences.chords.Chord;

@Pattern(regExp="DbMajor")
public class DFlatMajor extends Chord implements IModel {

	@Override
	public String getValue() {
		return "<des f aes>";
	}
}