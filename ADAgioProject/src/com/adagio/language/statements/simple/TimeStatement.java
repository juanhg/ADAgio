package com.adagio.language.statements.simple;

import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.statements.TimeStatementEvent;
import com.adagio.language.times.Time;

@Prefix("(?i)TIME")
public class TimeStatement extends SimpleStatement implements IModel {

	Time time;
	
	@Override
	public void run(MusicEventListener listener) {
		listener.setTime(new TimeStatementEvent(this,time));
	}

}