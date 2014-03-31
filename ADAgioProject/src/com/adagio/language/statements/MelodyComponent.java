package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Suffix;

import com.adagio.language.bars.MelodyBar;
import com.adagio.language.channels.ChannelIdentifier;

@Prefix("(?i)Melody")
public class MelodyComponent extends PlayComponent implements IModel {
	
	ChannelIdentifier identifier;
	
	@Prefix("\\|?")
	@Suffix("\\|?")
	@Separator("\\|")
	@Multiplicity(minimum = 1)
	MelodyBar [] mBars;
	
	
	public MelodyBar [] getMBars(){
		return mBars;
	}
	
	
	
	public ChannelIdentifier getIdentifier() {
		return identifier;
	}



	public void setIdentifier(ChannelIdentifier identifier) {
		this.identifier = identifier;
	}



	@Override
	public String toString(){
		String composition = "";
		composition += "Melody " + identifier.toString() + " ";
		for(int i = 0; i < mBars.length; i++){
			composition += mBars[i].toString() + " ";
			if(i != (mBars.length-1)){
				composition += "| ";
			}
		}
		return composition;
	}

	@Override
	public boolean isMelody() {
		return true;
	}

	@Override
	public boolean isLyrics() {
		return false;
	}
}
