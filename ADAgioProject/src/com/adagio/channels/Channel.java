package com.adagio.channels;

import com.adagio.instruments.Instrument;
import com.adagio.instruments.PolyphonicInstrument;
import com.adagio.language.channels.channeloptions.VolumeModifier;
import com.adagio.rhythms.Rhythm;

/**
 * @author Wungo
 */
public class Channel {
	
	private int volume;
	private boolean enable;
	private Instrument instrument;
	private Rhythm rhythm;
	
	private boolean erased;
	private String music;
	private int numBars;
	
	private boolean volumeChanged;
	private boolean instrumentChanged;
	private boolean rhythmChanged;
	//If music have chords, "used" will be true.
	private boolean used;

	public Channel() {
		instrument = new PolyphonicInstrument();
		volume = VolumeModifier.MAX_VOLUME;
		enable = true;
		erased = false;
		music = "";
		numBars = 0;
		volumeChanged = true;
		instrumentChanged = true;
		used = false;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public int getVolume() {
		return this.volume;
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

	public boolean hasVolumeChanged() {
		return volumeChanged;
	}

	public void setVolumeChanged(boolean volumeChanged) {
		this.volumeChanged = volumeChanged;
	}
	
	
	public boolean hasInstrumentChanged() {
		return instrumentChanged;
	}

	public void setInstrumentChanged(boolean instrumentChanged) {
		this.instrumentChanged = instrumentChanged;
	}
	

	public void addMusic(String music){
		this.music += music;
	}
	
	public void addNumBars(int numBars){
		this.numBars += numBars;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Rhythm getRhythm() {
		return rhythm;
	}

	public boolean isRhythmChanged() {
		return rhythmChanged;
	}

	public void setRhythm(Rhythm rhythm) {
		this.rhythm = rhythm;
	}

	public void setRhythmChanged(boolean rhythmChanged) {
		this.rhythmChanged = rhythmChanged;
	}
}
