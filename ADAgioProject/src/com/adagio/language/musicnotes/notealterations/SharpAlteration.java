package com.adagio.language.musicnotes.notealterations;

import org.modelcc.Pattern;

@Pattern(regExp="#")
public class SharpAlteration extends Alteration {

	@Override
	public String toString() {
		return "is";
	}

}