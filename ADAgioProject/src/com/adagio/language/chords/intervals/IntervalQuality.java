package com.adagio.language.chords.intervals;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp= "P|m|M|A|d|AA|dd")
public class IntervalQuality implements IModel {
	@Value
	String value;
	
	public IntervalQuality(String value){
		this.value = value;
	}
	
	public IntervalQuality(){
		
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
