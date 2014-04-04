package com.adagio.language.bars;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Separator;

public class MelodyBar implements IModel {
	@Separator("\\-")
	@Multiplicity(minimum = 1)
	MelodyBarComponent [] mComponents;
	
	public MelodyBarComponent [] getMComponents(){
		return mComponents;
	}
	
	@Override
	public String toString(){
		String composition = "";
		for(MelodyBarComponent current: mComponents){
			composition += current.toString() + " ";
		}
		return composition;
	}
}
