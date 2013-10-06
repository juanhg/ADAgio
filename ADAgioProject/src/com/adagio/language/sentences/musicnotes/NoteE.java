package com.adagio.language.sentences.musicnotes;

import org.modelcc.*;

@Pattern(regExp="E")
public class NoteE extends MusicNote implements IModel {

	@Override
	public String getValue() {
		return "e4";
	}

}
