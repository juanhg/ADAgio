package com.adagio.language.bars;

import org.modelcc.Associativity;
import org.modelcc.AssociativityType;
import org.modelcc.IModel;
import org.modelcc.Multiplicity;

@Associativity(AssociativityType.RIGHT_TO_LEFT)
public class Bar implements IModel {
	@Multiplicity(minimum = 1)
	BarItem [] barItems;

	public BarItem[] getBarItems() {
		return barItems;
	}

}
