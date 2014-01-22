package com.adagio.events.definitions;

import java.util.EventObject;

import com.adagio.language.instruments.InstrumentIdentifier;
import com.adagio.language.instruments.features.PhoneticType;
import com.adagio.language.instruments.features.Register;
import com.adagio.language.instruments.features.Timbre;

public class InstrumentDefinitionEvent extends EventObject {

	private static final long serialVersionUID = 1963515920780503994L;
	
	InstrumentIdentifier identifier;
	
	PhoneticType phType;
	Timbre timbre;
	Register [] registers;
	
	
	public InstrumentDefinitionEvent(Object source, InstrumentIdentifier identifier, PhoneticType phType, Timbre timbre, Register [] registers) {
		super(source);
		this.identifier = identifier;
		this.phType = phType;
		this.timbre = timbre;
		this.registers = registers;
	}

	public InstrumentIdentifier getIdentifier() {
		return identifier;
	}

	public PhoneticType getPhType() {
		return phType;
	}

	public Timbre getTimbre() {
		return timbre;
	}

	public Register[] getRegisters() {
		return registers;
	}

	

	
}
