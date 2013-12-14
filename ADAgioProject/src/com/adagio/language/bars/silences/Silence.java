package com.adagio.language.bars.silences;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

import com.adagio.language.bars.BarItem;

@Pattern(regExp = "_|S|s")
public class Silence extends BarItem implements IModel {
	@Value
	String value;
}
