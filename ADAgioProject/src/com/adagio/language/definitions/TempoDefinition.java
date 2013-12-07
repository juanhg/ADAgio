package com.adagio.language.definitions;

import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.definitions.MusicTempoDefinitionEvent;
import com.adagio.language.tempos.Tempo;
import com.adagio.language.tempos.TempoIdentifier;

@Prefix("(?i)DEFINE TEMPO")
public class TempoDefinition extends Definition implements IModel {

	@Prefix("\"")
	@Suffix("\"")
	TempoIdentifier identifier;
	Tempo tempo;
	
	@Override
	public void run(MusicEventListener listener) {
		listener.tempoDefinition(new MusicTempoDefinitionEvent(this, identifier, tempo));
	}

}
