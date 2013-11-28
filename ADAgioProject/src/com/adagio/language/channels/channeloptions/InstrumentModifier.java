package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.channels.MusicChannelInstrumentEvent;
import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.instruments.Instrument;

public class InstrumentModifier extends StatusModifier implements IModel{

	@Prefix({"(?i)INSTRUMENT","="})
	Instrument instrument;
	
	public static final String PATTERN = "INSTRUMENT";
	
	@Override
	public String getValue() {
		return "instrument=" + instrument.getValue();
	}

	@Override
	public void Apply(ChannelIdentifier id, MusicEventListener listener) {
		listener.setChannelInstrument(new MusicChannelInstrumentEvent(this,id,instrument));
	}

}
