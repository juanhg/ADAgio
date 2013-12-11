package com.adagio.events.chords;

import java.util.EventObject;

import com.adagio.language.bars.chords.Chord;

public class MusicChordEvent extends EventObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6681631112597904754L;
	Chord chord;
	
	public MusicChordEvent(Object source, Chord chord){
		super(source);
		this.chord = chord;
	}

	public Chord getChord() {
		return chord;
	}
}
