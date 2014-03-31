package com.adagio.language.bars.chords;

import org.modelcc.IModel;
import org.modelcc.Optional;
import org.modelcc.Prefix;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.MusicNote;
import com.adagio.language.musicnotes.RelativeMusicNote;
import com.adagio.structures.Duration;


public class Chord implements IModel {
	
	MusicNote note;
	ChordIdentifier identifier;
	
	@Optional
	@Prefix("/")
	MusicNote bassNote;

	Duration duration;
	
	public Chord(){}
	
	/**
	 * Generate a Chord that represents a silence
	 * @return Silence-Chord
	 */
	public static Chord genSilence(){
		Chord silence = new Chord(RelativeMusicNote.genSilence(),
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
	
	public Chord(MusicNote note, ChordIdentifier identifier, MusicNote bassNote, Duration duration){
		this.note = note;
		this.identifier = identifier;
		if(bassNote != null){
			this.bassNote = bassNote;
		}
		this.duration = duration;
	}
	
	//TODO Move this function to LilypondMusicPieceWriter
	public Chord toAbsoluteChord(AbsoluteMusicNote relative){
		AbsoluteMusicNote aNote;
		aNote = this.getNote().toAbsoluteMusicNote(relative);

		// We add the bassNote as an absoluteMusicNote
		if (this.getBassNote() != null) {
			bassNote = this.getBassNote().toAbsoluteMusicNote(relative);
		}
		if(duration == null){
			return (new Chord(aNote, identifier, bassNote));
		}
		else{
			return (new Chord(aNote, identifier, bassNote, duration));
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

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	public boolean isSilence(){
		return this.note.isSilence();
	}
	
	public String toString(){
		String composition = "";
		composition += note.toString() + identifier.value;
		if(bassNote != null){
			composition += "/" +  bassNote.toString(); 
		}
		if(duration != null){
			composition += duration.toString();
		}
		return composition;
	}
	
}
