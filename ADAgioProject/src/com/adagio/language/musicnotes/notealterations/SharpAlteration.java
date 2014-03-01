package com.adagio.language.musicnotes.notealterations;

import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="#")
public class SharpAlteration extends Alteration {

	@Value
	String value;
	
	public SharpAlteration(){
		value = "#";
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getPatern() {
		return "#";
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	@Override
	public Alteration clone() {
		Alteration alteration = new SharpAlteration();
		return alteration;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SharpAlteration other = (SharpAlteration) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}