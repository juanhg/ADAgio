package com.adagio.events.channels;

import java.util.EventObject;

import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.rhythm.RhythmIdentifier;

public class MusicChannelRhythmEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4433601089529804344L;
	private ChannelIdentifier channelID;
	private RhythmIdentifier rhythmID;
	
	public MusicChannelRhythmEvent(Object source, ChannelIdentifier channelID, RhythmIdentifier rhythmID){
		super(source);
		this.channelID = channelID;
		this.rhythmID = rhythmID;
	}

	public ChannelIdentifier getChannelID() {
		return channelID;
	}

	public RhythmIdentifier getRhythmID() {
		return rhythmID;
	}
}
