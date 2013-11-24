package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.io.lilypond.RunData;
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
	public void Apply(ChannelIdentifier id, RunData data) {
		if(data.getChannelsDB().containsKey(id)){
			data.getChannelsDB().get(id).setInstrument(instrument);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"" + this.toString() + "\" can't be applied.");
		}
	}

}
