package com.adagio.language.rhythm;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.types.DecimalModel;
import org.modelcc.types.NumberModel;

import com.adagio.language.musicnotes.MusicNote;

public class RhythmComponent implements IModel {
	RhythmNote note;
	@Prefix("[ |\n|\r|\t]+")
	NumberModel initTime;
	@Prefix({"[ |\n|\r|\t]+", "(?i)to", "[ |\n|\r|\t]+"})
	NumberModel finalTime;
	
	public RhythmComponent(){}
	
	public RhythmComponent(RhythmNote rNote, double initTime, double finalTime){
		this.note = rNote;
		this.initTime = new DecimalModel(initTime);
		this.finalTime = new DecimalModel(finalTime);
	}
	
	public RhythmComponent(double initTime, double finalTime){
		this.note = new PositionalRhythmNote();
		this.initTime = new DecimalModel(initTime);
		this.finalTime = new DecimalModel(finalTime);
	}
	
	public RhythmComponent(int notePosition, double initTime, double finalTime){
		this.note = new PositionalRhythmNote(notePosition);
		this.initTime = new DecimalModel(initTime);
		this.finalTime = new DecimalModel(finalTime);
	}
	
	public RhythmComponent(MusicNote note, double initTime, double finalTime){
		this.note = new AbsoluteRhythmNote(note);
		this.initTime = new DecimalModel(initTime);
		this.finalTime = new DecimalModel(finalTime);
	}
	
	/**
	 * Checks if the component is between 0 and 1
	 * @return true if the times are valid. false in other case
	 */
	@Constraint
	boolean validTimes(){
		if(initTime.doubleValue() >= 0 && initTime.doubleValue() <= 1
	 	   && finalTime.doubleValue() >= 0 && finalTime.doubleValue() <= 1		
	 	   && initTime.doubleValue() < finalTime.doubleValue()){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a instant between 0 and 1 belongs to the component
	 * @param instant A number between 0 and 1
	 * @return True if belongs to it. False in other case
	 */
	public boolean belongTo(double instant){
		if(instant >= initTime.doubleValue() && instant < finalTime.doubleValue()){
			return true;
		}
		else{
			return false;
		}
	}

	public double getInitTime() {
		return initTime.doubleValue();
	}

	public double getFinalTime() {
		return finalTime.doubleValue();
	}
	
	@Override
	public String toString(){
		return note.toString() + " " + initTime.doubleValue() + "-" + finalTime.doubleValue();
	}

	public RhythmNote getNote() {
		return note;
	}
	
	
}
