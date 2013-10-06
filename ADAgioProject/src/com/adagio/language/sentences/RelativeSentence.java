package com.adagio.language.sentences;
import com.adagio.language.*;
import org.modelcc.*;
import org.modelcc.types.QuotedStringModel;

@Prefix("(?i)relative")
public class RelativeSentence extends Sentence implements IModel {

	QuotedStringModel val;
	
	@Override
	public void run(RunData data) {
		data.setRelative(val.toString());
	}

}