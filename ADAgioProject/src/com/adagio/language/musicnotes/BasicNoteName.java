package com.adagio.language.musicnotes;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

import com.adagio.language.RunData;

@Pattern(regExp="A|B|C|D|E|F|G")
public class BasicNoteName extends MusicNoteName implements IModel {

	@Value
	String value;
	
	@Override
	public String toString(RunData data) {
		String result = value.toLowerCase();
		return result;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}
