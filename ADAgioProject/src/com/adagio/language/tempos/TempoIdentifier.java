package com.adagio.language.tempos;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Setup;
import org.modelcc.Value;

@Pattern(regExp = "[a-zA-Z][a-zA-Z[0-9]_]*")
public class TempoIdentifier implements IModel{
	
	@Value
	String value; 
	
	@Setup
	public void setup() {
		value = value.toLowerCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TempoIdentifier other = (TempoIdentifier) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	public TempoIdentifier clone(){
		TempoIdentifier cloned = new TempoIdentifier();
		cloned.value = this.value;
		return cloned;
	}
}
