package com.adagio.language.instruments;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Setup;
import org.modelcc.Value;

/**
 * If you add new instruments, upgrade "instrumentsTranslation" in the writer
 */
@Pattern(regExp = "(?i)(voice|piano|brightAcoustic|electricGrand|electricPiano|"
		+ "harpsichord|celesta|glockenspiel|musicBox|vibraphone|marimba|xylophone|"
		+ "tubularBells|dulcimer|drawbarOrgan|percussiveOrgan|rockOrgan|reedOrgan|"
		+ "accordion|harmonica|concertina|orchestralHarp|ocarina|"
		+ "harmonicsGuitar|acousticGuitar2|guitar2|acousticguitar|guitar|electricGuitar|electricGuitar2|electricGuitar3|"
		+ "acousticBass|bass|electricBass2|electricBass|contrasbass|"
		+ "violin|viola|cello|flute|panFlute|clarinet|trumpet|trombone|tuba|oboe|piccolo|"
		+ "altoSax|sopranoSax|tenorSax|baritoneSax)")

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
