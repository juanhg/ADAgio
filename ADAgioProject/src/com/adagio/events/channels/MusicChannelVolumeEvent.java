package com.adagio.events.channels;

import java.util.EventObject;

import com.adagio.language.channels.ChannelIdentifier;

public class MusicChannelVolumeEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChannelIdentifier id;
	private int volume;

	public MusicChannelVolumeEvent(Object source,ChannelIdentifier id, int volume) {
		super(source);
		this.id = id;
		this.volume = volume;
	}

	public ChannelIdentifier getId() {
		return id;
	}

	public int getVolume() {
		return volume;
	}
}
