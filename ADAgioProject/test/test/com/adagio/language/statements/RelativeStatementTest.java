package test.com.adagio.language.statements;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;
import static org.modelcc.test.ModelAssert.assertInvalid;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import test.com.adagio.InitTest;

import com.adagio.language.statements.RelativeStatement;


public class RelativeStatementTest extends InitTest {

	  Model model;
	  Parser<RelativeStatement> parser;

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
		super.setUp();
	    model = JavaModelReader.read(RelativeStatement.class);
	    parser = ParserFactory.create(model,ignore);
	  }
	
	@Test
	public void relativeStatementValidTest() {
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
