package com.adagio.language.clefs;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="(?i)(TREBLE|ALTO|TENOR|BASS)")
public class Clef implements IModel {
	
	@Value
	String value;
	
	public String toString(){
		return value.toLowerCase();
	}
}
