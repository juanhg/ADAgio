package com.adagio.language.musicnotes;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Priority;
import org.modelcc.Value;

import com.adagio.language.musicnotes.notealterations.Alteration;

@Priority(value = 2)
@Pattern(regExp="A|B|C|D|E|F|G")
public class BasicNoteName extends MusicNoteName implements IModel {

	@Value
	String value;
	
	public BasicNoteName(){
		
	}
	
	public BasicNoteName(String value){
		this.value = value;
	}
	
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

	@Override
	public MusicNoteName clone() {
		BasicNoteName bName = new BasicNoteName();
		bName.setValue(this.value);
		return bName;
	}
}
