package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;
import org.modelcc.Pattern;

import com.adagio.events.MusicEventListener;
import com.adagio.events.channels.ChannelIdentifierEvent;
import com.adagio.language.channels.ChannelIdentifier;

@Pattern(regExp="(?i)Melody")
public class MelodyModifier extends StatusModifier implements IModel {

	@Override
	public void Apply(ChannelIdentifier id, MusicEventListener listener) {
		listener.melodyChannel(new ChannelIdentifierEvent(this,id));
	}

}
