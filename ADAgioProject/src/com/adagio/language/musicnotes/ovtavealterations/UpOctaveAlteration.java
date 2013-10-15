package com.adagio.language.musicnotes.ovtavealterations;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="\'+")
public class UpOctaveAlteration extends OctaveAlteration implements IModel {

	@Value
	String value;
		
	@Override
	public String toString() {
		return value;
	}

}
