package test.com.adagio.language.statements;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;
import static org.modelcc.test.ModelAssert.assertInvalid;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import com.adagio.language.definitions.ChordDefinition;
import com.adagio.language.statements.simple.TimeStatement;

public class TimeStatementTest {

	Model model;
	Parser<ChordDefinition> parser;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		model = JavaModelReader.read(TimeStatement.class);
		parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	}

	@Test
	public void timeStatementValidTest() {
		assertAmbiguityFree(parser, "Time 4/4");
		assertAmbiguityFree(parser, "Time 2/4.");
		assertAmbiguityFree(parser, "TIME 2/4.");
		assertAmbiguityFree(parser, "TiMe 5 / 16");
	}
	
	@Test
	public void timeStatementInvalidTest() {
		assertInvalid(parser, "Time 4./4");
		assertInvalid(parser, "Time 3\\4.");
		assertInvalid(parser, "TIM 2/4");
	}

}
