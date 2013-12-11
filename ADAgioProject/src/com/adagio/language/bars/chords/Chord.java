package com.adagio.language.bars.chords;

import org.modelcc.IModel;
import org.modelcc.Optional;
import org.modelcc.Prefix;

import com.adagio.events.MusicEventListener;
import com.adagio.language.bars.BarItem;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.MusicNote;

public class Chord  extends BarItem implements IModel {
	
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
	
	public Chord toAbsoluteChord(MusicEventListener listener){
		AbsoluteMusicNote aNote;
		aNote = this.getNote().toAbsoluteMusicNote(listener);

		// We add the bassNote as an absoluteMusicNote
		if (this.getBassNote() != null) {
			bassNote = this.getBassNote().toAbsoluteMusicNote(listener);
		}
		return (new Chord(aNote, this.getIdentifier(), bassNote));
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
