package com.adagio.io.lilypond;

import java.util.HashMap;
import java.util.Map;

public class MusicDataBase<ID,Element> {
	protected Map<ID, Element> map;
	
	public MusicDataBase(){
		map = new HashMap<ID, Element>();
	}
	
	/**
	 * If doesn't exists, add it. If exists, override it.
	 * @param identifier Identifier of the element
	 * @param item Element to be added.
	 */
	public void addElement(ID identifier, Element item){
		this.map.put(identifier, item);
	}
	
	/**
	 * Looks for the element in the DB
	 * @param identifier Identifier of the element
	 * @return true if exists in the DB. False in other case.
	 */
	public boolean exists(ID identifier){
		return this.map.containsKey(identifier);
	}
	
	/**
	 * Retrieves the rhythm from rhythmDB
	 * @param identifier Rhythm Identifier we are looking for
	 * @return Rhythm if exists. Null in other case.
	 */
	public Element getElement(ID identifier){
		if(this.map.containsKey(identifier)){
			return this.map.get(identifier);
		}
		else{
			return null;
		}
	}
}
