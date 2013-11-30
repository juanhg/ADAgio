package test.com.adagio.language.statements;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import com.adagio.io.lilypond.LilyPondMusicPieceWriter;
import com.adagio.language.statements.PlayStatement;

public class PlayStatementsTest {

	  Model model;
	  Parser<PlayStatement> parser;
	  LilyPondMusicPieceWriter listener;

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
	    model = JavaModelReader.read(PlayStatement.class);
	    parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	    listener = new LilyPondMusicPieceWriter();
	  }
	
	@Test
	public void playSentenceValidtest() {
		assertAmbiguityFree(parser,"PLAY 3D#m");
		assertAmbiguityFree(parser,"PLAY 3Dbm");
		assertAmbiguityFree(parser,"PLAY 3D#m#");
		assertAmbiguityFree(parser,"PLAY A#M");
	}
}
