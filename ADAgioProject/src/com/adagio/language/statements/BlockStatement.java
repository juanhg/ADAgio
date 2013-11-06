package com.adagio.language.statements;
import com.adagio.language.*;

import org.modelcc.*;

@Prefix("\\{")
@Suffix("\\}")
public class BlockStatement extends Statement implements IModel {

	@Minimum(0)
	Statement [] statements;
	
	@Override
	public void run(RunData data) {
		for(Statement current: statements){
			current.run(data);
		}
	}

}