package com.adagio.language.statements;
import com.adagio.events.MusicEventListener;
import com.adagio.events.statements.MusicRelativeStatementEvent;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

import org.modelcc.*;


@Prefix("(?i)relative")
public class RelativeStatement extends Statement implements IModel {

	AbsoluteMusicNote value;
	
	@Override
	public void run(MusicEventListener listener) {
		listener.setRelative(new MusicRelativeStatementEvent(this, this.value));
	}

}