package com.adagio.io.lilypond;

import java.util.HashMap;
import java.util.Map;

import com.adagio.instruments.Instrument;
import com.adagio.language.instruments.InstrumentIdentifier;

public class InstrumentsDB {
	private Map<InstrumentIdentifier,Instrument> instrumentMap;
	
	public InstrumentsDB(){
		instrumentMap = new HashMap<InstrumentIdentifier,Instrument>();
	}
	
	/**
	 * If doesn't exists, add it. If exists, override it.
	 * @param identifier Instrument's identifier
	 * @param instrument Instrument to be added.
	 */
	public void addInstrument(InstrumentIdentifier identifier, Instrument instrument){
		this.instrumentMap.put(identifier, instrument);
	}
	
	/**
	 * Looks for the instrument in the DB
	 * @param identifier Instrument identifier
	 * @return true if exists in the DB. False in other case.
	 */
	public boolean exists(InstrumentIdentifier identifier){
		return this.instrumentMap.containsKey(identifier);
	}
	
	/**
	 * Retrieves the instrument from instrumentDB
	 * @param identifier Instrument Identifier we are looking for
	 * @return Instrument if exists. Null in other case.
	 */
	public Instrument getInstrument(InstrumentIdentifier identifier){
		if(this.instrumentMap.containsKey(identifier)){
			return this.instrumentMap.get(identifier);
		}
		else{
			return null;
		}
	}
}
