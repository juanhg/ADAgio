package com.adagio.language.instruments.features;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp = "(?i)Polyphonic")
public class PolyphonicType extends PhoneticType implements IModel {

	@Value
	String value;

	public String getValue() {
		return value;
	}
}
