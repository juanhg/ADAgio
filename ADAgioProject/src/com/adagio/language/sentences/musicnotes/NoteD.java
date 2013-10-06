package com.adagio.language.sentences.musicnotes;

import org.modelcc.*;

@Pattern(regExp="D")
public class NoteD extends MusicNote implements IModel {

	@Override
	public String getValue() {
		return "d4";
	}

}
