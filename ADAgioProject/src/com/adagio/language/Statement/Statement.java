package com.adagio.language.Statement;

import org.modelcc.IModel;

import com.adagio.language.RunData;

public abstract class Statement implements IModel {

	/**
	 * Abstract method father of all statements in the language
	 * @param data Structure needed to create the LilyPond file
	 */
	public abstract void run(RunData data);

}
