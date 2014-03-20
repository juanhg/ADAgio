package test.com.adagio.language.statements;

import static org.junit.Assert.*;
import static org.modelcc.test.ModelAssert.assertAmbiguityFree;
import static org.modelcc.test.ModelAssert.assertInvalid;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserException;
import org.modelcc.parser.ParserFactory;

import com.adagio.language.statements.MelodyStatement;

public class MelodyStatementTest {

	Model model;
	Parser<MelodyStatement> parser;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		model = JavaModelReader.read(MelodyStatement.class);
		parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	}

	@Test
	public void melodySentenceValidtest() throws ParserException {
		assertAmbiguityFree(parser,"MELODY voz  D4");
		assertAmbiguityFree(parser,"MELODY voz2  D4 | A8");
		assertAmbiguityFree(parser,"MELODY voz  D4 A8");
		assertAmbiguityFree(parser,"MELODY voz  D2 3A8");
		assertAmbiguityFree(parser,"MELODY voz  4D4 4A8");
		assertAmbiguityFree(parser,"MELODY voz  4D4. 4A8");
		assertAmbiguityFree(parser,"MELODY voz  4D4 4A8..");
		assertAmbiguityFree(parser,"MELODY voz  4D4.. 4A8...");
		assertAmbiguityFree(parser,"melody voz  4D4 4A8 | 3A1 C4 ");
		assertAmbiguityFree(parser,"MELODY voz  | A8. |");
		
		assertAmbiguityFree(parser,"MELODY  D1 6A8");
		MelodyStatement mStat = parser.parse("MELODY voz2  D1 6A8");
		assertEquals(1, mStat.getMBars()[0].getMComponents()[0].getFigure().getShape().intValue());
		
		
		assertAmbiguityFree(parser,"MELODY voz  1D1 6A8");
		mStat = parser.parse("MELODY voz 1D1 6A8");
		assertEquals(1, mStat.getMBars()[0].getMComponents()[0].getFigure().getShape().intValue());
		
		mStat = parser.parse("Melody voz 4B 4B4");
	}
	
	@Test
	public void melodySentenceInvalidtest() {
		assertInvalid(parser,"Melody");
		assertInvalid(parser,"Melody voz A");
		assertInvalid(parser,"Melody voz 4A");
		assertInvalid(parser,"Melody voz 4A4 || 4B2");
		assertInvalid(parser,"Melody voz 4B 4B4");
	}


}
