package com.adagio.language.instruments;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp = "(?i)Polyphonic")
public class PolyphonicType extends PhoneticType implements IModel {

	@Value
	String value;

	public PolyphonicType(){
		this.value = "polyphonic";
	}
	
	public String getValue() {
		return value;
	}
}
