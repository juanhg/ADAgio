package com.adagio.language.channels;

/**
 * @author Wungo
 */
public class Channel {
	
	private int volume;
	private boolean enable;
	
	private final static int MAX_VOLUME = 127;
	private final static int MIN_VOLUME = 0;
	
	public Channel(){
		volume = MAX_VOLUME;
		enable = true;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public static int getMaxVolume() {
		return MAX_VOLUME;
	}

	public static int getMinVolume() {
		return MIN_VOLUME;
	}
	
	
}
