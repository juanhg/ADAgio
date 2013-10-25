package com.adagio.language.sentences;

import org.modelcc.IModel;

import com.adagio.language.RunData;

public abstract class Sentence implements IModel {

	/**
	 * Abstract method father of all sentences in the language
	 * @param data Structure needed to create the LilyPond file
	 */
	public abstract void run(RunData data);

}
