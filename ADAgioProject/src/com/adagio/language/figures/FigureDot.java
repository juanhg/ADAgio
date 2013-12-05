package com.adagio.language.figures;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp = "\\.")
public class FigureDot implements IModel {
	
	@Value
	String value;
	
	public FigureDot(){
		value = ".";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public FigureDot clone(){
		FigureDot cloned = new FigureDot();
		return cloned;
	}
	
}
