package com.adagio.language.statements;
import com.adagio.events.MusicEventListener;
import com.adagio.io.lilypond.RunData;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

import org.modelcc.*;


@Prefix("(?i)relative")
public class RelativeStatement extends Statement implements IModel {

	AbsoluteMusicNote value;
	
	@Override
	public void run(RunData data, MusicEventListener listener) {
		data.setRelative(this.value);
	}

}