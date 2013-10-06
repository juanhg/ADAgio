package com.adagio.language.sentences.musicnotes;

import org.modelcc.*;

@Pattern(regExp="C")
public class NoteC extends MusicNote implements IModel {

	@Override
	public String getValue() {
		return "c4";
	}

}
