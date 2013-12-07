package com.adagio.events.notes;

import java.util.EventObject;

import com.adagio.language.musicnotes.MusicNoteName;

public class MusicNoteNameEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 722133536222690616L;
	private MusicNoteName musicNoteName;

	public MusicNoteNameEvent(Object source, MusicNoteName musicNoteName) {
		super(source);
		this.musicNoteName = musicNoteName;
	}

	public MusicNoteName getMusicNoteName() {
		return musicNoteName;
	}	
}
