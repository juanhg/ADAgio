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
	
	/**
	 * Adds a tempo to the Data Base. If tempo exists, overwrites it
	 * @param id Tempo identifier
	 * @param tempo tempo to be added
	 */
	public void add(TempoIdentifier id, Tempo tempo){
		if(this.temposMap.containsKey(id)){
			this.temposMap.get(id).setFigure(tempo.getFigure());
			this.temposMap.get(id).setBps(tempo.getBps());
		}
		else{
			this.temposMap.put(id, tempo);
		}
	}
}
