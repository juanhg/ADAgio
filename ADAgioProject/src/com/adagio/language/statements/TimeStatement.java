package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.statements.MusicTimeStatementEvent;
import com.adagio.language.times.Time;

@Prefix("(?i)TIME")
public class TimeStatement extends Statement implements IModel {

	Time time;
	
	@Override
	public void run(MusicEventListener listener) {
		listener.setTime(new MusicTimeStatementEvent(this,time));
	}

}