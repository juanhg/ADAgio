package test.com.adagio.language.statements;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import test.com.adagio.InitTest;

import com.adagio.language.statements.TestStatement;

public class TestStatementTest extends InitTest {

	  Model model;
	  Parser<TestStatement> parser;

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
		super.setUp();
	    model = JavaModelReader.read(TestStatement.class);
	    parser = ParserFactory.create(model,ignore);
	  }
	
	@Test
	public void testStatementValidTest() {
	    assertAmbiguityFree(parser,"Test A");
	    assertAmbiguityFree(parser,"Test A B	|	B"); //No deberia funcionar y lo hace. 
	    assertAmbiguityFree(parser,"Test A B | B"); //No deberia funcionar y lo hace 
	    assertAmbiguityFree(parser,"Test A B|B"); //No funciona
	}
}
