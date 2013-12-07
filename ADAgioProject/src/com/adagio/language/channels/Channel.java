package com.adagio.language.channels;

import com.adagio.language.channels.channeloptions.VolumeModifier;
import com.adagio.language.instruments.Instrument;

/**
 * @author Wungo
 */
public class Channel {
	
	public static final String DEFAULT_CHANNEL_IDENTIFIER = "default";

	private int volume;
	private boolean enable;
	private Instrument instrument;
	
	private boolean erased;
	private String music;
	private int numBars;
	private boolean volumeChanged;

	public Channel() {
		instrument = new Instrument();
		volume = VolumeModifier.MAX_VOLUME;
		enable = true;
		erased = false;
		music = "";
		numBars = 0;
		volumeChanged = true;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public double getVolume() {
		double doubleVolume = volume;
		return doubleVolume;
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

	public int getNumBars() {
		return numBars;
	}

	public void setNumBars(int numBars) {
		this.numBars = numBars;
	}

	public boolean isVolumeChanged() {
		return volumeChanged;
	}

	public void setVolumeChanged(boolean volumeChanged) {
		this.volumeChanged = volumeChanged;
	}
	
	public void addMusic(String music){
		this.music += music;
	}
	
	public void addNumBars(int numBars){
		this.numBars += numBars;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (enable ? 1231 : 1237);
		result = prime * result + (erased ? 1231 : 1237);
		result = prime * result
				+ ((instrument == null) ? 0 : instrument.hashCode());
		result = prime * result + ((music == null) ? 0 : music.hashCode());
		result = prime * result + numBars;
		result = prime * result + volume;
		result = prime * result + (volumeChanged ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Channel other = (Channel) obj;
		if (enable != other.enable)
			return false;
		if (erased != other.erased)
			return false;
		if (instrument == null) {
			if (other.instrument != null)
				return false;
		} else if (!instrument.equals(other.instrument))
			return false;
		if (music == null) {
			if (other.music != null)
				return false;
		} else if (!music.equals(other.music))
			return false;
		if (numBars != other.numBars)
			return false;
		if (volume != other.volume)
			return false;
		if (volumeChanged != other.volumeChanged)
			return false;
		return true;
	}

	

	
	
	

}
