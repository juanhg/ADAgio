package com.adagio.language.sentences.musicnotes;

import org.modelcc.*;

@Pattern(regExp="A")
public class NoteA extends MusicNote implements IModel {

	@Override
	public String getValue() {
		return "a4";
	}

}