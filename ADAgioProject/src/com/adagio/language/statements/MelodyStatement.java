package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
import com.adagio.language.bars.MelodyBar;
import com.adagio.language.channels.ChannelIdentifier;

@Prefix("(?i)Melody")
public class MelodyStatement extends Statement implements IModel {
	
	ChannelIdentifier identifier;
	
	@Prefix("\\|?")
	@Suffix("\\|?")
	@Separator("\\|")
	@Multiplicity(minimum = 1)
	MelodyBar [] mBars;
	
	@Override
	public void run(MusicEventListener listener) {
		
	}
	
	public MelodyBar [] getMBars(){
		return mBars;
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
}
