package test.com.adagio.language.definitions;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;
import static org.modelcc.test.ModelAssert.assertInvalid;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import com.adagio.language.definitions.ChordDefinition;

public class ChordDefinitionsTest {
	
	  Model model;
	  Parser<ChordDefinition> parser;

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
	    model = JavaModelReader.read(ChordDefinition.class);
	    parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	  }
	
	@Test
	public void chordDefinitionTest() {
		assertAmbiguityFree(parser,"DEFINE CHORD \"M\" NOTES P1 M3 P5");
		assertAmbiguityFree(parser,"DEFINE CHORD \"m\" NOTES P1 m3 P5");
		assertAmbiguityFree(parser, "DEFINE CHORD \"add2\" NOTES P1 M2 (M3) P5");
		assertAmbiguityFree(parser,"DEFINE CHORD \"M#\" NOTES AA3 m3 P5");
		assertAmbiguityFree(parser,"DEFINE CHORD \"Mbb\" NOTES P1 dd3 P5");
		assertAmbiguityFree(parser,"DEFINE CHORD \"Mx\" NOTES P1 M3 P5");
		assertAmbiguityFree(parser,"DEFINE CHORD \"M0#x\" NOTES P1 M3 P5");
		
	}
	
	 @Test
	  public void musicNoteInvalidOctaveTest() {
		 assertInvalid(parser,"DEFINE CHORD \"#\" NOTES P1 m3 P5");
		 assertInvalid(parser,"DEFINE CHORD \"x\" NOTES P1 m3 P5");
		 assertInvalid(parser,"DEFINE CHORD \"b\" NOTES P1 m3 P5");
		 assertInvalid(parser,"DEFINE CHORD \"bb\" NOTES P1 m3 P5");
		 assertInvalid(parser,"DEFINE CHORD \"bbMm\" NOTES P1 m3 P5");
		 assertInvalid(parser,"DEFINE CHORD \"xasdf\" NOTES P1 m3 P5");
		 assertInvalid(parser,"DEFINE CHORD \"A\" NOTES P1 M3 P5");
		 assertInvalid(parser,"DEFINE CHORD \"C\" NOTES P1 M3 P5");
		 assertInvalid(parser,"DEFINE CHORD \"4Mayor\" NOTES P1 M3 P5");
		 assertInvalid(parser,"DEFINE CHORD \"5Minor\" NOTES P1 M3 P5");
	  }

}
