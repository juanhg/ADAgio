package com.adagio.language.sentences.musicnotes;

import org.modelcc.IModel;

import com.adagio.language.sentences.musicnotes.alterations.Alteration;

public class AlteredNoteName extends MusicNoteName implements IModel {

	BasicNoteName noteName;
	Alteration alteration;
	
	@Override
	public String toString() {
		return noteName.toString() + alteration.toString();
	}

}
