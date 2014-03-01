package com.adagio.language.musicnotes.octavealterations;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp="\\,+")
public class DownOctaveAlteration extends OctaveAlteration implements IModel {

	@Value
	String value;
		
	public DownOctaveAlteration(){}
	
	public DownOctaveAlteration(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int toInt() {
		return -value.length();
	}
	
	@Override
	public String toString() {
		return this.value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DownOctaveAlteration other = (DownOctaveAlteration) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public DownOctaveAlteration clone() {
		return new DownOctaveAlteration(value);
	}
	
	
}
