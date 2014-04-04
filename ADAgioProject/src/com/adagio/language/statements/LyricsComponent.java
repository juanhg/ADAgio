package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Suffix;

import com.adagio.language.channels.ChannelIdentifier;

@Prefix("(?i)Lyrics")
public class LyricsComponent extends PlayComponent implements IModel {

	ChannelIdentifier identifier;
	
	@Separator("\\|")
	@Multiplicity(minimum = 1)
	@Suffix("\\|?")
	Verse [] verses;



	@Override
	public String toString(){
		String composition = "";
		composition += "Lyrics " + identifier.toString() + " ";
		for(int i = 0; i < verses.length; i++){
			if(verses[i] != null){
				composition += verses[i].toString() + " ";
			}
			else{
				composition += "null";
			}
			if(i != (verses.length-1)){
				composition += "| ";
			}
		}
		return composition;
	}



	public ChannelIdentifier getIdentifier() {
		return identifier;
	}



	public Verse[] getVerses() {
		return verses;
	}



	@Override
	public boolean isMelody() {
		return false;
	}

	@Override
	public boolean isLyrics() {
		return true;
	}
}
