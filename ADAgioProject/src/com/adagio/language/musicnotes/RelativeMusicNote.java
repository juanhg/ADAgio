package com.adagio.language.musicnotes;

import org.modelcc.IModel;
import org.modelcc.Optional;

import com.adagio.language.musicnotes.ovtavealterations.OctaveAlteration;

public class RelativeMusicNote extends MusicNote implements IModel {
	
	public MusicNoteName noteName;
	@Optional
	public OctaveAlteration octave;
	
	@Override
	public String toString() {
		if(octave != null){
			return noteName.toString() + octave.toString();
		}
		return noteName.toString();
	}

	public MusicNoteName getNoteName() {
		return noteName;
	}

	public void setNoteName(MusicNoteName noteName) {
		this.noteName = noteName;
	}

	public OctaveAlteration getOctave() {
		return octave;
	}

	public void setOctave(OctaveAlteration octave) {
		this.octave = octave;
	}

}
