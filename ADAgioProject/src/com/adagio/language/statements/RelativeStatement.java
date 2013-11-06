package com.adagio.language.statements;
import com.adagio.language.*;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

import org.modelcc.*;


@Prefix("(?i)relative")
public class RelativeStatement extends Statement implements IModel {

	AbsoluteMusicNote value;
	
	@Override
	public void run(RunData data) {
		data.setRelative(this.value);
	}

}