package com.adagio.language.rhythm;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.types.NumberModel;

public class RhythmComponent implements IModel {
	RhythmNote note;
	NumberModel initTime;
	@Prefix("(?i)to")
	NumberModel finalTime;
	
	/**
	 * Check if the component is between 0 and 1
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
}
