package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.Priority;

import com.adagio.events.MusicEventListener;
@Priority(value=1)
@Prefix("(?i)TEMPO")
public abstract class TempoStatement extends Statement implements IModel {
	
	@Override
	public abstract void run(MusicEventListener listener);

}
