package com.adagio.language.musicnotes.notealterations;

import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="bb")
public class DoubleFlatAlteration extends Alteration {

	@Value
	String value;
	
	@Override
	public String toString() {
		return "eses";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}