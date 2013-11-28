package com.adagio.events.chords;

import java.util.EventObject;
import java.util.List;

import com.adagio.language.chords.ChordIdentifier;
import com.adagio.language.chords.intervals.Interval;

public class MusicChordAddEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2227032302110950669L;
	
	ChordIdentifier id;
	List<Interval> intervals;
	
	public MusicChordAddEvent(Object source, ChordIdentifier id, List<Interval> intervals) {
		super(source);
		this.id = id;
		this.intervals = intervals;
	}

	public ChordIdentifier getId() {
		return id;
	}

	public void setId(ChordIdentifier id) {
		this.id = id;
	}

	public List<Interval> getIntervals() {
		return intervals;
	}

	public void setIntervals(List<Interval> intervals) {
		this.intervals = intervals;
	}
	
	
	
	
	
}
