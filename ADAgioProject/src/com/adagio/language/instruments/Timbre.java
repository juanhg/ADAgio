package com.adagio.language.instruments;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Setup;
import org.modelcc.Value;

@Pattern(regExp = "(?i)(voice|piano|violin|flute|clarinet|acousticguitar)")
public class Timbre implements IModel {
	@Value
	String value;
	
	@Setup
	public void setup() {
		value = value.toLowerCase();
	}
	
	public Timbre(String value){
		value = value.toLowerCase();
		if(value.equals("voice") ||
		   value.equals("piano") ||	
		   value.equals("violin")||
		   value.equals("flute")||
		   value.equals("clarinet")||
		   value.equals("acousticguitar")){
			this.value = value;
		}
		else{
			System.err.print("Error 17 (Timbre(String value): value must be (voice|piano|violin|flute|clarinet|acousticguitar)");
			System.exit(17);
		}
	}
	
	public Timbre(){
		value = "piano";
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public String toString(){
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timbre other = (Timbre) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}
