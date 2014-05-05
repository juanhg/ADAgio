package com.adagio.language.statements.complex;
import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;
import org.modelcc.Priority;
import org.modelcc.Suffix;
import org.modelcc.types.UnsignedIntegerModel;

import com.adagio.events.MusicEventListener;
import com.adagio.language.statements.simple.PlayStatement;

@Prefix("(?i)repeat")
@Priority(precedes=PlayStatement.class)
public class RepeatStatement extends ComplexStatement implements IModel {
	
	@Suffix("(?i)times")
	UnsignedIntegerModel times;
	
	BlockStatement sentence;
	
	@Override
	public void run(MusicEventListener listener) {
		for(int i = 0; i < times.intValue(); i++){
				sentence.run(listener);
		}
	}

}