package test.com.adagio.language.clefs;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import com.adagio.language.musicnotes.MusicNote;
import com.adagio.language.sentences.ClefSentence;

import static org.modelcc.test.ModelAssert.*;


public class ClefsTest {

	  Model model;
	  Parser<MusicNote> parser;

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
	    model = JavaModelReader.read(ClefSentence.class);
	    parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	  }
	
	@Test
	public void clefSetencesValidTest() {
	    assertAmbiguityFree(parser,"CLEF TENOR");
	    assertAmbiguityFree(parser,"CLEF TREBLE");
	    assertAmbiguityFree(parser,"CLEF alto");
	    assertAmbiguityFree(parser,"clef bass");
	}
	
	@Test
	public void relativeSetencesInvalidTest() {
		assertInvalid(parser,"CLEFT TENOR");
		assertInvalid(parser,"CLEFE BASS'");
		assertInvalid(parser,"CLEF BASSS'");
		assertInvalid(parser,"CLEF treeble");
	}

}
