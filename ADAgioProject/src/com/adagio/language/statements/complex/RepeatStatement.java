package com.adagio.language.statements.complex;
import org.modelcc.IModel;
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
	
	BlockStatement blockStatement;
	
	@Override
	public void run(MusicEventListener listener) {
		for(int i = 0; i < times.intValue(); i++){
				blockStatement.run(listener);
		}
	}

}