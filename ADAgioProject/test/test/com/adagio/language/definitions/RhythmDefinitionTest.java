package test.com.adagio.language.definitions;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;
import static org.modelcc.test.ModelAssert.assertInvalid;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import com.adagio.language.definitions.RhythmDefinition;

public class RhythmDefinitionTest {


	Model model;
	Parser<RhythmDefinition> parser;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		model = JavaModelReader.read(RhythmDefinition.class);
		parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	}
	
	@Test
	public void rhythmDefinitionsValidTest() {
		assertAmbiguityFree(parser, "Define Rhythm \"ritmoRock\" Note 0 to 0.25 Note 0.25 to 1");
		assertAmbiguityFree(parser, "Define Rhythm \"ritmoPop\" 1A# 0.0 to 0.75 Note 0.75 to 1");
		assertAmbiguityFree(parser, "Define Rhythm \"A\" 4Cbb 0.0 to 0.75");
	}
	
	@Test
	public void rhythmDefinitionsInvalidTest() {
		assertInvalid(parser, "DEFINE Rhtythm \"ritmoRock\" Note 0 to 0.25 Note 0.25 to 1");
		assertInvalid(parser, "Define Rhythm \"ritmoPop\"");
		assertInvalid(parser, "Define Rhythm \"ritmoPop\" 1A# 0.3 to 0.2 Note 0.2 to 4");
		assertInvalid(parser, "Define Rhythm \"ritmoPop\" 1A# 0.0 to 0.75 Note 0.75 to 1.1");
		assertInvalid(parser, "Define Rhythm \"ritmoPop\" Note -1 to 0.75 Note 0.75 to 1");
	}

}
