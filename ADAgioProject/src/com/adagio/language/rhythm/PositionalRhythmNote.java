package com.adagio.language.rhythm;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;


@Pattern(regExp = "(?i)Note")
public class PositionalRhythmNote extends RhythmNote implements IModel{
     
	@Value 
	String value;
	//UnsignedIntegerModel position;
	
	public PositionalRhythmNote() {
		
	}

	@Override
	public String toString() {
		return "NOTE";
	}
}

