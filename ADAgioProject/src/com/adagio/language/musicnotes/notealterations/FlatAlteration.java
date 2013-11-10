package com.adagio.language.musicnotes.notealterations;

import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="b")
public class FlatAlteration extends Alteration {

	@Value
	String value;
	
	public FlatAlteration(){}
	
	public FlatAlteration(boolean initValue){
		if(initValue == true){
			this.value = "b";
		}
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
	public static String getPatern() {
		return "b";
	}

	@Override
	public String toString() {
		return this.value;
	}
	
	@Override
	public Alteration clone() {
		Alteration alteration = new FlatAlteration(true);
		return alteration;
	}
}
