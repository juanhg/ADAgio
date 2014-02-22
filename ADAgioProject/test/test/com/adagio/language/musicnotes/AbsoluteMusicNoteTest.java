package test.com.adagio.language.musicnotes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import test.com.adagio.InitTest;

import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class AbsoluteMusicNoteTest extends InitTest {
		

	Model model;
	Parser<AbsoluteMusicNote> parser;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		super.setUp();
		model = JavaModelReader.read(AbsoluteMusicNote.class);
		parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	}


	public void isHigherTest() {
		
		assertTrue(A3.isHigher(A2));
		assertTrue(A3.isHigher(C3));
		assertTrue(Dm1.isHigher(new AbsoluteMusicNote(-1, "C")));
		
		assertFalse("Error in isHigerTest", A3.isHigher(A3));
		assertFalse("Error in isHigerTest", Am1.isHigher(Am1));
		assertFalse("Error in isHigerTest", A3.isHigher(B3));
		assertFalse("Error in isHigerTest", Am1.isHigher(C4));
		assertFalse("Error in isHigerTest", Cm1.isHigher(new AbsoluteMusicNote(-1, "G")));
		
	}
	
	@Test
	public void semitonesTill() {
		//Positive results
		assertEquals(3, A3.semitonesTill(C4));
		assertEquals(24, A3.semitonesTill(A5));
		assertEquals(25, A3.semitonesTill(A5Sharp));
		assertEquals(10, A3.semitonesTill(A4bb));
		
		//Negative results
		assertEquals(-3, C4.semitonesTill(A3));
		assertEquals(-24, A5.semitonesTill(A3));
		assertEquals(-25, A5Sharp.semitonesTill(A3));
		assertEquals(-10, A4bb.semitonesTill(A3));
		
		//Cero Results
		assertEquals(0, A3.semitonesTill(A3));
		assertEquals(0, A5Sharp.semitonesTill(A5Sharp));
	}
	
	public void nextTest(){
		assertEquals(Cm3, Bm4.next());
		assertEquals(B3, C4.next());
		assertEquals(Am5, Gm5.next());
		assertEquals(A3, G3.next());
	}
	
	public void previousTest(){
		assertEquals(Bm4, Cm3.next());
		assertEquals(C4, B3.next());
		assertEquals(Gm5, Am5.next());
		assertEquals(G3, A3.next());
	}
	
	public void lowerNotePositionTest(){
		List<AbsoluteMusicNote> aNotes = new ArrayList<AbsoluteMusicNote>();
		
		aNotes.add(A2);
		aNotes.add(A3);
		assertEquals(0, AbsoluteMusicNote.lowerNotePosition(aNotes));
		
		aNotes.clear();
		aNotes.add(A5);
		aNotes.add(A5Sharp);
		assertEquals(1, AbsoluteMusicNote.lowerNotePosition(aNotes));
		
		aNotes.clear();
		aNotes.add(Am1);
		aNotes.add(B3);
		aNotes.add(Am5);
		aNotes.add(Bm4);
		assertEquals(2, AbsoluteMusicNote.lowerNotePosition(aNotes));
	}
	
	@Test
	public void toAbsoluteTest(){
		AbsoluteMusicNote result = super.SRelative.toAbsoluteMusicNote(listener);
		assertTrue(result.isSilence());
		
		result = S0.toAbsoluteMusicNote(listener);
		assertTrue(result.isSilence());
	}
}
