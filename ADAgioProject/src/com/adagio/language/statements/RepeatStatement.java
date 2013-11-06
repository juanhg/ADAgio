package com.adagio.language.statements;
import com.adagio.language.*;

import org.modelcc.*;
import org.modelcc.types.UnsignedIntegerModel;

@Prefix("(?i)repeat")
@Priority(precedes=PlayStatement.class)
public class RepeatStatement extends Statement implements IModel {
	
	@Suffix("(?i)times")
	UnsignedIntegerModel times;
	
	@Minimum(0)
	BlockStatement [] sentences;
	
	@Override
	public void run(RunData data) {
		for(int i = 0; i < times.intValue(); i++){
			for(Statement current: sentences){
				current.run(data);
			}
		}
	}

}