package com.adagio.language.definitions;

import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.definitions.MusicTempoDefinitionEvent;
import com.adagio.language.tempos.Tempo;
import com.adagio.language.tempos.TempoIdentifier;

@Prefix({"(?i)Define", "[ |\n|\r|\t]+", "(?i)Tempo"})
public class TempoDefinition extends Definition implements IModel {

	@Prefix({"[ |\n|\r|\t]+", "\""})
	@Suffix("\"")
	TempoIdentifier identifier;
	@Prefix("[ |\n|\r|\t]+")
	Tempo tempo;
	
	@Override
	public void run(MusicEventListener listener) {
		listener.tempoDefinition(new MusicTempoDefinitionEvent(this, identifier, tempo));
	}

}
