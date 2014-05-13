package com.adagio.events.statements;

import java.util.EventObject;

import com.adagio.language.bars.Bar;



public class MusicPlayStatementEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
	// Este evento se lanza cuando se ha generado música.
	private  Bar [] bars;

	public MusicPlayStatementEvent(Object source, Bar [] bars) {
		super(source);
		if(bars != null){
			this.bars = bars;
		}
	}

	public Bar [] getBars() {
		return bars;
	}	
}
