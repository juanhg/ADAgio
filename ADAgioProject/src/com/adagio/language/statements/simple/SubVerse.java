package com.adagio.language.statements.simple;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp = "[a-zA-Z0-9_]*")
public class SubVerse implements IModel {
	@Value
	String value;
	
	@Override
	public String toString(){
		return value;
	}
}
