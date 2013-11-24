package test.com.adagio.language.statements;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import com.adagio.io.lilypond.RunData;
import com.adagio.language.statements.RelativeStatement;

import static org.modelcc.test.ModelAssert.*;


public class RelativeStatementsTest {

	  Model model;
	  Parser<RelativeStatement> parser;
	  RunData data;

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
		data = new RunData();
	    model = JavaModelReader.read(RelativeStatement.class);
	    parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	  }
	
	@Test
	public void relativeSetencesValidTest() {
	    assertAmbiguityFree(parser,"RELATIVE 0C");
	    assertAmbiguityFree(parser,"RELATIVE 1C");
	    assertAmbiguityFree(parser,"RELaTiVE 2C");
	    assertAmbiguityFree(parser,"relative 3C");
	    assertAmbiguityFree(parser,"relative -2C");
	    assertAmbiguityFree(parser,"relative -4C");
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
