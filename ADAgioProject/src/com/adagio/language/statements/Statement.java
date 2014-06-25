package com.adagio.language.statements;

import org.modelcc.IModel;

import com.adagio.events.MusicEventListener;

public abstract class Statement implements IModel {

	/**
	 * Abstract method father of all statements in the language
	 * @param listener Listener of the Music-events
	 */
	public abstract void run(MusicEventListener listener);

}
