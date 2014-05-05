package com.adagio.language.statements.simple;

import org.modelcc.IModel;

public abstract class PlayComponent implements IModel {
	public abstract boolean isMelody();
	public abstract boolean isLyrics();
}
