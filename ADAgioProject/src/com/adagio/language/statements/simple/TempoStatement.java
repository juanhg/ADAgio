package com.adagio.language.statements.simple;

import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.events.MusicEventListener;

@Prefix("(?i)TEMPO")
public abstract class TempoStatement extends SimpleStatement implements IModel {
	
	@Override
	public abstract void run(MusicEventListener listener);

}
