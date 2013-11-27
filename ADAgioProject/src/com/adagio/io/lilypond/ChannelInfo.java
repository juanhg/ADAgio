package com.adagio.io.lilypond;

import com.adagio.language.channels.Channel;

public class ChannelInfo {
	private Channel channel;
	private boolean erased;
	private String music;
	private int duration;
	
	public static final String DEFAULT_CHANNEL_IDENTIFIER = "default";

	public ChannelInfo() {
		channel = new Channel();
		erased = false;
		music = "";
		duration = 0;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public boolean isErased() {
		return erased;
	}

	public void setErased(boolean erased) {
		this.erased = erased;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void addMusic(String music){
		this.music += music;
	}
	
	public void addDuration(int duration){
		this.duration += duration;
	}
	
}
