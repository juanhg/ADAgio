package com.adagio.language.musicnotes.notealterations;

import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="bb")
public class DoubleFlatAlteration extends Alteration {

	@Value
	String value;

	public DoubleFlatAlteration(){}
	
	public DoubleFlatAlteration(boolean initValue){
		if(initValue == true){
			value = "bb";
		}
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String getPatern() {
		return "bb";
	}

	@Override
	public String toString() {
		return this.value;
	}
}