package com.adagio.rhythms;

import java.util.ArrayList;
import java.util.List;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.rhythm.RhythmComponent;

public class Rhythm {

	List<RhythmComponent> components;
	
	public Rhythm(){
		components = new ArrayList<RhythmComponent>();
	}
	
	public Rhythm(List<RhythmComponent> components){
		this.components = components;
	}
	
	/**
	 * Takes a list of notes and applies to them the components of the rhythm
	 * @param aNotes
	 * @return
	 */
	List<AbsoluteMusicNote> apply(List<AbsoluteMusicNote> aNotes){
		//TODO implement
		return null;
	}
}
