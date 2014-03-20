package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
import com.adagio.language.channels.ChannelIdentifier;

@Prefix("(?i)Lyrics")
public class LyricsStatement extends Statement implements IModel {
	
	ChannelIdentifier identifier;

	
	@Suffix("\\|?")
	@Prefix("\\|")
	@Separator("\\|")
	@Multiplicity(minimum = 1)
	Verse [] verses;

	@Override
	public void run(MusicEventListener listener) {

	}

	@Override
	public String toString(){
		String composition = "";
		composition += "Melody " + identifier.toString() + " ";
		for(int i = 0; i < verses.length; i++){
			composition += verses[i].toString() + " ";
			if(i != (verses.length-1)){
				composition += "| ";
			}
		}
		return composition;
	}
}
