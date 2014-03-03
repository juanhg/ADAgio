package com.adagio.language.musicnotes;
import java.util.ArrayList;
import java.util.List;

import org.modelcc.IModel;

import com.adagio.duration.Duration;
import com.adagio.events.MusicEventListener;
import com.adagio.language.figures.Figure;

public abstract class MusicNote implements IModel {
	
	protected Duration duration;
	protected boolean optional; 
	protected boolean ligatured;
	
	/**
	 * Obtain the AbsoluteMusicNote-form of the note 
	 * @param data
	 * @return A AbsolutMusicNote that contains the equivalent of the absolute or relative note.
	 */
	public abstract AbsoluteMusicNote toAbsoluteMusicNote(MusicEventListener listener);
	public abstract String getBasicNoteNameString();
	public abstract String toString();
	public abstract MusicNoteName getMusicNoteName();
	public abstract boolean isSilence();
	public abstract boolean equals(Object o);
	public abstract MusicNote clone();
	
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public boolean isOptional() {
		return optional;
	}
	public void setOptional(boolean optional) {
		this.optional = optional;
	}
	public boolean isLigatured() {
		return ligatured;
	}
	public void setLigatured(boolean ligatured) {
		this.ligatured = ligatured;
	}
	
	
	
	
}
