package com.adagio.language.rhythm;

import org.modelcc.IModel;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.MusicNote;

public class AbsoluteRhythmNote extends RhythmNote implements IModel{
    MusicNote note;

    public AbsoluteRhythmNote(){}
    
    public AbsoluteRhythmNote(AbsoluteMusicNote aNote){
    	note = aNote;
    }
    
	@Override
	public String toString() {
		return note.toString();
	}
	
	public int getNotePosition(){
		return -1;
	}

	@Override
	public MusicNote getMusicNote() {
		return note;
	}
}
