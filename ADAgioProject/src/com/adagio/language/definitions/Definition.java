package com.adagio.language.definitions;


import org.modelcc.IModel;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;

@Suffix(";")
public abstract class Definition implements IModel {

	/**
	 * Abstract method to define language's items
	 * @param listener Listener of the Music-events
	 */
	public abstract void run(MusicEventListener listener);

}