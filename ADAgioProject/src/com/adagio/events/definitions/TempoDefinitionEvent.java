package com.adagio.events.definitions;
import java.util.EventObject;

import com.adagio.language.tempos.Tempo;
import com.adagio.language.tempos.TempoIdentifier;

public class TempoDefinitionEvent extends EventObject {
	
	private static final long serialVersionUID = -3310742495934495209L;
	
	public TempoIdentifier id;
	public Tempo tempo;
	
	public TempoDefinitionEvent(Object source, TempoIdentifier id, Tempo tempo) {
		super(source);
		this.id = id;
		this.tempo = tempo;
	}

	public TempoIdentifier getId() {
		return id;
	}

	public Tempo getTempo() {
		return tempo;
	}
}
