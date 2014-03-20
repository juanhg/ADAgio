package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp = "[0-9a-zA-z-_ ]*")
public class Verse implements IModel {
	@Value
	String value;
	
	@Override
	public String toString(){
		return value;
	}
	
}
