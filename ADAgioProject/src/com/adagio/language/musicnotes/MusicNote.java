package com.adagio.language.musicnotes;
import org.modelcc.IModel;

import com.adagio.duration.Duration;

public abstract class MusicNote implements IModel {
	
	protected Duration duration;
	protected boolean optional; 
	protected boolean ligatured;
	
	/**
	 * Obtain the AbsoluteMusicNote-form of the note 
	 * @param relative The absolute music note set as the relative of the music piece
	 * @return A AbsolutMusicNote that contains the equivalent of the absolute or relative note.
	 */
	public abstract AbsoluteMusicNote toAbsoluteMusicNote(AbsoluteMusicNote relative);
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
