package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

import com.adagio.events.MusicChannelDestroyEvent;
import com.adagio.events.MusicEventListener;
import com.adagio.language.channels.ChannelIdentifier;

@Pattern(regExp = "(?i)DESTROY")
public class DestroyCommand extends Command implements IModel {

	@Value
	String value;
	
	public static final String PATTERN = "DESTROY";
	
	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public void Apply(ChannelIdentifier id, MusicEventListener listener) {
		listener.destroyChannel(new MusicChannelDestroyEvent(this,id));
	}
}
