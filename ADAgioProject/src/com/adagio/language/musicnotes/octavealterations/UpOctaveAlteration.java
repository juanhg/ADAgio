package com.adagio.language.musicnotes.octavealterations;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="\'+")
public class UpOctaveAlteration extends OctaveAlteration implements IModel {

	@Value
	String value;
		
	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int toInt() {
		return value.length();
	}
	
	

}
