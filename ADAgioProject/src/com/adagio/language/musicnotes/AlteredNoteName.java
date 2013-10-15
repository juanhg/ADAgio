package com.adagio.language.musicnotes;

import org.modelcc.IModel;

import com.adagio.language.musicnotes.notealterations.Alteration;

public class AlteredNoteName extends MusicNoteName implements IModel {

	BasicNoteName noteName;
	Alteration alteration;
	
	@Override
	public String toString() {
		return noteName.toString() + alteration.toString();
	}

}
