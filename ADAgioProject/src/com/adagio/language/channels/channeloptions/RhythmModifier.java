package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.channels.MusicChannelRhythmEvent;
import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.rhythm.RhythmIdentifier;

public class RhythmModifier extends StatusModifier implements IModel {
	@Prefix({"(?i)RHYTHM", "[ |\n|\r|\t]*", "=", "[ |\n|\r|\t]*"})
	RhythmIdentifier rhythmID;
	
	public static final String PATTERN = "RHYTHM";
	
	@Override
	public void Apply(ChannelIdentifier channelID, MusicEventListener listener) {
		listener.setChannelRhythm(new MusicChannelRhythmEvent(this,channelID,rhythmID));
	}
}
