package com.adagio.language.musicnotes;
import org.modelcc.*;

import com.adagio.events.MusicEventListener;

public abstract class MusicNote implements IModel {
	
	/**
	 * Obtain the note's absoluteMusicNote form 
	 * @param data
	 * @return A AbsolutMusicNote that contains the equivalent of the absolute or relative note.
	 */
	public abstract AbsoluteMusicNote toAbsoluteMusicNote(MusicEventListener listener);
	public abstract String getBasicNoteNameString();
	public abstract String toString();
}
