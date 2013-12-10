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
import com.adagio.language.definitions.TempoDefinition;

public class TempoDefinitionsTest {
	
	Model model;
	Parser<ChordDefinition> parser;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		model = JavaModelReader.read(TempoDefinition.class);
		parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	}

	@Test
	public void tempoDefinitionValidTest() {
		assertAmbiguityFree(parser,"DEFINE TEMPO \"andante\" 4=50");
		assertAmbiguityFree(parser,"DEFINE TEMPO \"A\" 4..=70");
		assertAmbiguityFree(parser,"DEFINE TEMPO \"ALLEGRO\" 1=200");
		assertAmbiguityFree(parser,"DEFINE TEMPO \"PreSto\" 8. = 50");
	}
	
	@Test
	public void tempoDefinitionInvalidTest() {
		assertInvalid(parser, "DEFINE TEMPO \"\" 4=50");
		assertInvalid(parser, "DEFINE TEMPO \"andante\" 5==50");
		assertInvalid(parser, "DEFINE TEMPO \"andante\" 4");
	}
}
