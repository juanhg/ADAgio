package com.adagio.channels;

public class Voice {
	String music;
	int numBars;
	
	public Voice(){
		music = "";
		numBars = 0;
	}
	
	public void addMusic(String musicToAdd, int numBars){
		music += musicToAdd;
		numBars += numBars;
	}

	public String getMusic() {
		return music;
	}

	public int getNumBars() {
		return numBars;
	}
	
	public Voice clone(){
		Voice voice = new Voice();
		voice.music = this.music;
		voice.numBars = this.numBars;
		return voice;
	}
	
}
