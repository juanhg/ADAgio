package com.adagio.language.musicnotes.notealterations;

import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="b")
public class FlatAlteration extends Alteration {

	@Value
	String value;
	
	@Override
	public String getValue() {
		return value;
	}
}
