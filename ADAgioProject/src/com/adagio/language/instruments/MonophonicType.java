package com.adagio.language.instruments;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="(?i)monophonic")
public class MonophonicType extends PhoneticType implements IModel {
	@Value
	String value;

	public String getValue() {
		return value;
	}
}
