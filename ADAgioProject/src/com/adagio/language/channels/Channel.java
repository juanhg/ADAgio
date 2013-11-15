package com.adagio.language.channels;

import com.adagio.language.channels.channeloptions.VolumeModifier;
import com.adagio.language.instruments.Instrument;

/**
 * @author Wungo
 */
public class Channel {

	private int volume;
	private boolean enable;
	private Instrument instrument;

	public Channel() {
		instrument = new Instrument();
		volume = VolumeModifier.MAX_VOLUME;
		enable = true;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (enable ? 1231 : 1237);
		result = prime * result
				+ ((instrument == null) ? 0 : instrument.hashCode());
		result = prime * result + volume;
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
		if (instrument == null) {
			if (other.instrument != null)
				return false;
		} else if (!instrument.equals(other.instrument))
			return false;
		if (volume != other.volume)
			return false;
		return true;
	}
	
	

}
