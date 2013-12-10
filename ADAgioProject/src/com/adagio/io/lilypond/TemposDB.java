package com.adagio.io.lilypond;

import java.util.HashMap;
import java.util.Map;

import com.adagio.language.tempos.Tempo;
import com.adagio.language.tempos.TempoIdentifier;

public class TemposDB {

	Map<TempoIdentifier,Tempo> temposMap;
	
	public TemposDB(){
		temposMap = new HashMap<TempoIdentifier,Tempo>();
	}
	
	/**
	 * If doesn't exists, add it. If exists, override it.
	 * @param identifier Tempo's identifier
	 * @param tempo Tempo to be added.
	 */
	public void addTempo(TempoIdentifier identifier, Tempo tempo){
		this.temposMap.put(identifier, tempo);
	}
	
	/**
	 * Looks for the tempo in the DB
	 * @param identifier Tempo identifier
	 * @return true if exists in the DB. False in other case.
	 */
	public boolean exists(TempoIdentifier identifier){
		return this.temposMap.containsKey(identifier);
	}
	
	/**
	 * Retrieves the tempo from tempoDB
	 * @param identifier Tempo Identifier we are looking for
	 * @return Tempo if exists. Null in other case.
	 */
	public Tempo getTempo(TempoIdentifier identifier){
		if(this.temposMap.containsKey(identifier)){
			return this.temposMap.get(identifier);
		}
		else{
			return null;
		}
	}
}
