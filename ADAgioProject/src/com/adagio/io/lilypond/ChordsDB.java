package com.adagio.io.lilypond;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adagio.language.bars.chords.ChordIdentifier;
import com.adagio.language.bars.chords.intervals.Interval;

public class ChordsDB {

	private Map<ChordIdentifier,List<Interval>> chordsMap;
	
	public ChordsDB(){
		chordsMap = new HashMap<ChordIdentifier,List<Interval>>();
	}
	
	public void addChord(ChordIdentifier identifier, List<Interval> intervals){
		chordsMap.put(identifier, intervals);
	}
	
	public boolean exists(ChordIdentifier identifier){
		return chordsMap.containsKey(identifier);
	}
	
	public List<Interval> getIntervals(ChordIdentifier identifier){
		if(exists(identifier)){
			return chordsMap.get(identifier);
		}
		else{
			return null;
		}
	}
}
