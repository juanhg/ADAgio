package com.adagio.language.sentences.musicnotes;

import org.modelcc.*;

@Pattern(regExp="G")
public class NoteG extends MusicNote implements IModel {

	@Override
	public String getValue() {
		return "g4";
	}

}
