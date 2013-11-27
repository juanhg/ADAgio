package com.adagio.events;

import java.util.EventObject;

import com.adagio.language.channels.ChannelIdentifier;

public class MusicChannelEnableEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChannelIdentifier id;
	
	public MusicChannelEnableEvent(Object source, ChannelIdentifier id){
		super(source);
		this.id = id;
	}

	public ChannelIdentifier getId() {
		return id;
	}

	public void setId(ChannelIdentifier id) {
		this.id = id;
	}
	
	
}
