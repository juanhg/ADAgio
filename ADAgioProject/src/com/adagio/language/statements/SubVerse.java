package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp = "[a-zAz0-9_][a-z ]*")
public class SubVerse implements IModel {
	@Value
	String value;
	
	@Override
	public String toString(){
		return value;
	}
}
