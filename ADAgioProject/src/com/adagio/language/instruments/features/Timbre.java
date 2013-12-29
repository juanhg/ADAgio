package com.adagio.language.instruments.features;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Setup;
import org.modelcc.Value;

@Pattern(regExp = "(?i)(voice|piano|violin|flute|clarinet|acousticguitar)")
public class Timbre implements IModel {
	@Value
	String value;
	
	@Setup
	public void setup() {
		value = value.toLowerCase();
	}
	
	public Timbre(){
		value = "piano";
	}

	public String getValue() {
		return value;
	}
}
