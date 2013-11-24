package com.adagio.language.musicnotes;

import org.modelcc.IModel;
import org.modelcc.Optional;

import com.adagio.io.lilypond.RunData;
import com.adagio.language.musicnotes.octavealterations.OctaveAlteration;

public class RelativeMusicNote extends MusicNote implements IModel {
	
	public MusicNoteName musicNoteName;
	@Optional
	public OctaveAlteration octave;
	

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

	@Override
	public AbsoluteMusicNote toAbsoluteMusicNote(RunData data) {
		int nAlterations = 0;
		AbsoluteMusicNote result = new AbsoluteMusicNote();
		nAlterations += data.alterationFromReference(musicNoteName);
		if(octave != null){
			nAlterations += octave.toInt();
		}
		result.setMusicNoteName(musicNoteName);
		result.setOctave(nAlterations);
		return result;
	}

	@Override
	public String getBasicNoteNameString() {
		return this.getMusicNoteName().getBaseNoteName().getValue();
	}

	@Override
	public String toString() {
		return this.getMusicNoteName().toString() + this.getOctave().toString();
	}
	
	

}
