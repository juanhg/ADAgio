package com.adagio.events.definitions;

import java.util.EventObject;

import com.adagio.instruments.Instrument;
import com.adagio.language.instruments.InstrumentIdentifier;

public class InstrumentDefinitionEvent extends EventObject {

	private static final long serialVersionUID = 1963515920780503994L;
	
	InstrumentIdentifier identifier;
	Instrument instrument;
	
	public InstrumentDefinitionEvent(Object source, InstrumentIdentifier identifier, Instrument instrument) {
		super(source);
		this.identifier = identifier;
		this.instrument = instrument;
	}

	public InstrumentIdentifier getIdentifier() {
		return identifier;
	}

	public Instrument getInstrument() {
		return instrument;
	}
}
