package com.adagio.language.sentences;
import com.adagio.language.*;

import org.modelcc.*;
import org.modelcc.types.UnsignedIntegerModel;

@Prefix("(?i)repeat")
@Priority(precedes=PlaySentence.class)
public class RepeatSentence extends Sentence implements IModel {
	
	@Suffix("(?i)times")
	UnsignedIntegerModel times;
	
	@Minimum(0)
	BlockSentence [] sentences;
	
	@Override
	public void run(RunData data) {
		for(int i = 0; i < times.intValue(); i++){
			for(Sentence current: sentences){
				current.run(data);
			}
		}
	}

}