package com.adagio.language.statements.simple;

import org.modelcc.IModel;

import com.adagio.events.MusicEventListener;
import com.adagio.events.statements.UndefinedTempoStatementEvent;
import com.adagio.language.tempos.Tempo;

public class UndefinedTempoStatement extends TempoStatement implements IModel {

	Tempo tempo;
	
	@Override
	public void run(MusicEventListener listener) {
		listener.setTempo(new UndefinedTempoStatementEvent(this, tempo));
	}

}
