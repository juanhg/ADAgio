package com.adagio.language.musicnotes;

import org.modelcc.IModel;

import com.adagio.language.musicnotes.notealterations.Alteration;

public class AlteredNoteName extends MusicNoteName implements IModel {

	BasicNoteName basicNoteName;
	Alteration alteration;
	
	@Override
	public String toString() {
		return basicNoteName.toString() + alteration.toString();
	}

	public BasicNoteName getBasicNoteName() {
		return basicNoteName;
	}

	public void setBasicNoteName(BasicNoteName basicNoteName) {
		this.basicNoteName = basicNoteName;
	}

	public Alteration getAlteration() {
		return alteration;
	}

	public void setAlteration(Alteration alteration) {
		this.alteration = alteration;
	}
	
	

}
