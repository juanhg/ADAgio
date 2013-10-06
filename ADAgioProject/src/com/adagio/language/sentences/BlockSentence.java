package com.adagio.language.sentences;
import com.adagio.language.*;

import org.modelcc.*;

@Prefix("\\{")
@Suffix("\\}")
public class BlockSentence extends Sentence implements IModel {

	@Minimum(0)
	Sentence [] sentences;
	
	@Override
	public void run(RunData data) {
		for(Sentence current: sentences){
			current.run(data);
		}
	}

}