package com.adagio.language.statements;
import com.adagio.events.MusicEventListener;
import com.adagio.io.lilypond.RunData;

import org.modelcc.*;

@Prefix("\\{")
@Suffix("\\}")
public class BlockStatement extends Statement implements IModel {

	@Minimum(0)
	Statement [] statements;
	
	@Override
	public void run(RunData data, MusicEventListener listener) {
		for(Statement current: statements){
			current.run(data,listener);
		}
	}

}