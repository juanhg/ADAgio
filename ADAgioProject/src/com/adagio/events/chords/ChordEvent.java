package com.adagio.events.chords;

import java.util.EventObject;

import com.adagio.language.bars.chords.Chord;

public class ChordEvent extends EventObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6681631112597904754L;
	Chord chord;
	
	public ChordEvent(Object source, Chord chord){
		super(source);
		this.chord = chord;
	}

	public Chord getChord() {
		return chord;
	}
}
