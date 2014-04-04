package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.channels.ChannelInstrumentEvent;
import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.instruments.InstrumentIdentifier;

public class InstrumentModifier extends StatusModifier implements IModel{

	@Prefix({"(?i)INSTRUMENT","="})
	InstrumentIdentifier instrumentID;
	
	public static final String PATTERN = "INSTRUMENT";
	
	@Override
	public void Apply(ChannelIdentifier id, MusicEventListener listener) {
		listener.setChannelInstrument(new ChannelInstrumentEvent(this,id,instrumentID));
	}

}
