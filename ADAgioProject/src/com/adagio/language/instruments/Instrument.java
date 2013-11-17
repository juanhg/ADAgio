package com.adagio.language.instruments;

import org.modelcc.IModel;
import org.modelcc.Value;
import org.modelcc.Pattern;

//TODO Make a better design

@Pattern(regExp = "(?i)(flute|acoustic grand|violin|acoustic bass|clarinet)")
public class Instrument implements IModel {

	@Value
	String value;
	
	public final static String DEFAULT_INSTRUMENT = "acoustic grand";
	
	public Instrument(){
		value = DEFAULT_INSTRUMENT;
	}
	
	public String getValue() {
		return value;
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
		Instrument other = (Instrument) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	

}
