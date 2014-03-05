package com.adagio.language.musicnotes;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Optional;

import com.adagio.language.musicnotes.octavealterations.OctaveAlteration;

public class RelativeMusicNote extends MusicNote implements IModel {
	
	public MusicNoteName musicNoteName;
	@Optional
	public OctaveAlteration octaveAlteration;
	
	@Constraint
	boolean silenceRestriction(){
		if(musicNoteName.isSilence()){
			if(octaveAlteration == null){
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

	public RelativeMusicNote(){}
	
	public RelativeMusicNote(MusicNoteName musicNoteName, OctaveAlteration octaveAlteration){
		this.musicNoteName = musicNoteName;
		this.octaveAlteration = octaveAlteration;
	}
	

	public MusicNoteName getMusicNoteName() {
		return musicNoteName;
	}

	public void setMusicNoteName(MusicNoteName noteName) {
		this.musicNoteName = noteName;
	}

	public OctaveAlteration getOctave() {
		return octaveAlteration;
	}

	public void setOctave(OctaveAlteration octave) {
		this.octaveAlteration = octave;
	}

	@Override
	public AbsoluteMusicNote toAbsoluteMusicNote(AbsoluteMusicNote relative) {
		int nAlterations = 0;
		AbsoluteMusicNote result = new AbsoluteMusicNote();
		nAlterations += alterationFromReference(relative);
		if(octaveAlteration != null){
			nAlterations += octaveAlteration.toInt();
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
		note.octaveAlteration = null;
		
		return note;
	}

	@Override
	public boolean equals(Object obj) {

		if(!super.equals(obj)){
			return false;
		}
		else{
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
			if (octaveAlteration == null) {
				if (other.octaveAlteration != null)
					return false;
			} else if (!octaveAlteration.equals(other.octaveAlteration))
				return false;
		}
		return true;
	}
	
	/**
	 * Obtains the alteration produces in a MusicNoteName because of the reference
	 * @return A integer value, that means the octave-alteration produces.
	 * If is a silence, returns the relative-octave alteration
	 */
	public int alterationFromReference(AbsoluteMusicNote relative) {

		boolean up = false;
		boolean down = false;

		String rNoteName = relative.getBasicNoteNameString();
		int octave = relative.getOctave().intValue();

		if(this.getMusicNoteName().isSilence() == false){

			int distance = relative.getMusicNoteName().getBaseNoteName().shortestDistance(this.getMusicNoteName().getBaseNoteName());

			if(distance == 3 && (rNoteName.equals("A") || rNoteName.equals("B")|| rNoteName.equals("G"))){
				up = true;
			}
			else if(distance == 2 && (rNoteName.equals("A") || rNoteName.equals("B"))){
				up = true;
			}
			else if(distance == 1 && (rNoteName.equals("B"))){
				up = true;
			}
			else if(distance == -3 && (rNoteName.equals("C") || rNoteName.equals("D") || rNoteName.equals("E"))){
				down = true;
			}
			else if(distance == -2 && (rNoteName.equals("C") || rNoteName.equals("D"))){
				down = true;
			}
			else if(distance == -1 && (rNoteName.equals("C"))){
				down = true;
			}

			if(up){ octave++;}
			else if(down){ octave--;}

		}

		return octave;
	}

	@Override
	public RelativeMusicNote clone() {
		RelativeMusicNote rNote = new RelativeMusicNote();
		rNote.musicNoteName = this.musicNoteName.clone();
		rNote.octaveAlteration = this.octaveAlteration.clone();
		rNote.ligatured = this.ligatured;
		rNote.optional = this.optional;
		if(this.duration != null){
			rNote.duration = this.duration.clone();
		}
		return null;
	}
	
	@Override
	public String toString() {
		String composition = "";
		composition += musicNoteName.toString();
		if(octaveAlteration != null){
			composition += octaveAlteration.toString();
		}
		if(duration != null){
			composition += duration.toString();
		}
		if(ligatured){
			composition += "~";
		}
		if(optional){
			composition = "(" +  composition + ")";
		}
		return composition;
	}
	

}
