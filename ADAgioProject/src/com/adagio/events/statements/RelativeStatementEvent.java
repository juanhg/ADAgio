package com.adagio.events.statements;

import java.util.EventObject;

import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class RelativeStatementEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	private AbsoluteMusicNote relativeNote;
	
	public RelativeStatementEvent(Object arg0, AbsoluteMusicNote aNote) {
		super(arg0);
		this.relativeNote = aNote;
	}

	public AbsoluteMusicNote getaNote() {
		return relativeNote;
	}
}
