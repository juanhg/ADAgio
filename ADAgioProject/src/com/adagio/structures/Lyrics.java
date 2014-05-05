package com.adagio.structures;

import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.statements.simple.Verse;

public class Lyrics {
	ChannelIdentifier identifier;
	Verse [] verses;
	
	
	public Lyrics(ChannelIdentifier identifier, Verse [] verses){
		this.identifier = identifier;
		this.verses = verses;
	}
	
	public ChannelIdentifier getIdentifier() {
		return identifier;
	}
	public void setIdentifier(ChannelIdentifier identifier) {
		this.identifier = identifier;
	}
	public Verse[] getVerses() {
		return verses;
	}
	public void setVerses(Verse[] verses) {
		this.verses = verses;
	}
	
}
