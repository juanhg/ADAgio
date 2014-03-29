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
import com.adagio.language.instruments.InstrumentIdentifier;
import com.adagio.language.instruments.MonophonicType;
import com.adagio.language.instruments.PhoneticType;
import com.adagio.language.instruments.PolyphonicType;
import com.adagio.language.instruments.Register;
import com.adagio.language.instruments.Timbre;

@Prefix({"(?i)Define", "( |\\n|\\r|\\t)+","(?i)Instrument"})
@FreeOrder
public class InstrumentDefinition extends Definition implements IModel {

	@Prefix({"( |\\n|\\r|\\t)+", "\""})
	@Suffix("\"")
	InstrumentIdentifier identifier;
	
	@Prefix("( |\\n|\\r|\\t)+")
	@Position(element="identifier", position=Position.AFTER)
	PhoneticType phType;
	
	@Optional
	@Prefix({"( |\\n|\\r|\\t)+", "(?i)Timbre", "( |\\n|\\r|\\t)+"})
	Timbre timbre;
	
	@Optional
	@Separator({"( |\\n|\\r|\\t)*", ",", "( |\\n|\\r|\\t)*"})
	@Prefix({"( |\\n|\\r|\\t)+", "(?i)Registers", "( |\\n|\\r|\\t)+"})
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
		listener.instrumentDefinition(new InstrumentDefinitionEvent(this,identifier, phType, timbre, registers));
	}
}
