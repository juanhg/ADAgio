package com.adagio.language.sentences.musicnotes;

import org.modelcc.*;

@Pattern(regExp="F")
public class NoteF extends MusicNote implements IModel {

	@Override
	public String getValue() {
		return "f4";
	}

}