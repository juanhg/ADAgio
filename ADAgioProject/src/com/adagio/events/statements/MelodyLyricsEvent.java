package com.adagio.events.statements;

import java.util.EventObject;

import com.adagio.structures.Lyrics;
import com.adagio.structures.Melody;

public class MelodyLyricsEvent extends EventObject {

	private static final long serialVersionUID = -270556291380736921L;

	private Melody melody;
	private Lyrics lyrics;
	
	public MelodyLyricsEvent(Object source, Melody melody, Lyrics lyrics) {
		super(source);
		this.melody = melody;
		this.lyrics = lyrics;
	}

	public Melody getMelody() {
		return melody;
	}

	public Lyrics getLyrics() {
		return lyrics;
	}

}
