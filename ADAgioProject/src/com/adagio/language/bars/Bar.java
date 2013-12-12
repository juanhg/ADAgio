package com.adagio.language.bars;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;

public class Bar implements IModel {
	@Multiplicity(minimum = 1)
	BarItem [] barItems;

	public BarItem[] getBarItems() {
		return barItems;
	}

}
