package com.adagio.language.bars.chords;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Priority;
import org.modelcc.Setup;
import org.modelcc.Value;

@Priority(value=2)
@Pattern(regExp="([\\-\\+]*[ac-zH-QT-Z][a-zA-Z0-9#]*)?")
public class ChordIdentifier implements IModel {

	@Value
	private String value;

	@Setup
	public void setup() {
		if(value != null){
			value = value.toLowerCase();
			if(value.equals("null")){
				value = "";
			}
		}
	}

	public ChordIdentifier(){}

	public ChordIdentifier(String value){
		//TODO No permitir asignación de valores erróneos
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
		ChordIdentifier other = (ChordIdentifier) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
