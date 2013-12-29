package com.adagio.language.instruments;

import com.adagio.language.instruments.features.PhoneticType;
import com.adagio.language.instruments.features.Register;
import com.adagio.language.instruments.features.Timbre;


public class Instrument{

	public final static String DEFAULT_INSTRUMENT = "acoustic grand";
	
	PhoneticType phType;
	Timbre timbre;
	Register registers[];
}
