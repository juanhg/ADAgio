package com.adagio.language.musicnotes;

import org.modelcc.IModel;

import com.adagio.language.RunData;

public abstract class MusicNoteName implements IModel {
	public abstract String toString(RunData data);
}
