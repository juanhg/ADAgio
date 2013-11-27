package com.adagio.events;

import java.util.EventObject;

import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.instruments.Instrument;

public class MusicChannelInstrumentEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1888586157392301529L;
	private ChannelIdentifier id;
	private Instrument instrument;
	
	public MusicChannelInstrumentEvent(Object source, ChannelIdentifier id, Instrument instrument){
		super(source);
		this.id = id;
		this.instrument = instrument;
	}
	
	public ChannelIdentifier getId() {
		return id;
	}

	public void setId(ChannelIdentifier id) {
		this.id = id;
	}



	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
}
