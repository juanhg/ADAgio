package com.adagio.structures;

import com.adagio.language.bars.MelodyBar;
import com.adagio.language.channels.ChannelIdentifier;

public class Melody {
	ChannelIdentifier identifier;
	MelodyBar [] mBars;
	
	public Melody(ChannelIdentifier identifier, MelodyBar [] mBars){
		this.identifier = identifier;
		this.mBars = mBars;
	}
	
	public ChannelIdentifier getIdentifier() {
		return identifier;
	}
	public void setIdentifier(ChannelIdentifier id) {
		this.identifier = id;
	}
	public MelodyBar[] getMBars() {
		return mBars;
	}
	public void setmBars(MelodyBar[] mBars) {
		this.mBars = mBars;
	}
	
}
