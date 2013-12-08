package com.adagio.events.statements;

import java.util.EventObject;

import com.adagio.language.chords.Bar;



public class MusicPlayStatementEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
	// Este evento se lanza cuando se ha generado música.
	private  Bar bar;

	public MusicPlayStatementEvent(Object source, Bar bar) {
		super(source);
		if(bar != null){
			this.bar = bar;
		}
	}

	public Bar getBar() {
		return bar;
	}	
}
