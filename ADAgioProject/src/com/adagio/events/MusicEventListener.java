package com.adagio.events;

import java.util.EventListener;

import com.adagio.events.channels.MusicChannelIdentifierEvent;
import com.adagio.events.channels.MusicChannelInstrumentEvent;
import com.adagio.events.channels.MusicChannelVolumeEvent;
import com.adagio.events.chords.MusicChordAddEvent;
import com.adagio.events.chords.MusicChordEvent;
import com.adagio.events.notes.MusicNoteNameEvent;
import com.adagio.events.notes.MusicNoteToAbsoluteEvent;
import com.adagio.events.statements.MusicPlayStatementEvent;
import com.adagio.events.statements.MusicRelativeStatementEvent;
import com.adagio.events.tempos.MusicTempoDefinitionEvent;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

public interface MusicEventListener extends EventListener {
		

	/** ----- CHANNEL EVENTS ----- **/
	public void createChannel(MusicChannelIdentifierEvent e);
	public void destroyChannel(MusicChannelIdentifierEvent e);
	public void enableChannel(MusicChannelIdentifierEvent e);
	public void disableChannel(MusicChannelIdentifierEvent e);
	public boolean existsChannel(MusicChannelIdentifierEvent e);
	public boolean isErasedChannel(MusicChannelIdentifierEvent e);
	public void setChannelVolume(MusicChannelVolumeEvent e);
	public void setChannelInstrument(MusicChannelInstrumentEvent e);
	
	/**
	 * Recovers and erased channel
	 */
	public void recoverChannel(MusicChannelIdentifierEvent e);
	
	
	/** ----- STATEMENT EVENTS ----- **/
	public void setRelative(MusicRelativeStatementEvent e);
	public void musicPlay(MusicPlayStatementEvent e);
	
	/** ----- NOTE EVENTS ----- **/
	public AbsoluteMusicNote toAbsolute(MusicNoteToAbsoluteEvent e);
	public int alterationFromReference(MusicNoteNameEvent e);
	
	
	/** ----- CHORDS EVENTS ----- **/
	
	/**
	 * Looks for chord in the Data base
	 * @param e
	 * @return True if is Defined. False in other case
	 */
	public boolean isChordDefined(MusicChordEvent e);
	public void addChord(MusicChordAddEvent e);
	
	/** ----- TEMPOS EVENTS ----- **/
	
	public void tempoDefinition(MusicTempoDefinitionEvent e);
}
