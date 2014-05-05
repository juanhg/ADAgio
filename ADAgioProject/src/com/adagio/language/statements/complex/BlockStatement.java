package com.adagio.language.statements.complex;
import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
import com.adagio.language.statements.Statement;

@Prefix("\\{")
@Suffix("\\}")
public class BlockStatement extends ComplexStatement implements IModel {

	@Multiplicity(minimum = 0)
	Statement [] statements;
	
	@Override
	public void run(MusicEventListener listener) {
		for(Statement current: statements){
			current.run(listener);
		}
	}

}