package com.adagio.events;

import java.util.EventListener;

import com.adagio.events.channels.ChannelIdentifierEvent;
import com.adagio.events.channels.ChannelInstrumentEvent;
import com.adagio.events.channels.ChannelRhythmEvent;
import com.adagio.events.channels.ChannelVolumeEvent;
import com.adagio.events.chords.ChordEvent;
import com.adagio.events.definitions.InstrumentDefinitionEvent;
import com.adagio.events.definitions.RhythmDefinitionEvent;
import com.adagio.events.definitions.TempoDefinitionEvent;
import com.adagio.events.definitions.ChorDefinitionEvent;
import com.adagio.events.statements.DefinedTempoStatementEvent;
import com.adagio.events.statements.MelodyLyricsEvent;
import com.adagio.events.statements.MusicPlayStatementEvent;
import com.adagio.events.statements.RelativeStatementEvent;
import com.adagio.events.statements.TimeStatementEvent;
import com.adagio.events.statements.UndefinedTempoStatementEvent;

public interface MusicEventListener extends EventListener {
		
	/** ----- DEFINITIONS EVENTS ----- **/
	
	public void chordDefinition(ChorDefinitionEvent e);
	public void tempoDefinition(TempoDefinitionEvent e);
	public void instrumentDefinition(InstrumentDefinitionEvent e);
	public void rhythmDefinition(RhythmDefinitionEvent e);
	
	
	/** ----- STATEMENT EVENTS ----- **/
	public void setRelative(RelativeStatementEvent e);
	public void harmonyPlay(MusicPlayStatementEvent e);
	public void melodyPlay(MelodyLyricsEvent e);
	public void melodyLyricsPlay(MelodyLyricsEvent e);
	public void setTime(TimeStatementEvent e);
	public void setTempo(DefinedTempoStatementEvent e);
	public void setTempo(UndefinedTempoStatementEvent e);
	

	/** ----- CHANNEL EVENTS ----- **/
	public void createChannel(ChannelIdentifierEvent e);
	public void destroyChannel(ChannelIdentifierEvent e);
	public void enableChannel(ChannelIdentifierEvent e);
	public void disableChannel(ChannelIdentifierEvent e);
	public void melodyChannel(ChannelIdentifierEvent e);
	public void harmonyChannel(ChannelIdentifierEvent e);
	public boolean existsChannel(ChannelIdentifierEvent e);
	public boolean isErasedChannel(ChannelIdentifierEvent e);
	public void setChannelVolume(ChannelVolumeEvent e);
	public void setChannelInstrument(ChannelInstrumentEvent e);
	public void setChannelRhythm(ChannelRhythmEvent e);
	
	/**
	 * Recovers an erased channel
	 */
	public void recoverChannel(ChannelIdentifierEvent e);
	
	/** ----- CHORDS EVENTS ----- **/
	
	/**
	 * Looks for chord in the Data base
	 * @param e
	 * @return True if is Defined. False in other case
	 */
	public boolean isChordDefined(ChordEvent e);
}
