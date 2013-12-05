package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.events.MusicEventListener;
import com.adagio.language.tempos.TempoIdentifier;

@Prefix("(?i)TEMPO")
public class TempoStatement extends Statement implements IModel {

	TempoIdentifier tempoID;
	
	@Override
	public void run(MusicEventListener listener) {
		// TODO Auto-generated method stub
		
	}

}
