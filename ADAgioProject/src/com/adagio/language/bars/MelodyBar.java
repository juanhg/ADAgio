package com.adagio.language.bars;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Separator;

public class MelodyBar implements IModel {
	
	@Separator("( |\\n|\\r|\\t)+")
	@Multiplicity(minimum = 1)
	MelodyComponent [] mComponents;
	
	public MelodyComponent [] getMComponents(){
		return mComponents;
	}
	
	@Override
	public String toString(){
		String composition = "";
		for(MelodyComponent current: mComponents){
			composition += current.toString() + " ";
		}
		return composition;
	}
}
