package com.adagio.language.definitions;


import org.modelcc.IModel;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;

public abstract class Definition implements IModel {

	/**
	 * Abstract method to define language's items
	 * @param data Structure needed to create the LilyPond file
	 */
	public abstract void run(MusicEventListener listener);

}