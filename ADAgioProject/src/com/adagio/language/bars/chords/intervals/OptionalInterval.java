package com.adagio.language.bars.chords.intervals;
import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.Suffix;

@Prefix("\\(")
@Suffix("\\)")
public class OptionalInterval extends Interval implements IModel {

	public OptionalInterval(String quality, int number) {
		super(quality, number);
	}
	
	public OptionalInterval() {
		super();
	}

}
