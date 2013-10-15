package com.adagio.language.musicnotes;

import org.modelcc.IModel;
import org.modelcc.Optional;

import com.adagio.language.musicnotes.ovtavealterations.OctaveAlteration;

public class RelativeMusicNote extends MusicNote implements IModel {
	
	public MusicNoteName musicNoteName;
	@Optional
	public OctaveAlteration octave;
	
	@Override
	public String toString() {
		if(octave != null){
			return musicNoteName.toString() + octave.toString();
		}
		return musicNoteName.toString();
	}

	public MusicNoteName getMusicNoteName() {
		return musicNoteName;
	}

	public void setMusicNoteName(MusicNoteName noteName) {
		this.musicNoteName = noteName;
	}

	public OctaveAlteration getOctave() {
		return octave;
	}

	public void setOctave(OctaveAlteration octave) {
		this.octave = octave;
	}
	
	

}
