package com.adagio.language.statements;

import org.modelcc.Associativity;
import org.modelcc.AssociativityType;
import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
import com.adagio.language.channels.ChannelIdentifier;

@Prefix("(?i)Lyrics")
public class LyricsStatement extends Statement implements IModel {


	@Separator("\\|")
	@Multiplicity(minimum = 1)
	@Suffix("\\|?")
	Verse [] verses;

	@Override
	public void run(MusicEventListener listener) {

	}

	@Override
	public String toString(){
		String composition = "";
//		composition += "Lyrics " + identifier.toString() + " ";
		for(int i = 0; i < verses.length; i++){
			composition += verses[i].toString() + " ";
			if(i != (verses.length-1)){
				composition += "| ";
			}
		}
		return composition;
	}
}
