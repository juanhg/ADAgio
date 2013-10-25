package com.adagio.language.chords;

import org.modelcc.IModel;
import org.modelcc.Optional;
import org.modelcc.Prefix;
import org.modelcc.Suffix;

import com.adagio.language.musicnotes.MusicNote;

public class Chord implements IModel {
	
	MusicNote note;
	
	@Prefix("\"")
	@Suffix("\"")
	ChordIdentifier identifier;
	
	@Optional
	@Prefix("/")
	MusicNote bassNote;
}
