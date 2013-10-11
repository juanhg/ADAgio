package com.adagio.language.sentences.musicnotes.alterations;

import org.modelcc.Pattern;

@Pattern(regExp="b")
public class Flat extends Alteration {

	@Override
	public String toString() {
		return "es";
	}

}
