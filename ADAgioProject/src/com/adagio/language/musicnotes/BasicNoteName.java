package com.adagio.language.musicnotes;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

import com.adagio.language.musicnotes.notealterations.Alteration;

@Pattern(regExp="A|B|C|D|E|F|G")
public class BasicNoteName extends MusicNoteName implements IModel {

	@Value
	String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public BasicNoteName getBaseNoteName() {
		return this;
	}

	@Override
	public Alteration getAlteration() {
		return null;
	}
	

}
