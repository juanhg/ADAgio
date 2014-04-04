package com.adagio.events.channels;

import java.util.EventObject;

import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.instruments.InstrumentIdentifier;

public class ChannelInstrumentEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1888586157392301529L;
	private ChannelIdentifier id;
	private InstrumentIdentifier instrumentID;
	
	public ChannelInstrumentEvent(Object source, ChannelIdentifier id, InstrumentIdentifier instrumentID){
		super(source);
		this.id = id;
		this.instrumentID = instrumentID;
	}
	
	public ChannelIdentifier getId() {
		return id;
	}

	public InstrumentIdentifier getInstrumentID() {
		return instrumentID;
	}
}
