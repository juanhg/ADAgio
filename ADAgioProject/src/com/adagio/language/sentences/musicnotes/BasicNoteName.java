package com.adagio.language.sentences.musicnotes;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="A|B|C|D|E|F|G")
public class BasicNoteName extends MusicNoteName implements IModel {

	@Value
	String value;
	
	@Override
	public String toString() {
		return value.toLowerCase();
	}

}
