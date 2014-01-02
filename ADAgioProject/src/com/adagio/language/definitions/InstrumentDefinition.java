package com.adagio.language.definitions;

import org.modelcc.Constraint;
import org.modelcc.FreeOrder;
import org.modelcc.IModel;
import org.modelcc.Optional;
import org.modelcc.Position;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.definitions.InstrumentDefinitionEvent;
import com.adagio.instruments.Instrument;
import com.adagio.instruments.LimitedPolyphonicInstrument;
import com.adagio.instruments.MonophonicInstrument;
import com.adagio.instruments.PolyphonicInstrument;
import com.adagio.language.instruments.InstrumentIdentifier;
import com.adagio.language.instruments.features.LimitedPolyphonicType;
import com.adagio.language.instruments.features.MonophonicType;
import com.adagio.language.instruments.features.PhoneticType;
import com.adagio.language.instruments.features.PolyphonicType;
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
	
	@Constraint
	boolean validNumRegisters(){
		if(phType.getClass().equals(MonophonicType.class)
		|| phType.getClass().equals(PolyphonicType.class)){
			if(registers != null && registers.length != 1){
				System.err.println("Error 17: Monophonic and Unlimited Polyphonic instrument"
						+ " can not have more than 1 register");
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void run(MusicEventListener listener) {
		Instrument instrument;
		
		if(phType.getClass().equals(MonophonicType.class)){
			instrument = new MonophonicInstrument(timbre, registers);
		}
		else if(phType.getClass().equals(LimitedPolyphonicType.class)){
			instrument = new LimitedPolyphonicInstrument(timbre, registers);
		}
		else{
			instrument = new PolyphonicInstrument(timbre, registers);
		}
		
		listener.instrumentDefinition(new InstrumentDefinitionEvent(this, identifier, instrument));	
	}
}
