package com.adagio.language.sentences.musicnotes;

import org.modelcc.*;

@Pattern(regExp="B")
public class NoteB extends MusicNote implements IModel {

	@Override
	public String getValue() {
		return "b4";
	}
}
