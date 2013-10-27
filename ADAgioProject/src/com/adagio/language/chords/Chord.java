package com.adagio.language.chords;

import org.modelcc.IModel;
import org.modelcc.Optional;
import org.modelcc.Prefix;

import com.adagio.language.musicnotes.MusicNote;

public class Chord implements IModel {
	
	MusicNote note;
	
	ChordIdentifier identifier;
	
	@Optional
	@Prefix("/")
	MusicNote bassNote;

	public Chord(){
		
	}
	
	public Chord(MusicNote note, ChordIdentifier identifier, MusicNote bassNote){
		this.note = note;
		this.identifier = identifier;
		if(bassNote != null){
			this.bassNote = bassNote;
		}
	}
	
	public MusicNote getNote() {
		return note;
	}

	public void setNote(MusicNote note) {
		this.note = note;
	}

	public ChordIdentifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(ChordIdentifier identifier) {
		this.identifier = identifier;
	}

	public MusicNote getBassNote() {
		return bassNote;
	}

	public void setBassNote(MusicNote bassNote) {
		this.bassNote = bassNote;
	}
}
