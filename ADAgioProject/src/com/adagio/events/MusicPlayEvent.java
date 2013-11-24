package com.adagio.events;

import com.adagio.language.chords.Chord;

import java.util.EventObject;



public class MusicPlayEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
	// Este evento se lanza cuando se ha generado música.
	Chord chord;

	public MusicPlayEvent(Object source, Chord chord) {
		super(source);
		this.chord = chord;
	}
}
