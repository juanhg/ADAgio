package com.adagio.language.definitions;

import org.modelcc.FreeOrder;
import org.modelcc.IModel;
import org.modelcc.Optional;
import org.modelcc.Position;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
import com.adagio.language.instruments.InstrumentIdentifier;
import com.adagio.language.instruments.features.PhoneticType;
import com.adagio.language.instruments.features.Register;
import com.adagio.language.instruments.features.Timbre;

@Prefix({"(?i)Define", "(?i)Instrument"})
@FreeOrder
public class InstrumentDefinition extends Definition implements IModel {

	@Prefix("\"")
	@Suffix("\"")

	InstrumentIdentifier identifier;
	
	@Position(element="identifier", position=Position.AFTER)
	PhoneticType phType;
	
	@Optional
	@Prefix("(?i)Timbre")
	Timbre timbre;
	
	@Optional
	@Separator(",")
	@Prefix("(?i)Registers")
	Register registers[];
	
	@Override
	public void run(MusicEventListener listener) {
		// TODO Auto-generated method stub
		
	}

}
