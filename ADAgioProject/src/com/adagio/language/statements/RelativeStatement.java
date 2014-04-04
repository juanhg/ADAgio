package com.adagio.language.statements;
import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.statements.RelativeStatementEvent;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

@Prefix("(?i)relative")
public class RelativeStatement extends Statement implements IModel {

	AbsoluteMusicNote value;
	
	@Override
	public void run(MusicEventListener listener) {
		listener.setRelative(new RelativeStatementEvent(this, this.value));
	}

}