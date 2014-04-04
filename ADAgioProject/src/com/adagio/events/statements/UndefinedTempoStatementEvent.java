package com.adagio.events.statements;

import java.util.EventObject;

import com.adagio.language.tempos.Tempo;

public class UndefinedTempoStatementEvent extends EventObject {

	private static final long serialVersionUID = -7696608063302655763L;
	Tempo tempo;
	
	public UndefinedTempoStatementEvent(Object source, Tempo tempo) {
		super(source);
		this.tempo = tempo;
	}

	public Tempo getTempo() {
		return tempo;
	}
}
