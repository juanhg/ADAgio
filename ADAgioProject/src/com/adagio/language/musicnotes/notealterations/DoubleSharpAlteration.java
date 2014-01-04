package com.adagio.language.musicnotes.notealterations;

import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="x")
public class DoubleSharpAlteration extends Alteration {

	@Value
	String value;

	public DoubleSharpAlteration(){
		value = "x";
	}
		
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getPatern() {
		return "x";
	}

	@Override
	public String toString() {
		return this.value;
	}
	
	@Override
	public Alteration clone() {
		Alteration alteration = new DoubleSharpAlteration();
		return alteration;
	}

}
