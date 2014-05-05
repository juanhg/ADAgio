package com.adagio.events.definitions;

import java.util.EventObject;
import java.util.List;

import com.adagio.language.bars.chords.ChordIdentifier;
import com.adagio.language.bars.chords.intervals.Interval;

public class chorDefinitionEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2227032302110950669L;
	
	ChordIdentifier id;
	List<Interval> intervals;
	
	public chorDefinitionEvent(Object source, ChordIdentifier id, List<Interval> intervals) {
		super(source);
		this.id = id;
		this.intervals = intervals;
	}

	public ChordIdentifier getId() {
		return id;
	}

	public List<Interval> getIntervals() {
		return intervals;
	}	
}
