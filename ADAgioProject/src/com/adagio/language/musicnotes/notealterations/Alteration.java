package com.adagio.language.musicnotes.notealterations;

import org.modelcc.IModel;

public abstract class Alteration implements IModel {
	public abstract String getValue();
	public abstract String toString();
	public abstract Alteration clone();
}
