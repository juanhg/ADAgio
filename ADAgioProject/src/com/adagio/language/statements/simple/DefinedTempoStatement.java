package com.adagio.language.statements.simple;

import org.modelcc.IModel;

import com.adagio.events.MusicEventListener;
import com.adagio.events.statements.DefinedTempoStatementEvent;
import com.adagio.language.tempos.TempoIdentifier;

public class DefinedTempoStatement extends TempoStatement implements IModel {

	TempoIdentifier identifier;
	
	@Override
	public void run(MusicEventListener listener) {
		listener.setTempo(new DefinedTempoStatementEvent(this, identifier));
	}

}
