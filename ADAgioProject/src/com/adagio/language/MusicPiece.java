package com.adagio.language;
import org.modelcc.*;

import com.adagio.language.definitions.Definition;
import com.adagio.language.sentences.Sentence;

/**
 * Main Class. This Class constructs the lilypond code.
 * A MusicPiece is sequence of sentences. (or 0)
 * @author Wungo
 */
public class MusicPiece implements IModel {
	@Minimum(0)
	Definition[] definitions;
	@Minimum(0)
	Sentence[] sentences;
	
	/**
	 * Charges in data the information needed to translate
	 */

	public void run(RunData data){
		for(Definition current: this.getDefinitions()){
			current.run(data);
		}
		
		for(Sentence current: this.getSentences()){
			current.run(data);
		}
	}
	
	public Sentence[] getSentences() {
		return sentences;
	}

	public void setSentences(Sentence[] sentences) {
		this.sentences = sentences;
	}

	public Definition[] getDefinitions() {
		return definitions;
	}

	public void setDefinitions(Definition[] definitions) {
		this.definitions = definitions;
	}
	
	
	
}
