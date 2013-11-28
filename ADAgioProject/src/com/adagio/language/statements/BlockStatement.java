package com.adagio.language.statements;
import com.adagio.events.MusicEventListener;

import org.modelcc.*;

@Prefix("\\{")
@Suffix("\\}")
public class BlockStatement extends Statement implements IModel {

	@Minimum(0)
	Statement [] statements;
	
	@Override
	public void run(MusicEventListener listener) {
		for(Statement current: statements){
			current.run(listener);
		}
	}

}