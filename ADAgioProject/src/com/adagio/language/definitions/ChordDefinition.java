package com.adagio.language.definitions;

import java.util.ArrayList;
import java.util.List;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.Suffix;

import com.adagio.language.RunData;
import com.adagio.language.chords.ChordIdentifier;
import com.adagio.language.chords.intervals.Interval;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.DoubleSharpAlteration;
import com.adagio.language.musicnotes.notealterations.FlatAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;


@Prefix("(?i)DEFINE CHORD")
public class ChordDefinition extends Definition implements IModel {
	
	@Prefix("\"")
	@Suffix("\"")
	ChordIdentifier identifier;
	@Constraint
	boolean allowedIdentifier(){
		boolean allowed = true;
		
		if(identifier.getValue().equals(FlatAlteration.getPatern()) || identifier.getValue().substring(0, 1).equals(FlatAlteration.getPatern())){
			allowed = false;
		}
		else if (identifier.getValue().equals(DoubleFlatAlteration.getPatern())){
			allowed = false;
		}
		else if (identifier.getValue().equals(SharpAlteration.getPatern())){
			allowed = false;
		}
		else if (identifier.getValue().equals(DoubleSharpAlteration.getPatern())){
			allowed = false;
		}
		if(!allowed){
			System.err.println("Error 2: \"" + identifier.getValue() +"\" Can't be used in chord definition");
			System.exit(2);
		}
		return allowed;
	}
	
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
