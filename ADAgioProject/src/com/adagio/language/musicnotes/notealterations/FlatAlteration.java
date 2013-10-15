package com.adagio.language.musicnotes.notealterations;

import org.modelcc.Pattern;

@Pattern(regExp="b")
public class FlatAlteration extends Alteration {

	@Override
	public String toString() {
		return "es";
	}

}
