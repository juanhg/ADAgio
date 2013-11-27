package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

import com.adagio.events.MusicChannelEnableEvent;
import com.adagio.events.MusicEventListener;
import com.adagio.language.channels.ChannelIdentifier;

@Pattern(regExp="(?i)enable")
public class EnableModifier extends StatusModifier implements IModel{

	@Value
	String value;
	
	public static final String PATTERN = "ENABLE";
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void Apply(ChannelIdentifier id, MusicEventListener listener) {
		listener.enableChannel(new MusicChannelEnableEvent(this,id));
	}
	
	@Override
	public String toString(){
		return value;
	}
}
