package test.com.adagio.language.definitions;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserException;
import org.modelcc.parser.ParserFactory;

import test.com.adagio.InitTest;

import com.adagio.language.definitions.InstrumentDefinition;

public class InstrumentDefinitionTest extends InitTest {

	Model model;
	Parser<InstrumentDefinition> parser;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		super.setUp();
		model = JavaModelReader.read(InstrumentDefinition.class);
		parser = ParserFactory.create(model,ignore);
	}

	@Test
	public void instrumentDefinitionValidTest() throws ParserException {
		assertAmbiguityFree(parser,"define instrument \"malevoice\" Polyphonic");
		assertAmbiguityFree(parser, "define instrument \"malevoice\" monophonic timbre voice registers 2C to 5C");
		assertAmbiguityFree(parser, "define instrument \"femalevoice\" monophonic timbre voice registers 3C to 6C");
		assertAmbiguityFree(parser,"define instrument \"voice\" monophonic timbre voice");
		assertAmbiguityFree(parser,"define instrument \"realpiano\" polyphonic registers -4B to 4C  timbre piano");
		assertAmbiguityFree(parser,"define instrument \"piano\" polyphonic timbre piano");
		assertAmbiguityFree(parser,"define instrument \"acousticguitar\" limited polyphonic registers 2E to 3G, 2A to 4C, 3D to 4F, 3G to 4A#, 3B to 5D, 4E to 6G timbre acousticguitar");
		assertAmbiguityFree(parser,"define instrument \"acousticguitar\" limited polyphonic 	registers 2E to 3G,2A to 4C, 3D to 4F, 3G to 4A#, 3B to 5D, 4E to       6G timbre  	acousticguitar");

	}
}
