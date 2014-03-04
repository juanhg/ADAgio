package com.adagio.events;

import java.util.EventListener;

import com.adagio.events.channels.MusicChannelIdentifierEvent;
import com.adagio.events.channels.MusicChannelInstrumentEvent;
import com.adagio.events.channels.MusicChannelRhythmEvent;
import com.adagio.events.channels.MusicChannelVolumeEvent;
import com.adagio.events.chords.MusicChordAddEvent;
import com.adagio.events.chords.MusicChordEvent;
import com.adagio.events.definitions.InstrumentDefinitionEvent;
import com.adagio.events.definitions.MusicTempoDefinitionEvent;
import com.adagio.events.definitions.RhythmDefinitionEvent;
import com.adagio.events.statements.MusicDefinedTempoStatementEvent;
import com.adagio.events.statements.MusicPlayStatementEvent;
import com.adagio.events.statements.MusicRelativeStatementEvent;
import com.adagio.events.statements.MusicTimeStatementEvent;
import com.adagio.events.statements.MusicUndefinedTempoStatementEvent;

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
	public void setChannelRhythm(MusicChannelRhythmEvent e);
	
	/**
	 * Recovers and erased channel
	 */
	public void recoverChannel(MusicChannelIdentifierEvent e);
	
	
	/** ----- STATEMENT EVENTS ----- **/
	public void setRelative(MusicRelativeStatementEvent e);
	public void musicPlay(MusicPlayStatementEvent e);
	public void setTime(MusicTimeStatementEvent e);
	public void setTempo(MusicDefinedTempoStatementEvent e);
	public void setTempo(MusicUndefinedTempoStatementEvent e);
	
	/** ----- NOTE EVENTS ----- **/

	
	
	/** ----- CHORDS EVENTS ----- **/
	
	/**
	 * Looks for chord in the Data base
	 * @param e
	 * @return True if is Defined. False in other case
	 */
	public boolean isChordDefined(MusicChordEvent e);
	public void addChord(MusicChordAddEvent e);
	
	
	/** ----- DEFINITIONS ----- **/
	
	public void tempoDefinition(MusicTempoDefinitionEvent e);
	public void instrumentDefinition(InstrumentDefinitionEvent e);
	public void rhythmDefinition(RhythmDefinitionEvent e);
}
