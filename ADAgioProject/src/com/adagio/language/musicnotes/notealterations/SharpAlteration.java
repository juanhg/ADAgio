package com.adagio.language.musicnotes.notealterations;

import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="#")
public class SharpAlteration extends Alteration {

	@Value
	String value;
	
	@Override
	public String toString() {
		return "is";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}