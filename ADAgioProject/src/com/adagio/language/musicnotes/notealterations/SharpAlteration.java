package com.adagio.language.musicnotes.notealterations;

import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="#")
public class SharpAlteration extends Alteration {

	@Value
	String value;
	
	public SharpAlteration(){
	}
	
	public SharpAlteration(boolean initValue){
		if(initValue == true){
			this.value = "#";
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getPatern() {
		return "#";
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	@Override
	public Alteration clone() {
		Alteration alteration = new SharpAlteration(true);
		return alteration;
	}
	
	

}