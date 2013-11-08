package com.adagio.language.musicnotes;

import org.modelcc.IModel;

import com.adagio.language.musicnotes.notealterations.Alteration;

public abstract class MusicNoteName implements IModel {

	public abstract BasicNoteName getBaseNoteName();
	public abstract Alteration getAlteration();
	public abstract MusicNoteName clone();
	public abstract boolean equals(MusicNoteName name);
	public abstract String toString();
}
