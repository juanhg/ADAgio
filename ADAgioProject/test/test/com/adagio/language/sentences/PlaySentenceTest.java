package test.com.adagio.language.sentences;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import com.adagio.language.RunData;
import com.adagio.language.musicnotes.MusicNote;
import com.adagio.language.sentences.PlaySentence;

public class PlaySentenceTest {

	  Model model;
	  Parser<MusicNote> parser;
	  RunData data;

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
		data = new RunData();
	    model = JavaModelReader.read(PlaySentence.class);
	    parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	  }
	
	@Test
	public void playSentenceValidtest() {
		assertAmbiguityFree(parser,"PLAY 3D#m");
		assertAmbiguityFree(parser,"PLAY 3Dbm");
		assertAmbiguityFree(parser,"PLAY 3D#m#");
		assertAmbiguityFree(parser,"PLAY A#M");
	}

}
