package com.adagio.language.musicnotes;

import org.modelcc.IModel;
import org.modelcc.Optional;

import com.adagio.language.RunData;
import com.adagio.language.musicnotes.octavealterations.OctaveAlteration;

public class RelativeMusicNote extends MusicNote implements IModel {
	
	public MusicNoteName musicNoteName;
	@Optional
	public OctaveAlteration octave;
	
	@Override
	public String toString(RunData data) {
		
		int nAlterations = 0; 
		String result = "";
		result += musicNoteName.toString(data);
		
		nAlterations += data.alterationFromReference(musicNoteName);
		
		if(octave != null){
			nAlterations += octave.toInt();
		}
		
		for(int i = 0; i < Math.abs(nAlterations); i++){
			if(nAlterations > 0){
				result += "'";
			}
			else{
				result += ",";
			}
		}
		return result;
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
