package com.adagio.events.tempos;
import java.util.EventObject;

import com.adagio.language.tempos.Tempo;
import com.adagio.language.tempos.TempoIdentifier;

public class MusicTempoDefinitionEvent extends EventObject {
	
	private static final long serialVersionUID = -3310742495934495209L;
	
	public TempoIdentifier id;
	public Tempo tempo;
	
	public MusicTempoDefinitionEvent(Object source, TempoIdentifier id, Tempo tempo) {
		super(source);
		this.id = id.clone();
		this.tempo = tempo.clone();
	}

	public TempoIdentifier getId() {
		return id;
	}

	public void setId(TempoIdentifier id) {
		this.id = id;
	}

	public Tempo getTempo() {
		return tempo;
	}

	public void setTempo(Tempo tempo) {
		this.tempo = tempo;
	}
}
