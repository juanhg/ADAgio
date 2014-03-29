package test.com.adagio.language.statements;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;
import static org.modelcc.test.ModelAssert.assertInvalid;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserException;
import org.modelcc.parser.ParserFactory;

import test.com.adagio.InitTest;

import com.adagio.language.statements.PlayStatement;

public class PlayStatementTest extends InitTest {

	Model model;
	Parser<PlayStatement> parser;

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
		super.setUp();
	    model = JavaModelReader.read(PlayStatement.class);
	    parser = ParserFactory.create(model,ignore);
	
	  }
	
	@Test
	public void playSentenceValidtest() throws ParserException {
		
		assertAmbiguityFree(parser, "PLAY Bb B|A B");
		assertAmbiguityFree(parser, "PLAY  | Bb B | A|");
		assertAmbiguityFree(parser, "PLAY  | Bb B | A |");
		assertAmbiguityFree(parser, "PLAY | Bb B | A");
		assertAmbiguityFree(parser, "PLAY Bb B | A");
		assertAmbiguityFree(parser, "PLAY Bb S |");
		assertAmbiguityFree(parser, "PLAY | Bb S |");
		assertAmbiguityFree(parser,"PLAY | 3D#m |");
		assertAmbiguityFree(parser,"PLAY  3D#m |");
		assertAmbiguityFree(parser,"PLAY | 3D#m ");
		assertAmbiguityFree(parser,"PLAY 3D#m");
		assertAmbiguityFree(parser,"PLAY | 3Dbm |");
		assertAmbiguityFree(parser,"PLAY | 3D#m# |");
		assertAmbiguityFree(parser,"PLAY | A#M |");
		assertAmbiguityFree(parser,"PLAY | G Cm |"); // Nos garantiza que es válido y no hay ambigüedades
	    assertAmbiguityFree(parser, "PLAY | G |");
	    assertAmbiguityFree(parser, "PLAY | Gm |");
	    assertAmbiguityFree(parser, "PLAY | Cm |");
	    assertAmbiguityFree(parser, "PLAY | G C |");
	    assertAmbiguityFree(parser, "PLAY | Gm C |");
	    assertAmbiguityFree(parser, "PLAY | G Cm |");
	    assertAmbiguityFree(parser, "PLAY | G Cm |");
	    assertAmbiguityFree(parser, "PLAY | Bb S |");
	    assertAmbiguityFree(parser, "PLAY Bb S | A B |");
	    assertAmbiguityFree(parser, "PLAY Bb S |A B |");
	    assertAmbiguityFree(parser, "PLAY Bb S | A B|");
	    assertAmbiguityFree(parser, "PLAY | Bb S| A B|");
	    
	}
	
	@Test
	public void playSentenceInvalidtest() {
		assertInvalid(parser,"PLAY");
		assertInvalid(parser, "PLAYBb S |");
	}
}
