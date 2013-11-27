package com.adagio.events;

import java.util.EventObject;

import com.adagio.language.channels.ChannelIdentifier;

public class MusicChannelDisableEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5763182959489914368L;
	private ChannelIdentifier id;

	public MusicChannelDisableEvent(Object source, ChannelIdentifier id){
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
