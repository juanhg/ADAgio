package com.adagio.language.definitions;


import org.modelcc.IModel;

import com.adagio.events.MusicEventListener;
import com.adagio.io.lilypond.RunData;

public abstract class Definition implements IModel {

	/**
	 * Abstract method to define language's items
	 * @param data Structure needed to create the LilyPond file
	 */
	public abstract void run(RunData data, MusicEventListener listener);

}