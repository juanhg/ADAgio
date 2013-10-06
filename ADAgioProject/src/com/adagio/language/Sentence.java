package com.adagio.language;

import org.modelcc.IModel;

public abstract class Sentence implements IModel {

	/**
	 * Abstract method that charges the contents of the sentence in
	 * a RunData structure
	 * @param data Structure needed to create the lilypond file
	 */
	public abstract void run(RunData data);

}
