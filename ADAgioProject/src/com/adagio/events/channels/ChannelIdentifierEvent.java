package com.adagio.events.channels;

import java.util.EventObject;

import com.adagio.language.channels.ChannelIdentifier;

public class ChannelIdentifierEvent extends EventObject{

	private static final long serialVersionUID = 1L;
	private ChannelIdentifier id;

	public ChannelIdentifierEvent(Object source, ChannelIdentifier id) {
		super(source);
		this.id = id;
	}

	public ChannelIdentifier getId() {
		return id;
	}
}
