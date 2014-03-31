package test.com.adagio.language.statements;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserException;
import org.modelcc.parser.ParserFactory;

import com.adagio.language.statements.LyricsStatement;

public class LyricsStatementTest {

	Model model;
	Parser<LyricsStatement> parser;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		model = JavaModelReader.read(LyricsStatement.class);
		parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	}

	@Test
	public void melodySentenceValidtest() throws ParserException {
		assertAmbiguityFree(parser,"Lyrics voz | hi my friend |");
		assertAmbiguityFree(parser,"Lyrics voz | hi friend of mine | hi my friend");
		
	}
	
	@Test
	public void melodySentenceInvalidtest() {
	}

}
