package com.adagio.events;

import com.adagio.language.chords.Chord;

import java.util.EventObject;
import java.util.Vector;



public class MusicPlayEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
	// Este evento se lanza cuando se ha generado música.
	private  Vector<Chord> chords;

	public MusicPlayEvent(Object source, Vector<Chord> chords) {
		super(source);
		this.chords = chords;
	}

	public Vector<Chord> getChords() {
		return chords;
	}

	public void setChords(Vector<Chord> chords) {
		this.chords = chords;
	}


	
	
}
