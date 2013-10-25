package com.adagio.language.definitions;

import java.util.ArrayList;
import java.util.List;

import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.Suffix;

import com.adagio.language.RunData;
import com.adagio.language.chords.ChordIdentifier;
import com.adagio.language.chords.intervals.Interval;


@Prefix("(?i)DEFINE CHORD")
public class ChordDefinition extends Definition implements IModel {
	
	@Prefix("\"")
	@Suffix("\"")
	ChordIdentifier identifier;
	
	@Prefix("(?i)NOTES")
	Interval[] intervals;
	
	@Override
	public void run(RunData data) {
		List<Interval> auxInterval = new ArrayList<Interval>();
		for(int i = 0; i < intervals.length; i++){
			auxInterval.add(intervals[i]);
		}
		data.getChordsDB().put(identifier, auxInterval);
	}

}
