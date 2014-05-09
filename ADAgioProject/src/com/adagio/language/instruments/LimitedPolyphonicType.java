package com.adagio.language.instruments;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Prefix;
import org.modelcc.Value;

//TODO Unificar en prefix
@Prefix("(?i)limited")
@Pattern(regExp="(?i)polyphonic")
public class LimitedPolyphonicType extends PhoneticType implements IModel {
	@Value
	String value;

	public String getValue() {
		return value;
	}
}

