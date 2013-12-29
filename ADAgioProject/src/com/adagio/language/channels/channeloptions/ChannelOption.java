package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;

import com.adagio.events.MusicEventListener;
import com.adagio.language.channels.ChannelIdentifier;

public abstract class ChannelOption implements IModel {
	public abstract void Apply(ChannelIdentifier id, MusicEventListener listener);
}
