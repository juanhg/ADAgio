package test.com.adagio.language.sentences;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import com.adagio.language.musicnotes.MusicNote;
import com.adagio.language.sentences.RelativeSentence;

import static org.modelcc.test.ModelAssert.*;


public class SentencesTest {

	  Model model;
	  Parser<MusicNote> parser;

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
	    model = JavaModelReader.read(RelativeSentence.class);
	    parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	  }
	
	@Test
	public void relativeSetencesValidTest() {
	    assertAmbiguityFree(parser,"RELATIVE C");
	    assertAmbiguityFree(parser,"RELATIVE C'");
	    assertAmbiguityFree(parser,"RELaTiVE C''");
	    assertAmbiguityFree(parser,"relative C'''");
	    assertAmbiguityFree(parser,"relative C,");
	    assertAmbiguityFree(parser,"relative C,,,,");
	}
	
	@Test
	public void relativeSetencesInvalidTest() {
		assertInvalid(parser,"RELATIVE c");
		assertInvalid(parser,"RELATIVE c'");
		assertInvalid(parser,"RELATIVE A'");
		assertInvalid(parser,"RELATIVE B'''");
		assertInvalid(parser,"RELATIVE c,");
	}

}
