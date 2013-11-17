package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;

import com.adagio.language.RunData;
import com.adagio.language.channels.ChannelIdentifier;

public abstract class ChannelOption implements IModel {
	public abstract String getValue();
	public abstract void Apply(ChannelIdentifier id, RunData run);
}
