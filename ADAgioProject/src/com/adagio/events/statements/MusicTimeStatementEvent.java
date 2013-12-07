package com.adagio.events.statements;

import java.util.EventObject;

import com.adagio.language.times.Time;

public class MusicTimeStatementEvent extends EventObject {

	private static final long serialVersionUID = -1029286347060898226L;
	Time time;
	
	public MusicTimeStatementEvent(Object arg0, Time time) {
		super(arg0);
		this.time = time;
	}

	public Time getTime() {
		return time;
	}
}
