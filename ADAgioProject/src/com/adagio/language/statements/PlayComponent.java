package com.adagio.language.statements;

import org.modelcc.IModel;

public abstract class PlayComponent implements IModel {
	public abstract boolean isMelody();
	public abstract boolean isLyrics();
}
