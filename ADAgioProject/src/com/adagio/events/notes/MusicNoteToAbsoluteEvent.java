package com.adagio.events.notes;

import java.util.EventObject;

import com.adagio.language.musicnotes.MusicNote;

public class MusicNoteToAbsoluteEvent extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1578685439059379536L;
	MusicNote note;
	
	public MusicNoteToAbsoluteEvent(Object source, MusicNote note){
		super(source);
		this.note = note;
	}

	public MusicNote getNote() {
		return note;
	}

	public void setNote(MusicNote note) {
		this.note = note;
	}
	
	
}
