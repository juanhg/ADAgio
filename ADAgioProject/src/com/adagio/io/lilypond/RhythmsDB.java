package com.adagio.io.lilypond;

import java.util.HashMap;
import java.util.Map;

import com.adagio.language.rhythm.RhythmIdentifier;
import com.adagio.rhythms.Rhythm;

public class RhythmsDB {

private Map<RhythmIdentifier, Rhythm> rhythmMap;
	
	public RhythmsDB(){
		rhythmMap = new HashMap<RhythmIdentifier, Rhythm> ();
	}
	
	/**
	 * If doesn't exists, add it. If exists, override it.
	 * @param identifier Identifier of the Rhythm
	 * @param Rhythm Rhythm to be added.
	 */
	public void addRhythm(RhythmIdentifier identifier, Rhythm rhythm){
		this.rhythmMap.put(identifier, rhythm);
	}
	
	/**
	 * Looks for the rhythm in the DB
	 * @param identifier Rhythm identifier
	 * @return true if exists in the DB. False in other case.
	 */
	public boolean exists(RhythmIdentifier identifier){
		return this.rhythmMap.containsKey(identifier);
	}
	
	/**
	 * Retrieves the rhythm from rhythmDB
	 * @param identifier Rhythm Identifier we are looking for
	 * @return Rhythm if exists. Null in other case.
	 */
	public Rhythm getRhythm(RhythmIdentifier identifier){
		if(this.rhythmMap.containsKey(identifier)){
			return this.rhythmMap.get(identifier);
		}
		else{
			return null;
		}
	}
}
