package com.adagio.language.musicnotes;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Optional;

import com.adagio.events.MusicEventListener;
import com.adagio.events.notes.MusicNoteNameEvent;
import com.adagio.language.musicnotes.octavealterations.OctaveAlteration;

public class RelativeMusicNote extends MusicNote implements IModel {
	
	public MusicNoteName musicNoteName;
	@Optional
	public OctaveAlteration octave;
	
	@Constraint
	boolean silenceRestriction(){
		if(musicNoteName.isSilence()){
			if(octave == null){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return true;
		}
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

	@Override
	public AbsoluteMusicNote toAbsoluteMusicNote(MusicEventListener listener) {
		int nAlterations = 0;
		AbsoluteMusicNote result = new AbsoluteMusicNote();
		nAlterations += listener.alterationFromReference(new MusicNoteNameEvent(this,musicNoteName));
		if(octave != null){
			nAlterations += octave.toInt();
		}
		result.setMusicNoteName(musicNoteName);
		result.setOctave(nAlterations);
//		if(duration != null){
//			result.setDuration(duration.clone());
//		}
		return result;
	}

	@Override
	public String getBasicNoteNameString() {
		return this.getMusicNoteName().getBaseNoteName().getValue();
	}

	@Override
	public String toString() {
		String composition = "";
		composition += musicNoteName.toString();
		if(octave != null){
			composition += octave.toString();
		}
		if(duration != null){
			composition += duration.toString();
		}
		return composition;
	}
	
	@Override
	public boolean isSilence() {
		return musicNoteName.isSilence();
	}

	/**
	 * Generates a RelativeMusicNote that represents a silence
	 * @return Silence-RelativeMusicNote
	 */
	public static RelativeMusicNote genSilence() {
		RelativeMusicNote note = new RelativeMusicNote();
		note.musicNoteName = BasicNoteName.genSilence();
		note.octave = null;
		
		return note;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelativeMusicNote other = (RelativeMusicNote) obj;
		if (musicNoteName == null) {
			if (other.musicNoteName != null)
				return false;
		} else if (!musicNoteName.equals(other.musicNoteName))
			return false;
		if (octave == null) {
			if (other.octave != null)
				return false;
		} else if (!octave.equals(other.octave))
			return false;
		return true;
	}


	@Override
	public RelativeMusicNote clone() {
		RelativeMusicNote rNote = new RelativeMusicNote();
		rNote.musicNoteName = this.musicNoteName.clone();
		rNote.octave = this.octave.clone();
		if(this.duration != null){
			rNote.duration = this.duration.clone();
		}
		return null;
	}
	
	

}
