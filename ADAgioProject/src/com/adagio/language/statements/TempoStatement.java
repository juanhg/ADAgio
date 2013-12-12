package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.Priority;

import com.adagio.events.MusicEventListener;

@Prefix("(?i)TEMPO")
public abstract class TempoStatement extends Statement implements IModel {
	
	@Override
	public abstract void run(MusicEventListener listener);

}
