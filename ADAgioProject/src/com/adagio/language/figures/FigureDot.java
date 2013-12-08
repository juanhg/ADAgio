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
		FigureDot other = (FigureDot) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
}
