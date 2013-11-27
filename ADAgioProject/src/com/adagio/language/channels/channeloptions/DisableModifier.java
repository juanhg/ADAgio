package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

import com.adagio.events.MusicChannelDisableEvent;
import com.adagio.events.MusicEventListener;
import com.adagio.language.channels.ChannelIdentifier;

@Pattern(regExp="(?i)DISABLE")
public class DisableModifier extends StatusModifier implements IModel{

	@Value
	String value;
	
	public static final String PATTERN = "DISABLE";

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void Apply(ChannelIdentifier id, MusicEventListener listener) {
		listener.disableChannel(new MusicChannelDisableEvent(this,id));
	}
	
	@Override
	public String toString(){
		return value;
	}

}

