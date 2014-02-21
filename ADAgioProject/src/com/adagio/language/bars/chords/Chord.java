package com.adagio.language.bars.chords;

import org.modelcc.IModel;
import org.modelcc.Optional;
import org.modelcc.Prefix;

import com.adagio.duration.Duration;
import com.adagio.events.MusicEventListener;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.AlteredNoteName;
import com.adagio.language.musicnotes.MusicNote;
import com.adagio.language.musicnotes.RelativeMusicNote;


public class Chord implements IModel {
	
	MusicNote note;
	ChordIdentifier identifier;
	Duration duration;
	
	@Optional
	@Prefix("/")
	MusicNote bassNote;

	public Chord(){}
	
	public static Chord generateSilenceChord(){
		Chord silence = new Chord(RelativeMusicNote.generateSilenceNote(),
				                      new ChordIdentifier(""),null);
		return silence;
	}
	
	public Chord(MusicNote note, ChordIdentifier identifier, MusicNote bassNote){
		this.note = note;
		this.identifier = identifier;
		if(bassNote != null){
			this.bassNote = bassNote;
		}
	}
	
	//TODO Move this function to LilypondMusicPieceWriter
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

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	public boolean isSilence(){
		return this.note.isSilence();
	}
	
}
