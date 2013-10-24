package com.adagio.language;
import org.modelcc.*;

/**
 * Main Class. This Class constructs the lilypond code.
 * A MusicPiece is sequence of sentences. (or 0)
 * @author Wungo
 */
public class MusicPiece implements IModel {
	@Minimum(0)
	Sentence[] sentences;
	
	/**
	 * Takes a RunData and makes the traduction between
	 * our language and Lilypond.
	 * @return A String in Lilypond's format
	 */

	public Sentence[] getSentences() {
		return sentences;
	}

	public void setSentences(Sentence[] sentences) {
		this.sentences = sentences;
	}
	
	
	
}
